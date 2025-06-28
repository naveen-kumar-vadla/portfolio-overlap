package com.example.geektrust.core.command;

import com.example.geektrust.core.model.Fund;
import com.example.geektrust.core.model.Portfolio;
import com.example.geektrust.logger.Logger;
import com.example.geektrust.service.FundManager;

import java.util.List;

import static com.example.geektrust.AppConstants.INDEX_1;

public class CurrentPortfolioCommand implements Command {
  final List<String> funds;

  public CurrentPortfolioCommand(List<String> funds) {
    this.funds = funds;
  }

  public static Command create(List<String> params) {
    List<String> funds = params.subList(INDEX_1, params.size());
    return new CurrentPortfolioCommand(funds);
  }

  @Override
  public void execute(FundManager fundManager, Portfolio portfolio, Logger logger) {
    funds.forEach(fundName -> {
      Fund fund = fundManager.getFundByName(fundName);
      portfolio.addFund(fund);
    });
  }
}
