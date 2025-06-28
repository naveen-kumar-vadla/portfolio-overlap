package com.example.geektrust.core.command;

import java.util.List;

public class CurrentPortfolioCommand implements Command {
  final List<String> funds;

  public List<String> getFunds() {
    return funds;
  }

  public CurrentPortfolioCommand(List<String> funds) {
    this.funds = funds;
  }

  @Override
  public CommandType getType() {
    return CommandType.CURRENT_PORTFOLIO;
  }
}

