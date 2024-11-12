package com.dev.gabriel.authentication_microservice.service;

import com.dev.gabriel.authentication_microservice.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
  private final RefreshTokenRepository refreshTokenRepository;

  @Value(value = "${refresh-token.duration-seconds}")
  private Long refreshTokenDurationSeconds;
}
