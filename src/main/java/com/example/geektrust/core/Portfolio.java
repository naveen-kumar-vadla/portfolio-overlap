package com.example.geektrust.core;

import java.util.ArrayList;
import java.util.List;

import static com.example.geektrust.AppConstants.OVERLAP_PERCENTAGE_FORMAT;
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

  public List<String> calculateOverlap(Fund fund) {
    List<String> overlaps = new ArrayList<>();
    for (Fund portfolioFund : funds) {
      Double percentage = fund.overlapPercentage(portfolioFund);
      if (percentage <= ZERO) continue;
      String overLap = String.format(OVERLAP_PERCENTAGE_FORMAT, fund.getName(), portfolioFund.getName(), percentage);
      overlaps.add(overLap);
    }
    return overlaps;
  }
}
