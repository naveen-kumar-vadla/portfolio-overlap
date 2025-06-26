package com.example.geektrust.domain.service;

import com.example.geektrust.domain.exception.FundNotFound;
import com.example.geektrust.domain.model.Fund;
import com.example.geektrust.domain.model.Portfolio;
import com.example.geektrust.domain.model.Stock;

import java.util.*;

public class OverlapCalculator {
  public List<String> calculate(String fundName, Portfolio portfolio, FundManager fundManager) {
    try {
      Fund fund = fundManager.getFundByName(fundName);
      return calc(portfolio, fund);
    } catch (FundNotFound e) {
      return Collections.singletonList("FUND_NOT_FOUND");
    }
  }

  private List<String> calc(Portfolio portfolio, Fund fund) {
    List<String> result = new ArrayList<>();
    for (Fund portfolioFund : portfolio.getFunds()) {
      Double percentage = overlapPercentage(fund, portfolioFund);
      if (percentage <= 0.0) continue;
      String overLap = String.format("%s %s %.2f%%", fund.getName(), portfolioFund.getName(), percentage);
      result.add(overLap);
    }
    return result;
  }

  private Double overlapPercentage(Fund first, Fund second) {
    List<Stock> commonStocks = first.getCommonStocks(second);
    return 2.0 * (commonStocks.size()) / (first.getStocks().size() + second.getStocks().size()) * 100.0;
  }
}
