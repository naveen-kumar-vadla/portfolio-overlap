package com.example.geektrust.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Fund {
  String name;
  List<Stock> stocks;

  public Fund(String fundName) {
    this.name = fundName;
    this.stocks = new ArrayList<>();
  }

  public String getName() {
    return name;
  }

  public void addStock(String stockName) {
    Stock stock = new Stock(stockName);
    this.stocks.add(stock);
  }

  public List<Stock> getStocks() {
    return stocks;
  }

  public List<Stock> getCommonStocks(Fund anotherFund) {
    return getStocks().stream()
        .filter(stock -> anotherFund.getStocks().contains(stock))
        .collect(Collectors.toList());
  }
}
