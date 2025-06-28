package com.example.geektrust.core.model;

import com.example.geektrust.logger.Logger;

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

  public void calculateAndLogOverlap(Fund fund, Logger logger) {
    for (Fund portfolioFund : funds) {
      Double percentage = fund.overlapPercentage(portfolioFund);
      logOverLapIfApplicable(fund, portfolioFund, percentage, logger);
    }
  }

  private static void logOverLapIfApplicable(Fund fund, Fund portfolioFund, Double percentage, Logger logger) {
    if (percentage <= ZERO) return;
    String overLap = String.format(OVERLAP_PERCENTAGE_FORMAT, fund.getName(), portfolioFund.getName(), percentage);
    logger.info(overLap);
  }
}
