package com.autodeli.service;

import com.autodeli.web.User;

import java.util.List;

public interface UserService {

  List<User> getAllUsers();

  User getUserById(String id);

  User getUserByUsername(String username);

  User addUser(User user);

  User updateUser(User user);

  User deleteUser(String id);

  boolean existsByUsername(String username);

  long getCount();
}
