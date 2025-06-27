package com.example.geektrust;


import com.example.geektrust.domain.model.Portfolio;
import com.example.geektrust.domain.service.FundManager;
import com.example.geektrust.domain.service.InputProcessor;
import com.example.geektrust.domain.model.Fund;
import com.example.geektrust.util.FileReader;

import java.util.List;

import static com.example.geektrust.util.AppConstants.ZERO;

public class Main {
  public static void main(String[] args) {
    try {
      String filePath = args.length > ZERO ? args[ZERO] : null;
      List<List<String>> commands = FileReader.readCommands(filePath);

      String stocksFilePath = "stock_data.json";
      List<Fund> funds = FileReader.readStockDataFromResources(stocksFilePath);
      FundManager fundManager = new FundManager();
      fundManager.loadFunds(funds);

      Portfolio portfolio = new Portfolio();
      InputProcessor inputProcessor = new InputProcessor(fundManager, portfolio);
      List<String> result = inputProcessor.processCommands(commands);
      result.forEach(System.out::println);
    } catch (Exception e) {
      System.err.println("An error occurred: " + e.getMessage());
    }
  }
}
