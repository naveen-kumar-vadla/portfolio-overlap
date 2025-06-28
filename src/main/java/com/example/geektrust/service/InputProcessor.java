package com.example.geektrust.service;

import com.example.geektrust.core.command.*;
import com.example.geektrust.exception.FundNotFound;
import com.example.geektrust.core.model.Fund;
import com.example.geektrust.core.model.Portfolio;
import com.example.geektrust.logger.Logger;

import java.util.Collections;
import java.util.List;

import static com.example.geektrust.AppConstants.*;

public class InputProcessor {
  private final FundManager fundManager;
  private final Portfolio portfolio;
  private final Logger logger;

  public InputProcessor(FundManager fundManager, Portfolio portfolio, Logger logger) {
    this.fundManager = fundManager;
    this.portfolio = portfolio;
    this.logger = logger;
  }

  public void processCommands(List<Command> commands) {
    for (Command command : commands) {
      CommandType commandType = command.getType();
      switch (commandType) {
        case CURRENT_PORTFOLIO:
          generatePortfolio((CurrentPortfolioCommand) command);
          break;
        case ADD_STOCK:
          addStockToFund((AddStockCommand) command);
          break;
        case CALCULATE_OVERLAP:
          calculateOverlap((CalculateOverlapCommand) command);
          break;
      }
    }
  }

  private void generatePortfolio(CurrentPortfolioCommand command) {
    command.getFunds().forEach(fundName -> {
      Fund fund = fundManager.getFundByName(fundName);
      portfolio.addFund(fund);
    });
  }

  private void addStockToFund(AddStockCommand command) {
    fundManager.addStock(command.getFundName(), command.getStockName());
  }

  private void calculateOverlap(CalculateOverlapCommand command) {
    List<String> res;
    try {
      Fund fund = fundManager.getFundByName(command.getFundName());
      res = portfolio.calculateOverlap(fund);
    } catch (FundNotFound e) {
      res = Collections.singletonList(FUND_NOT_FOUND);
    }
    logger.info(String.join(System.lineSeparator(), res));
  }
}
