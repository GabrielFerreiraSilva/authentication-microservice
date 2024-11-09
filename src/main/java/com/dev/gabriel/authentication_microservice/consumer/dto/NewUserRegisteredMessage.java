package com.dev.gabriel.authentication_microservice.consumer.dto;

public record NewUserRegisteredMessage(String id, String email, String password, String role) {}
