package com.autodeli.controller.user;

import com.autodeli.exception.InvalidEntityDataException;
import com.autodeli.service.user.UserService;
import com.autodeli.utils.JwtUtils;
import com.autodeli.web.user.Credentials;
import com.autodeli.web.user.JwtResponse;
import com.autodeli.web.user.Role;
import com.autodeli.web.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;
import java.util.HashSet;

import static com.autodeli.utils.ErrorHandlingUtils.getErrors;

@RestController
@Slf4j
public class LoginController {
  @Autowired
  private UserService userService;
  @Autowired
  private JwtUtils jwtUtils;
  @Autowired
  private AuthenticationManager authenticationManager;

  @PostMapping("/api/login")
  public JwtResponse login(@Valid @RequestBody Credentials credentials, Errors errors) {
    if (errors.hasErrors()) {
      throw new InvalidEntityDataException("Invalid username or password");
    }
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
        credentials.getUsername(), credentials.getPassword()));
    final User user = userService.getUserByUsername(credentials.getUsername());
    final String token = jwtUtils.generateToken(user);
    log.info("Login successful for {}: {}", user.getUsername(), token); //remove it!
    return new JwtResponse(user, token);
  }

  @PostMapping("/api/register")
  public User register(@Valid @RequestBody User user, Errors errors) {
    if (errors.hasErrors()) {
      throw new InvalidEntityDataException("Invalid user data", getErrors(errors));
    }
    if (userService.existsByUsername(user.getUsername())) {
      throw new InvalidEntityDataException("Username is taken");
    }
    user.setRoles(new HashSet<>(Collections.singletonList(Role.USER)));
    return userService.addUser(user);
  }
}