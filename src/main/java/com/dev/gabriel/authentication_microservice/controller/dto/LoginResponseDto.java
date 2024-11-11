package com.dev.gabriel.authentication_microservice.controller.dto;

import java.time.Instant;

public record LoginResponseDto(String token, Instant expiration, String refreshToken) {}
