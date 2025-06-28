package com.example.geektrust;


import com.example.geektrust.dto.StockDataDTO;
import com.example.geektrust.core.model.Portfolio;
import com.example.geektrust.service.FundManager;
import com.example.geektrust.service.InputProcessor;
import com.example.geektrust.util.FileReader;

import java.util.List;

import static com.example.geektrust.AppConstants.ZERO;

public class Main {
  public static void main(String[] args) {
    try {
      String filePath = args.length > ZERO ? args[ZERO] : null;
      List<List<String>> commands = FileReader.readCommands(filePath);

      String stocksFilePath = "stock_data.json";
      StockDataDTO data = FileReader.readStockDataFromResources(stocksFilePath);
      FundManager fundManager = new FundManager();
      fundManager.loadFunds(data);

      Portfolio portfolio = new Portfolio();
      InputProcessor inputProcessor = new InputProcessor(fundManager, portfolio);
      List<String> result = inputProcessor.processCommands(commands);
      result.forEach(System.out::println);
    } catch (Exception e) {
      System.err.println("An error occurred: " + e.getMessage());
    }
  }
}
