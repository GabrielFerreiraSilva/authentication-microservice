package com.dev.gabriel.authentication_microservice.service;

import com.dev.gabriel.authentication_microservice.model.entity.RefreshToken;
import com.dev.gabriel.authentication_microservice.model.entity.User;
import com.dev.gabriel.authentication_microservice.repository.RefreshTokenRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
  private final RefreshTokenRepository refreshTokenRepository;

  @Value(value = "${refresh-token.duration-seconds}")
  private Long refreshTokenDurationSeconds;

  @Transactional
  public String createRefreshToken(User user) {
    this.refreshTokenRepository.deleteByUser(user);

    RefreshToken refreshToken =
        RefreshToken.builder()
            .token(UUID.randomUUID().toString())
            .user(user)
            .expiresAt(Instant.now().plusSeconds(refreshTokenDurationSeconds))
            .build();

    this.refreshTokenRepository.save(refreshToken);
    return refreshToken.getToken();
  }

  @Transactional
  public RefreshToken validateRefreshToken(String token) {
    RefreshToken refreshToken =
        this.refreshTokenRepository
            .findByToken(token)
            .orElseThrow(() -> new BadCredentialsException("Invalid refresh token"));

    if (refreshToken.getExpiresAt().isBefore(Instant.now())) {
      this.refreshTokenRepository.delete(refreshToken);
      throw new BadCredentialsException("Refresh token expired");
    }

    return refreshToken;
  }
}
