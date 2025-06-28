package com.example.geektrust.exception;

public class FundNotFound extends RuntimeException {
  String fundName;

  public FundNotFound(String fundName) {
    super("Fund not found: " + fundName);
    this.fundName = fundName;
  }
}
