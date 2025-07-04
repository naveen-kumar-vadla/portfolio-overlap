package com.example.geektrust.core.model;

import java.util.ArrayList;
import java.util.List;

import static com.example.geektrust.AppConstants.ZERO;

public class Portfolio {
  final List<Fund> funds;

  public Portfolio() {
    this.funds = new ArrayList<>();
  }

  public void addFund(Fund fund) {
    this.funds.add(fund);
  }

  public boolean hasFund(String fundName) {
    return funds.stream().anyMatch(f -> f.getName().equals(fundName));
  }

  public List<OverlapResult> calculateOverlaps(Fund targetFund) {
    List<OverlapResult> overlapResults = new ArrayList<>();
    for (Fund portfolioFund : funds) {
      Double percentage = targetFund.overlapPercentage(portfolioFund);
      if (percentage <= ZERO) continue;
      OverlapResult overlapResult = new OverlapResult(targetFund.getName(), portfolioFund.getName(), percentage);
      overlapResults.add(overlapResult);
    }
    return overlapResults;
  }
}
