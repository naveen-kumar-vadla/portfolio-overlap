package com.example.geektrust.domain.service;

import com.example.geektrust.domain.model.Fund;
import com.example.geektrust.domain.model.Portfolio;
import com.example.geektrust.domain.model.Stock;

import java.util.*;
import java.util.stream.Collectors;

public class OverlapCalculator {
  public List<String> calculate(Fund fund, Portfolio portfolio) {
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
    List<Stock> commonStocks = getCommonStocks(first, second);
    return 2.0 * (commonStocks.size()) / (first.getStocks().size() + second.getStocks().size()) * 100.0;
  }

  private List<Stock> getCommonStocks(Fund first, Fund second) {
    return first.getStocks().stream()
        .filter(s -> second.getStocks().contains(s))
        .collect(Collectors.toList());
  }
}
