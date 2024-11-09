package com.dev.gabriel.authentication_microservice.config;

import com.dev.gabriel.authentication_microservice.util.KeyUtils;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;

@Configuration
public class SecurityConfig {
  @Value("${security.private-key-path}")
  private String privateKeyPath;

  @Value("${security.public-key-path}")
  private String publicKeyPath;

  private RSAPrivateKey privateKey;
  private RSAPublicKey publicKey;

  @PostConstruct
  public void loadKeys() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
    this.privateKey = (RSAPrivateKey) KeyUtils.decodeKey(this.privateKeyPath);
    this.publicKey = (RSAPublicKey) KeyUtils.decodeKey(this.publicKeyPath);
  }
}
