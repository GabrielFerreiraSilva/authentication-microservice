package com.dev.gabriel.authentication_microservice.model.entity;

import com.dev.gabriel.authentication_microservice.model.enums.UserRole;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tb_user")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private String password;

  @Enumerated(EnumType.STRING)
  private UserRole role;

  @Column(nullable = false)
  private Boolean isBlocked;
}
