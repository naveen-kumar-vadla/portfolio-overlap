package com.example.geektrust.core.model;

import com.example.geektrust.AppConstants;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PortfolioTest {
  private final Portfolio portfolio = new Portfolio();

  @Test
  void shouldAddFund() {
    String fundName = "FundA";
    Fund fund = new Fund(fundName);
    portfolio.addFund(fund);
    assertTrue(portfolio.hasFund(fundName));
  }

  @Test
  void shouldGiveTrueWhenFundExists() {
    String fundName = "FundA";
    Fund fund = new Fund(fundName);
    portfolio.addFund(fund);
    assertTrue(portfolio.hasFund(fundName));
  }

  @Test
  void shouldGiveFalseWhenFundNotExists() {
    String nonExistentFund = "FundB";
    assertFalse(portfolio.hasFund(nonExistentFund));
  }

  @Test
  void shouldCalculateOverlapWhenCommonStocksPresent() {
    Fund fundA = new Fund("FundA");
    fundA.addStock("Stock1");
    fundA.addStock("Stock2");

    Fund fundB = new Fund("FundB");
    fundB.addStock("Stock2");
    fundB.addStock("Stock3");

    portfolio.addFund(fundB);

    List<String> overlaps = portfolio.calculateOverlap(fundA);
    assertEquals(1, overlaps.size());
    String expected = String.format(AppConstants.OVERLAP_PERCENTAGE_FORMAT, fundA.getName(), fundB.getName(), 50.0);
    assertEquals(expected, overlaps.get(0));
  }

  @Test
  void shouldNotCalculateOverlapWhenCommonStocksNotPresent() {
    Fund fundA = new Fund("FundA");
    fundA.addStock("Stock1");
    fundA.addStock("Stock2");

    Fund fundB = new Fund("FundB");
    fundB.addStock("Stock3");

    portfolio.addFund(fundB);

    List<String> overlaps = portfolio.calculateOverlap(fundA);
    assertEquals(0, overlaps.size());
  }
}