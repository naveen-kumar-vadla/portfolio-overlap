package com.example.geektrust.service;

import com.example.geektrust.dto.StockDataDTO;
import com.example.geektrust.exception.FundNotFound;
import com.example.geektrust.core.Fund;

import java.util.ArrayList;
import java.util.List;

public class FundManager {
  final List<Fund> funds;

  public FundManager() {
    this.funds = new ArrayList<>();
  }

  public Fund getFundByName(String fundName) {
    return this.funds.stream()
        .filter(f -> f.getName().equals(fundName))
        .findFirst()
        .orElseThrow(() -> new FundNotFound(fundName));
  }

  public void loadFunds(StockDataDTO stockData) {
    stockData.getFunds().stream().map(Fund::create).forEach(fund -> this.funds.add(fund));
  }

  public void addStock(String fundName, String stockName) {
    Fund fund = getFundByName(fundName);
    fund.addStock(stockName);
  }
}
