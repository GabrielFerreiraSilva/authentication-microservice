package com.dev.gabriel.authentication_microservice.model.entity;

import com.dev.gabriel.authentication_microservice.model.enums.UserRole;

import java.util.UUID;

public class User {
  private UUID id;
  private String email;
  private String password;
  private UserRole role;
  private Boolean isBlocked;
}
