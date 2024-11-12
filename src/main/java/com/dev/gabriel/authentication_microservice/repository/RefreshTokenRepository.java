package com.dev.gabriel.authentication_microservice.repository;

import com.dev.gabriel.authentication_microservice.model.entity.RefreshToken;
import com.dev.gabriel.authentication_microservice.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, UUID> {
  void deleteByUser(User user);
}
