package com.example.geektrust.domain.service;

import com.example.geektrust.domain.exception.FundNotFound;
import com.example.geektrust.domain.model.Fund;

import java.util.ArrayList;
import java.util.List;

public class FundManager {
  List<Fund> funds;

  public FundManager() {
    this.funds = new ArrayList<>();
  }

  public Fund getFundByName(String fundName) {
    return this.funds.stream()
        .filter(f -> f.getName().equals(fundName))
        .findFirst()
        .orElseThrow(() -> new FundNotFound(fundName));
  }

  public void loadFunds(List<Fund> funds) {
    this.funds.addAll(funds);
  }
}
