package com.example.geektrust.domain.service;

import com.example.geektrust.domain.exception.FundNotFound;
import com.example.geektrust.domain.model.Fund;
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
}