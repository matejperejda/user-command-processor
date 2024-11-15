package com.example.processor.domain.entity;

import org.immutables.value.Value;

import java.util.UUID;

@Value.Immutable
public interface User {

  @Value.Parameter
  UUID id();

  @Value.Parameter
  String guid();

  @Value.Parameter
  String name();

  @Value.Check
  default User check() {
    if (name().trim().isEmpty()) {
      throw new IllegalArgumentException("Name cannot be empty.");
    }
    return this;
  }
}
