package com.dev.gabriel.authentication_microservice.controller;

import com.dev.gabriel.authentication_microservice.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authentication")
@RequiredArgsConstructor
public class AuthController {
  private final LoginService loginService;
}
