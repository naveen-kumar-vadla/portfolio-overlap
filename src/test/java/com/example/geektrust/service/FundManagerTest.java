package com.example.geektrust.service;

import com.example.geektrust.dto.FundDTO;
import com.example.geektrust.dto.StockDataDTO;
import com.example.geektrust.exception.FundNotFound;
import com.example.geektrust.core.Fund;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FundManagerTest {
  private final FundManager fundManager = new FundManager();
  private final String fundAName = "FundA";
  private List<FundDTO> funds;

  @BeforeEach
  void setUp() {
    funds = new ArrayList<>();
  }

  @Test
  void shouldLoadAndGetFunds() {
    funds.add(new FundDTO(fundAName));

    String fundBName = "FundB";
    funds.add(new FundDTO(fundBName));

    String fundCName = "FundC";
    funds.add(new FundDTO(fundCName));

    StockDataDTO data = new StockDataDTO(funds);
    fundManager.loadFunds(data);
    assertNotNull(fundManager.getFundByName(fundAName));
    assertNotNull(fundManager.getFundByName(fundBName));
    assertNotNull(fundManager.getFundByName(fundCName));
  }

  @Test
  void shouldThrowForMissingFund() {
    StockDataDTO data = new StockDataDTO(funds);
    fundManager.loadFunds(data);
    assertThrows(FundNotFound.class, () -> fundManager.getFundByName(fundAName));
  }

  @Test
  void shouldAddStock() {
    funds.add(new FundDTO(fundAName));
    StockDataDTO data = new StockDataDTO(funds);

    fundManager.loadFunds(data);
    assertNotNull(fundManager.getFundByName(fundAName));

    String stockName = "StockX";
    fundManager.addStock(fundAName, stockName);
    Fund fund = fundManager.getFundByName(fundAName);
    assertTrue(fund.hasStock(stockName));
  }
}