package com.autodeli.web.user;

import lombok.Data;

@Data
public class JwtResponse {

  private final User user;
  private final String token;
}
