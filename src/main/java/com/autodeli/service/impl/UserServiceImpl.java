package com.autodeli.service.impl;

import com.autodeli.exception.EntityNotFoundException;
import com.autodeli.exception.InvalidEntityDataException;
import com.autodeli.repository.UserRepository;
import com.autodeli.service.UserService;
import com.autodeli.web.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;

  @Autowired
  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  @Override
  public User getUserById(String id) {
    return userRepository.findById(id).orElseThrow(() ->
        new EntityNotFoundException(String.format("User with ID:%s does not exist.", id)));
  }

  @Override
  public User getUserByUsername(String username) {
    return userRepository.findByUsername(username).orElseThrow(() ->
        new InvalidEntityDataException("Invalid username or password."));
  }

  @Override
  public User addUser(User user) {
    user.setId(null);
    PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    user.setPassword(encoder.encode(user.getPassword()));
    return userRepository.insert(user);
  }

  @Override
  public User updateUser(User user) {
    User temp = getUserById(user.getId());
    user.setPassword(temp.getPassword());
    user.setModified(LocalDateTime.now());
    return userRepository.save(user);
  }

  @Override
  public User deleteUser(String id) {
    User removed = getUserById(id);
    userRepository.deleteById(id);
    return removed;
  }

  @Override
  public boolean existsByUsername(String username) {
    return userRepository.existsByUsername(username);
  }

  @Override
  public long getCount() {
    return userRepository.count();
  }
}
