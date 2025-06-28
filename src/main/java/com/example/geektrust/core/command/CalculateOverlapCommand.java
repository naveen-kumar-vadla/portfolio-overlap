package com.example.geektrust.core.command;

public class CalculateOverlapCommand implements Command {
  final String fundName;

  public CalculateOverlapCommand(String fundName) {
    this.fundName = fundName;
  }

  public String getFundName() {
    return fundName;
  }

  @Override
  public CommandType getType() {
    return CommandType.CALCULATE_OVERLAP;
  }
}
