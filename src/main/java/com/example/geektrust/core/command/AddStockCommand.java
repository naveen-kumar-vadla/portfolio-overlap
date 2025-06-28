package com.example.geektrust.core.command;

public class AddStockCommand implements Command {
  final String fundName;
  final String stockName;

  public AddStockCommand(String fundName, String stockName) {
    this.fundName = fundName;
    this.stockName = stockName;
  }

  public String getFundName() {
    return fundName;
  }

  public String getStockName() {
    return stockName;
  }

  @Override
  public CommandType getType() {
    return CommandType.ADD_STOCK;
  }
}
