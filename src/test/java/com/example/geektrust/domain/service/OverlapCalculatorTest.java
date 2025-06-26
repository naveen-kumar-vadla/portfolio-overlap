package com.example.geektrust.domain.service;

import com.example.geektrust.domain.model.Fund;
import com.example.geektrust.domain.model.Portfolio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OverlapCalculatorTest {
  private final OverlapCalculator overlapCalculator = new OverlapCalculator();
  private Portfolio portfolio;
  private FundManager fundManager;
  private final String fundAName = "fundA";
  private final String fundBName = "fundB";

  @BeforeEach
  void setUp() {
    Fund fundA = new Fund(fundAName);
    fundA.addStock("stock1");
    fundA.addStock("stock2");
    fundA.addStock("stock3");

    Fund fundB = new Fund(fundBName);
    fundB.addStock("stock1");
    fundB.addStock("stock2");

    portfolio = new Portfolio();
    portfolio.addFund(fundB);

    fundManager = new FundManager();
    fundManager.loadFunds(Arrays.asList(fundA, fundB));
  }

  @Test
  void shouldCalculateOverlap() {
    List<String> overlap = overlapCalculator.calculate(fundAName, portfolio, fundManager);
    assertNotNull(overlap);
    assertFalse(overlap.isEmpty());
    assertEquals(1, overlap.size());
    assertEquals(fundAName + " " + fundBName + " 80.00%", overlap.get(0));
  }

  @Test
  void shouldReturnFundNotFoundWhenFundDoesNotExist() {
    String nonExistentFundName = "nonExistentFund";
    List<String> overlap = overlapCalculator.calculate(nonExistentFundName, portfolio, fundManager);
    assertNotNull(overlap);
    assertEquals(1, overlap.size());
    assertEquals("FUND_NOT_FOUND", overlap.get(0));
  }
}