package com.example.geektrust.core.model;

import com.example.geektrust.dto.FundDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.example.geektrust.AppConstants.COMMON_STOCKS_MULTIPLIER;
import static com.example.geektrust.AppConstants.MAX_PERCENTAGE;

public class Fund {
  final String name;
  final List<Stock> stocks;

  public Fund(String fundName) {
    this.name = fundName;
    this.stocks = new ArrayList<>();
  }

  public static Fund create(FundDTO fundDTO) {
    Fund fund = new Fund(fundDTO.getName());
    fundDTO.getStocks().forEach(fund::addStock);
    return fund;
  }

  public String getName() {
    return name;
  }

  public void addStock(String stockName) {
    Stock stock = new Stock(stockName);
    this.stocks.add(stock);
  }

  public boolean hasStock(String stockName) {
    return stocks.stream().anyMatch(stock -> Objects.equals(stock.getName(), stockName));
  }

  private List<Stock> getStocks() {
    return stocks;
  }

  private Long getCommonStocksCount(Fund other) {
    return this.getStocks().stream()
        .filter(other.getStocks()::contains).count();
  }

  public Double overlapPercentage(Fund other) {
    Long commonStocksCount = this.getCommonStocksCount(other);
    return COMMON_STOCKS_MULTIPLIER * (commonStocksCount) / (getStocks().size() + other.getStocks().size()) * MAX_PERCENTAGE;
  }
}
