package com.example.geektrust.core.model;

import com.example.geektrust.AppConstants;
import com.example.geektrust.logger.Logger;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

class PortfolioTest {
  public static final String fundAName = "FundA";
  public static final String fundBName = "FundB";
  public static final String stock1 = "Stock1";
  public static final String stock2 = "Stock2";
  public static final String stock3 = "Stock3";
  private final Portfolio portfolio = new Portfolio();
  private final Logger logger = Mockito.mock(Logger.class);

  @Test
  void shouldAddFund() {
    Fund fund = new Fund(fundAName);
    portfolio.addFund(fund);
    assertTrue(portfolio.hasFund(fundAName));
  }

  @Test
  void shouldGiveTrueWhenFundExists() {
    String fundName = fundAName;
    Fund fund = new Fund(fundName);
    portfolio.addFund(fund);
    assertTrue(portfolio.hasFund(fundName));
  }

  @Test
  void shouldGiveFalseWhenFundNotExists() {
    assertFalse(portfolio.hasFund(fundBName));
  }

  @Test
  void shouldCalculateAndLogOverlapWhenCommonStocksPresent() {
    Fund fundA = new Fund(fundAName);
    fundA.addStock(stock1);
    fundA.addStock(stock2);

    Fund fundB = new Fund(fundBName);
    fundB.addStock(stock2);
    fundB.addStock(stock3);

    portfolio.addFund(fundB);

    portfolio.calculateAndLogOverlap(fundA, logger);
    ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
    verify(logger, Mockito.times(1)).info(captor.capture());
    String overlap = captor.getValue();

    assertNotNull(overlap);
    double expectedPercentage = 50.0;
    String expected = String.format(AppConstants.OVERLAP_PERCENTAGE_FORMAT, fundA.getName(), fundB.getName(), expectedPercentage);
    assertEquals(expected, overlap);
  }

  @Test
  void shouldNotCalculateAndLogOverlapWhenCommonStocksNotPresent() {
    Fund fundA = new Fund(fundAName);
    fundA.addStock(stock1);
    fundA.addStock(stock2);

    Fund fundB = new Fund(fundBName);
    fundB.addStock(stock3);

    portfolio.addFund(fundB);

    portfolio.calculateAndLogOverlap(fundA, logger);
    verifyNoMoreInteractions(logger);
  }
}