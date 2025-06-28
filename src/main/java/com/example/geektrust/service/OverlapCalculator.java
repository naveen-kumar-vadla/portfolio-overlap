package com.example.geektrust.service;

import com.example.geektrust.core.Fund;
import com.example.geektrust.core.Portfolio;
import com.example.geektrust.core.Stock;

import java.util.*;
import java.util.stream.Collectors;

import static com.example.geektrust.AppConstants.*;

public class OverlapCalculator {
  public List<String> calculate(Fund fund, Portfolio portfolio) {
    List<String> result = new ArrayList<>();
    for (Fund portfolioFund : portfolio.getFunds()) {
      Double percentage = overlapPercentage(fund, portfolioFund);
      if (percentage <= ZERO) continue;
      String overLap = String.format(OVERLAP_PERCENTAGE_FORMAT, fund.getName(), portfolioFund.getName(), percentage);
      result.add(overLap);
    }
    return result;
  }

  private Double overlapPercentage(Fund first, Fund second) {
    List<Stock> commonStocks = getCommonStocks(first, second);
    return COMMON_STOCKS_MULTIPLIER * (commonStocks.size()) / (first.getStocks().size() + second.getStocks().size()) * MAX_PERCENTAGE;
  }

  private List<Stock> getCommonStocks(Fund first, Fund second) {
    return first.getStocks().stream()
        .filter(s -> second.getStocks().contains(s))
        .collect(Collectors.toList());
  }
}
