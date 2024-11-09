package com.dev.gabriel.authentication_microservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {
  @Value("${security.private-key-path}")
  private String privateKeyPath;

  @Value("${security.public-key-path}")
  private String publicKeyPath;
}
