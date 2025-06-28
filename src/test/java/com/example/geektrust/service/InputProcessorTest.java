package com.example.geektrust.service;

import com.example.geektrust.core.command.AddStockCommand;
import com.example.geektrust.core.command.CalculateOverlapCommand;
import com.example.geektrust.core.command.Command;
import com.example.geektrust.core.command.CurrentPortfolioCommand;
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
    CurrentPortfolioCommand currentPortfolio = new CurrentPortfolioCommand(Collections.singletonList(fundBName));
    List<Command> commands = Collections.singletonList(currentPortfolio);
    inputProcessor.processCommands(commands);
    assertTrue(portfolio.hasFund(fundBName));
  }

  @Test
  void shouldProcessAddStockCommand() {
    String stockName = "stockName";
    AddStockCommand addStock = new AddStockCommand(fundBName, stockName);
    List<Command> commands = Collections.singletonList(addStock);
    inputProcessor.processCommands(commands);
    assertTrue(fundManager.getFundByName(fundBName).hasStock(stockName));
  }

  @Test
  void shouldProcessCalculateOverlap() {
    CurrentPortfolioCommand currentPortfolio = new CurrentPortfolioCommand(Collections.singletonList(fundBName));
    CalculateOverlapCommand calculateOverlap = new CalculateOverlapCommand(fundAName);
    List<Command> commands = Arrays.asList(currentPortfolio, calculateOverlap);
    inputProcessor.processCommands(commands);
    ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
    verify(logger, Mockito.times(1)).info(captor.capture());
    String overlaps = captor.getValue().trim();
    assertNotNull(overlaps);
    assertEquals(fundAName + SPACE_DELIMITER + fundBName + " 80.00%", overlaps);
  }

  @Test
  void shouldProvideNotFoundForCalculateOverlapWhenFundNotFound() {
    CurrentPortfolioCommand currentPortfolio = new CurrentPortfolioCommand(Collections.singletonList(fundBName));
    CalculateOverlapCommand calculateOverlap = new CalculateOverlapCommand("UNKNOWN");
    List<Command> commands = Arrays.asList(currentPortfolio, calculateOverlap);
    inputProcessor.processCommands(commands);
    ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
    verify(logger, Mockito.times(1)).info(captor.capture());
    String overlaps = captor.getValue().trim();
    assertNotNull(overlaps);
    assertEquals(FUND_NOT_FOUND, overlaps);
  }
}