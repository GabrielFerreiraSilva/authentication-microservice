package com.dev.gabriel.authentication_microservice.repository;

import com.dev.gabriel.authentication_microservice.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {}
