package com.example.geektrust.domain.model;

import java.util.Objects;

public class Stock {
  String name;

  public Stock(String stockName) {
    this.name = stockName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Stock stock = (Stock) o;
    return name.equals(stock.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }
}
