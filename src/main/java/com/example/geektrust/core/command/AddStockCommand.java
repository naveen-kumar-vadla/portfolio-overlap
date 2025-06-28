package com.example.geektrust.core.command;

import com.example.geektrust.core.model.Portfolio;
import com.example.geektrust.logger.Logger;
import com.example.geektrust.service.FundManager;

import java.util.List;

import static com.example.geektrust.AppConstants.*;

public class AddStockCommand implements Command {
  final String fundName;
  final String stockName;

  public AddStockCommand(String fundName, String stockName) {
    this.fundName = fundName;
    this.stockName = stockName;
  }

  public static Command create(List<String> params) {
    String stockName = String.join(SPACE_DELIMITER, params.subList(INDEX_2, params.size()));
    return new AddStockCommand(params.get(INDEX_1), stockName);
  }

  @Override
  public void execute(FundManager fundManager, Portfolio portfolio, Logger logger) {
    fundManager.addStock(fundName, stockName);
  }
}
