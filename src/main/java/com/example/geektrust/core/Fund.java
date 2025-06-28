package com.example.geektrust.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.example.geektrust.AppConstants.COMMON_STOCKS_MULTIPLIER;
import static com.example.geektrust.AppConstants.MAX_PERCENTAGE;

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

  public boolean hasStock(String stockName) {
    return stocks.stream().anyMatch(s -> Objects.equals(s.getName(), stockName));
  }

  private Long getCommonStocksCount(Fund other) {
    return this.stocks.stream()
        .filter(s -> other.stocks.contains(s)).count();
  }

  public Double overlapPercentage(Fund other) {
    Long commonStocksCount = this.getCommonStocksCount(other);
    return COMMON_STOCKS_MULTIPLIER * (commonStocksCount) / (this.stocks.size() + other.stocks.size()) * MAX_PERCENTAGE;
  }
}
