package com.dev.gabriel.authentication_microservice.model.entity;

import java.time.Instant;
import java.util.UUID;

public class RefreshToken {
  private UUID id;
  private String token;
  private User user;
  private Instant expiresAt;
}
