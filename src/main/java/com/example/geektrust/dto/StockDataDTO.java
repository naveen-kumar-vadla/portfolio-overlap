package com.example.geektrust.dto;

import java.util.List;

public class StockDataDTO {
  List<FundDTO> funds;

  public StockDataDTO() {
  }

  public StockDataDTO(List<FundDTO> funds) {
    this.funds = funds;
  }

  public List<FundDTO> getFunds() {
    return funds;
  }
}
