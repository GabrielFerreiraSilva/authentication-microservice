package com.dev.gabriel.authentication_microservice.service;

import com.dev.gabriel.authentication_microservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
  private final JwtEncoder jwtEncoder;
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Value("${token.issuer}")
  private String tokenIssuer;

  @Value("${token.duration.seconds}")
  private Long tokenDurationSeconds;
}
