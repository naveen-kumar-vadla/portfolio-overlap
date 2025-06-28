package com.example.geektrust.service;

import com.example.geektrust.exception.FundNotFound;
import com.example.geektrust.core.Fund;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FundManagerTest {
  private final FundManager fundManager = new FundManager();

  @Test
  void shouldLoadAndGetFunds() {
    String fundA = "FundA";
    String fundB = "FundB";
    String fundC = "FundC";

    List<Fund> funds = new ArrayList<>();
    funds.add(new Fund(fundA));
    funds.add(new Fund(fundB));
    funds.add(new Fund(fundC));

    fundManager.loadFunds(funds);
    assertNotNull(fundManager.getFundByName(fundA));
    assertNotNull(fundManager.getFundByName(fundB));
    assertNotNull(fundManager.getFundByName(fundC));
  }

  @Test
  void shouldThrowForMissingFund() {
    String fundA = "FundA";

    List<Fund> funds = new ArrayList<>();

    fundManager.loadFunds(funds);
    assertThrows(FundNotFound.class, () -> fundManager.getFundByName(fundA));
  }

  @Test
  void shouldAddStock() {
    String fundA = "FundA";

    List<Fund> funds = new ArrayList<>();
    Fund fund = new Fund(fundA);
    funds.add(fund);

    fundManager.loadFunds(funds);
    assertNotNull(fundManager.getFundByName(fundA));

    String stockName = "StockX";
    fundManager.addStock(fundA, stockName);
    assertTrue(fund.hasStock(stockName));
  }
}