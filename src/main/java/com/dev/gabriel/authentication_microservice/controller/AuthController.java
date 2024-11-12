package com.dev.gabriel.authentication_microservice.controller;

import com.dev.gabriel.authentication_microservice.controller.dto.LoginRequestDto;
import com.dev.gabriel.authentication_microservice.controller.dto.LoginResponseDto;
import com.dev.gabriel.authentication_microservice.controller.dto.RefreshTokenRequest;
import com.dev.gabriel.authentication_microservice.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authentication")
@RequiredArgsConstructor
public class AuthController {
  private final LoginService loginService;

  @PostMapping("/login")
  public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto request) {
    LoginResponseDto response = this.loginService.loginUser(request);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @PostMapping("/refresh")
  public ResponseEntity<LoginResponseDto> refreshToken(@RequestBody RefreshTokenRequest request) {
    LoginResponseDto response = this.loginService.refreshToken(request);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }
}
