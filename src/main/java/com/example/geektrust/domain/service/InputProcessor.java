package com.example.geektrust.domain.service;

import com.example.geektrust.domain.exception.FundNotFound;
import com.example.geektrust.domain.model.Fund;
import com.example.geektrust.domain.model.Portfolio;

import java.util.ArrayList;
import java.util.List;

import static com.example.geektrust.util.AppConstants.*;

public class InputProcessor {
  private final FundManager fundManager;
  private final Portfolio portfolio;
  private final OverlapCalculator overlapCalculator;
  private final List<String> result;

  public InputProcessor(FundManager fundManager, Portfolio portfolio) {
    this.fundManager = fundManager;
    this.portfolio = portfolio;
    this.overlapCalculator = new OverlapCalculator();
    this.result = new ArrayList<>();
  }

  public List<String> processCommands(List<List<String>> commands) {
    for (List<String> commandLine : commands) {
      String commandType = commandLine.get(COMMAND_INDEX);
      switch (commandType) {
        case "CURRENT_PORTFOLIO":
          generatePortfolio(commandLine);
          break;
        case "ADD_STOCK":
          addStockToFund(commandLine);
          break;
        case "CALCULATE_OVERLAP":
          calculateOverlap(commandLine);
          break;
      }
    }

    return result;
  }

  private void generatePortfolio(List<String> commandLine) {
    List<String> funds = commandLine.subList(FUND_NAME_INDEX, commandLine.size());
    funds.forEach(fundName -> {
      Fund fund = fundManager.getFundByName(fundName);
      portfolio.addFund(fund);
    });
  }

  private void addStockToFund(List<String> commandLine) {
    String fundName = commandLine.get(FUND_NAME_INDEX);
    String stockName = String.join(" ", commandLine.subList(STOCK_NAME_INDEX, commandLine.size()));
    Fund fund = fundManager.getFundByName(fundName);
    fund.addStock(stockName);
  }

  private void calculateOverlap(List<String> commandLine) {
    String fundName = commandLine.get(FUND_NAME_INDEX);
    try {
      Fund fund = fundManager.getFundByName(fundName);
      this.result.addAll(overlapCalculator.calculate(fund, portfolio));
    } catch (FundNotFound e) {
      this.result.add("FUND_NOT_FOUND");
    }
  }
}
