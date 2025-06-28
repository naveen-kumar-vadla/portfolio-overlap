package com.example.geektrust.service;

import com.example.geektrust.dto.FundDTO;
import com.example.geektrust.dto.StockDataDTO;
import com.example.geektrust.core.model.Portfolio;
import com.example.geektrust.logger.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.example.geektrust.AppConstants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class InputProcessorTest {
  private FundManager fundManager;
  private Portfolio portfolio;
  private final Logger logger = Mockito.mock(Logger.class);

  private final String fundAName = "fundA";
  private final String fundBName = "fundB";
  private InputProcessor inputProcessor;

  @BeforeEach
  void setUp() {
    FundDTO fundA = new FundDTO(fundAName, Arrays.asList("stock1", "stock2", "stock3"));
    FundDTO fundB = new FundDTO(fundBName, Arrays.asList("stock1", "stock2"));
    FundDTO fundC = new FundDTO("fundC");
    List<FundDTO> funds = Arrays.asList(fundA, fundB, fundC);
    StockDataDTO data = new StockDataDTO(funds);
    fundManager = new FundManager();
    portfolio = new Portfolio();
    fundManager.loadFunds(data);
    inputProcessor = new InputProcessor(fundManager, portfolio, logger);
  }

  @Test
  void shouldProcessCurrentPortfolioCommand() {
    List<String> currentPortfolio = Arrays.asList(CURRENT_PORTFOLIO, fundBName);
    List<List<String>> commands = Collections.singletonList(currentPortfolio);
    inputProcessor.processCommands(commands);
    assertTrue(portfolio.hasFund(fundBName));
  }

  @Test
  void shouldProcessAddStockCommand() {
    String stockName = "stockName";
    List<String> addStock = Arrays.asList(ADD_STOCK, fundBName, stockName);
    List<List<String>> commands = Collections.singletonList(addStock);
    inputProcessor.processCommands(commands);
    assertTrue(fundManager.getFundByName(fundBName).hasStock(stockName));
  }

  @Test
  void shouldProcessCalculateOverlap() {
    List<String> currentPortfolio = Arrays.asList(CURRENT_PORTFOLIO, fundBName);
    List<String> calculateOverlap = Arrays.asList(CALCULATE_OVERLAP, fundAName);
    List<List<String>> commands = Arrays.asList(currentPortfolio, calculateOverlap);
    inputProcessor.processCommands(commands);
    ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
    verify(logger, Mockito.times(1)).info(captor.capture());
    String overlaps = captor.getValue().trim();
    assertNotNull(overlaps);
    assertEquals(fundAName + SPACE_DELIMITER + fundBName + " 80.00%", overlaps);
  }

  @Test
  void shouldProvideNotFoundForCalculateOverlapWhenFundNotFound() {
    List<String> currentPortfolio = Arrays.asList(CURRENT_PORTFOLIO, fundBName);
    List<String> calculateOverlap = Arrays.asList(CALCULATE_OVERLAP, "UNKNOWN");
    List<List<String>> commands = Arrays.asList(currentPortfolio, calculateOverlap);
    inputProcessor.processCommands(commands);
    ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
    verify(logger, Mockito.times(1)).info(captor.capture());
    String overlaps = captor.getValue().trim();
    assertNotNull(overlaps);
    assertEquals(FUND_NOT_FOUND, overlaps);
  }
}