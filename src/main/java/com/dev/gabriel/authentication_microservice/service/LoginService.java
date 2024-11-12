package com.dev.gabriel.authentication_microservice.service;

import com.dev.gabriel.authentication_microservice.controller.dto.LoginRequestDto;
import com.dev.gabriel.authentication_microservice.controller.dto.LoginResponseDto;
import com.dev.gabriel.authentication_microservice.controller.dto.RefreshTokenRequest;
import com.dev.gabriel.authentication_microservice.model.entity.RefreshToken;
import com.dev.gabriel.authentication_microservice.model.entity.User;
import com.dev.gabriel.authentication_microservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class LoginService {
  private final JwtEncoder jwtEncoder;
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final RefreshTokenService refreshTokenService;

  @Value("${token.issuer}")
  private String tokenIssuer;

  @Value("${token.duration.seconds}")
  private Long tokenDurationSeconds;

  public LoginResponseDto loginUser(LoginRequestDto request) {
    User user =
        this.userRepository
            .findByEmail(request.email())
            .orElseThrow(() -> new BadCredentialsException("User not found"));

    if (Boolean.TRUE.equals(user.getIsBlocked())) {
      throw new LockedException("User blocked");
    }

    if (!this.passwordEncoder.matches(request.password(), user.getPassword())) {
      throw new BadCredentialsException("Wrong password");
    }

    JwtClaimsSet claims =
        JwtClaimsSet.builder()
            .issuer(this.tokenIssuer)
            .subject(user.getId().toString())
            .expiresAt(Instant.now().plusSeconds(this.tokenDurationSeconds))
            .issuedAt(Instant.now())
            .claim("role", user.getRole())
            .build();

    Jwt jwt = jwtEncoder.encode(JwtEncoderParameters.from(claims));
    String refreshToken = this.refreshTokenService.createRefreshToken(user);

    return new LoginResponseDto(jwt.getTokenValue(), jwt.getExpiresAt(), refreshToken);
  }

  public LoginResponseDto refreshToken(RefreshTokenRequest request) {
    RefreshToken validatedRefreshToken =
        this.refreshTokenService.validateRefreshToken(request.refreshToken());
    User user = validatedRefreshToken.getUser();

    JwtClaimsSet claims =
        JwtClaimsSet.builder()
            .issuer(this.tokenIssuer)
            .subject(user.getId().toString())
            .expiresAt(Instant.now().plusSeconds(this.tokenDurationSeconds))
            .issuedAt(Instant.now())
            .claim("role", user.getRole())
            .build();

    Jwt jwt = jwtEncoder.encode(JwtEncoderParameters.from(claims));
    return new LoginResponseDto(jwt.getTokenValue(), jwt.getExpiresAt(), request.refreshToken());
  }
}
