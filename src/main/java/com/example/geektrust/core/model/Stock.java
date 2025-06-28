package com.example.geektrust.core.model;

import java.util.Objects;

public class Stock {
  final String name;

  public Stock(String stockName) {
    this.name = stockName;
  }

  public String getName() {
    return name;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) return true;
    if (other == null || getClass() != other.getClass()) return false;
    Stock stock = (Stock) other;
    return name.equals(stock.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }
}
