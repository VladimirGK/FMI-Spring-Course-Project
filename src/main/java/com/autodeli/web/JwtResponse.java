package com.autodeli.web;

import lombok.Data;

@Data
public class JwtResponse {

  private final User user;
  private final String token;
}
