package com.example.geektrust.dto;

import java.util.ArrayList;
import java.util.List;

public class FundDTO {
  String name;
  List<String> stocks;

  public FundDTO() {
  }

  public FundDTO(String name) {
    this.name = name;
    this.stocks = new ArrayList<>();
  }

  public FundDTO(String name, List<String> stocks) {
    this.name = name;
    this.stocks = stocks;
  }

  public String getName() {
    return name;
  }

  public List<String> getStocks() {
    return stocks;
  }
}
