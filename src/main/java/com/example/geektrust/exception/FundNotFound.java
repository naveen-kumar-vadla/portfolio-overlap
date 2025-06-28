package com.example.geektrust.exception;

public class FundNotFound extends RuntimeException {
  public FundNotFound(String fundName) {
    super("Fund not found: " + fundName);
  }
}
