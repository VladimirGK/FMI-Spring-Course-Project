package com.autodeli.controller.user;

import com.autodeli.exception.InvalidEntityDataException;
import com.autodeli.service.user.UserService;
import com.autodeli.web.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public List<User> getAllUsers() {
    return userService.getAllUsers();
  }

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/{id}")
  public User getUserById(@PathVariable("id") String id) {
    return userService.getUserById(id);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping
  public ResponseEntity<User> createUser(@RequestBody User user) {
    User created = userService.addUser(user);
    return ResponseEntity.created(
        ServletUriComponentsBuilder.fromCurrentRequest().pathSegment("{id}")
            .buildAndExpand(created.getId()).toUri()
    ).body(created);
  }

  @PutMapping("/{id}")
  public User updateUser(@PathVariable String id, @RequestBody User user) {
    User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (id.equals(principal.getId()) || SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
      return userService.updateUser(user);
    }
    throw new InvalidEntityDataException(
            String.format("User URL ID:%s differs from body entity ID:%s", id, user.getId()));
  }

  @PreAuthorize("hasRole('ADMIN')")
  @DeleteMapping("/{id}")
  public User deleteUser(@PathVariable String id) {
    User removed = getUserById(id);
    userService.deleteUser(id);
    return removed;
  }
}