package com.dev.gabriel.authentication_microservice.service;

import com.dev.gabriel.authentication_microservice.consumer.dto.NewUserRegisteredMessage;
import com.dev.gabriel.authentication_microservice.model.entity.User;
import com.dev.gabriel.authentication_microservice.model.enums.UserRole;
import com.dev.gabriel.authentication_microservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;

  public void registerUser(NewUserRegisteredMessage message) {
    if (this.userRepository.existsByEmail(message.email())) {
      throw new IllegalArgumentException("This email is already registered");
    }

    UUID newUserId = null;

    try {
      newUserId = UUID.fromString(message.id());
    } catch (IllegalArgumentException ex) {
      throw new IllegalArgumentException("This ID is invalid");
    }

    if (this.userRepository.existsById(newUserId)) {
      throw new IllegalArgumentException("This ID is already registered");
    }

    UserRole newUserRole = null;

    try {
      newUserRole = UserRole.valueOf(message.role().toUpperCase());
    } catch (IllegalArgumentException ex) {
      throw new IllegalArgumentException("This role is invalid");
    }

    User newUser =
        User.builder()
            .id(newUserId)
            .email(message.email())
            .password(message.password())
            .role(newUserRole)
            .isBlocked(Boolean.FALSE)
            .build();

    this.userRepository.save(newUser);
  }
}
