package com.autodeli.controller.user;

import com.autodeli.exception.InvalidEntityDataException;
import com.autodeli.service.user.UserService;
import com.autodeli.web.ShoppingCart;
import com.autodeli.web.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
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

  @GetMapping("/{id}")
  public User getUserById(@PathVariable("id") String id) {
    return userService.getUserById(id);
  }

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
    if (!id.equals(user.getId())) {
      throw new InvalidEntityDataException(
          String.format("User URL ID:%s differs from body entity ID:%s", id, user.getId()));
    }
    return userService.updateUser(user);
  }

  @DeleteMapping("/{id}")
  public User deleteUser(@PathVariable String id) {
    User removed = getUserById(id);
    userService.deleteUser(id);
    return removed;
  }
}