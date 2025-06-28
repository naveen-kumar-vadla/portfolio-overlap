package com.example.geektrust.service;

import com.example.geektrust.exception.FundNotFound;
import com.example.geektrust.core.model.Fund;
import com.example.geektrust.core.model.Portfolio;
import com.example.geektrust.logger.Logger;

import java.util.ArrayList;
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

  public void processCommands(List<List<String>> commands) {
    for (List<String> commandLine : commands) {
      String commandType = commandLine.get(ZERO);
      switch (commandType) {
        case CURRENT_PORTFOLIO:
          generatePortfolio(commandLine);
          break;
        case ADD_STOCK:
          addStockToFund(commandLine);
          break;
        case CALCULATE_OVERLAP:
          calculateOverlap(commandLine);
          break;
      }
    }
  }

  private void generatePortfolio(List<String> commandLine) {
    List<String> funds = commandLine.subList(INDEX_1, commandLine.size());
    funds.forEach(fundName -> {
      Fund fund = fundManager.getFundByName(fundName);
      portfolio.addFund(fund);
    });
  }

  private void addStockToFund(List<String> commandLine) {
    String fundName = commandLine.get(INDEX_1);
    String stockName = String.join(SPACE_DELIMITER, commandLine.subList(INDEX_2, commandLine.size()));
    fundManager.addStock(fundName, stockName);
  }

  private void calculateOverlap(List<String> commandLine) {
    String fundName = commandLine.get(INDEX_1);
    List<String> res;
    try {
      Fund fund = fundManager.getFundByName(fundName);
      res = portfolio.calculateOverlap(fund);
    } catch (FundNotFound e) {
      res = Collections.singletonList(FUND_NOT_FOUND);
    }
    logger.info(String.join(System.lineSeparator(), res));
  }
}
