package com.example.geektrust.domain.service;

import com.example.geektrust.domain.model.Fund;
import com.example.geektrust.domain.model.Portfolio;
import com.example.geektrust.domain.model.Stock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.example.geektrust.AppConstants.*;
import static org.junit.jupiter.api.Assertions.*;

class InputProcessorTest {
  private Portfolio portfolio;
  private final String fundAName = "fundA";
  private final String fundBName = "fundB";
  private Fund fundB;
  private InputProcessor inputProcessor;

  @BeforeEach
  void setUp() {
    Fund fundA = new Fund(fundAName);
    fundA.addStock("stock1");
    fundA.addStock("stock2");
    fundA.addStock("stock3");

    fundB = new Fund(fundBName);
    fundB.addStock("stock1");
    fundB.addStock("stock2");

    portfolio = new Portfolio();

    FundManager fundManager = new FundManager();
    fundManager.loadFunds(Arrays.asList(fundA, fundB));
    inputProcessor = new InputProcessor(fundManager, portfolio);
  }

  @Test
  void shouldProcessCurrentPortfolioCommand() {
    List<String> currentPortfolio = Arrays.asList(CURRENT_PORTFOLIO, fundBName);
    List<List<String>> commands = Collections.singletonList(currentPortfolio);
    inputProcessor.processCommands(commands);
    assertTrue(portfolio.getFunds().contains(fundB));
  }

  @Test
  void shouldProcessAddStockCommand() {
    String stock3 = "stock3";
    List<String> addStock = Arrays.asList(ADD_STOCK, fundBName, stock3);
    List<List<String>> commands = Collections.singletonList(addStock);
    inputProcessor.processCommands(commands);
    assertTrue(fundB.getStocks().contains(new Stock(stock3)));
  }

  @Test
  void shouldProcessCalculateOverlap() {
    List<String> currentPortfolio = Arrays.asList(CURRENT_PORTFOLIO, fundBName);
    List<String> calculateOverlap = Arrays.asList(CALCULATE_OVERLAP, fundAName);
    List<List<String>> commands = Arrays.asList(currentPortfolio, calculateOverlap);
    List<String> overlap = inputProcessor.processCommands(commands);
    assertNotNull(overlap);
    assertEquals(1, overlap.size());
    assertEquals(fundAName + SPACE_DELIMITER + fundBName + " 80.00%", overlap.get(0));
  }

  @Test
  void shouldProvideNotFoundForCalculateOverlapWhenFundNotFound() {
    List<String> currentPortfolio = Arrays.asList(CURRENT_PORTFOLIO, fundBName);
    List<String> calculateOverlap = Arrays.asList(CALCULATE_OVERLAP, "UNKNOWN");
    List<List<String>> commands = Arrays.asList(currentPortfolio, calculateOverlap);
    List<String> overlap = inputProcessor.processCommands(commands);
    assertNotNull(overlap);
    assertEquals(1, overlap.size());
    assertEquals(FUND_NOT_FOUND, overlap.get(0));
  }
}