package com.example.geektrust.core.model;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class StockTest {
  @Test
  void shouldGiveName() {
    String stockName = "ABC";
    Stock stock = new Stock(stockName);
    assertEquals(stockName, stock.getName());
  }

  @Test
  void shouldGiveTrueForEqual() {
    String stockName = "ABC";
    Stock stock1 = new Stock(stockName);
    Stock stock2 = new Stock(stockName);
    assertTrue(stock1.equals(stock1));
    assertTrue(stock1.equals(stock2));
  }

  @Test
  void shouldGiveFalseForNonEqual() {
    String stockName1 = "ABC";
    String stockName2 = "DEF";
    Stock stock1 = new Stock(stockName1);
    Stock stock2 = new Stock(stockName2);
    assertFalse(stock1.equals(stock2));
    assertFalse(stock1.equals(null));
    assertFalse(stock1.equals(stockName1));
  }

  @Test
  void shouldGiveNameAsUniqueHashcode() {
    String stockName = "ABC";
    Stock stock = new Stock(stockName);
    int hashCode = stock.hashCode();
    assertEquals(hashCode, Objects.hash(stockName));
  }
}