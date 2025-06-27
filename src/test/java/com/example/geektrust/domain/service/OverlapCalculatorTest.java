package com.example.geektrust.domain.service;

import com.example.geektrust.domain.model.Fund;
import com.example.geektrust.domain.model.Portfolio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OverlapCalculatorTest {
  private final OverlapCalculator overlapCalculator = new OverlapCalculator();
  private Portfolio portfolio;
  private final String fundAName = "fundA";
  private final String fundBName = "fundB";
  Fund fundA;

  @BeforeEach
  void setUp() {
    fundA = new Fund(fundAName);
    fundA.addStock("stock1");
    fundA.addStock("stock2");
    fundA.addStock("stock3");

    Fund fundB = new Fund(fundBName);
    fundB.addStock("stock1");
    fundB.addStock("stock2");

    Fund fundC = new Fund("fundB");

    portfolio = new Portfolio();
    portfolio.addFund(fundB);
    portfolio.addFund(fundC);
  }

  @Test
  void shouldCalculateOverlap() {
    List<String> overlap = overlapCalculator.calculate(fundA, portfolio);
    assertNotNull(overlap);
    assertFalse(overlap.isEmpty());
    assertEquals(1, overlap.size());
    assertEquals(fundAName + " " + fundBName + " 80.00%", overlap.get(0));
  }
}