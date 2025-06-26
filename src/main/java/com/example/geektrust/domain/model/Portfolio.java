package com.example.geektrust.domain.model;

import java.util.ArrayList;
import java.util.List;

public class Portfolio {
  List<Fund> funds;

  public Portfolio() {
    this.funds = new ArrayList<>();
  }

  public void addFund(Fund fund) {
    this.funds.add(fund);
  }

  public List<Fund> getFunds() {
    return funds;
  }
}
