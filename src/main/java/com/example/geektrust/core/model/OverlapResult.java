package com.example.geektrust.core.model;

import static com.example.geektrust.AppConstants.OVERLAP_PERCENTAGE_FORMAT;

public class OverlapResult {
  private final String fundName;
  private final String targetFundName;
  private final double overlapPercentage;

  public OverlapResult(String fundName, String targetFundName, double overlapPercentage) {
    this.fundName = fundName;
    this.targetFundName = targetFundName;
    this.overlapPercentage = overlapPercentage;
  }

  @Override
  public String toString() {
    return String.format(OVERLAP_PERCENTAGE_FORMAT, fundName, targetFundName, overlapPercentage);
  }
}
