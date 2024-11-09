package com.dev.gabriel.authentication_microservice.service;

import com.dev.gabriel.authentication_microservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;
}
