package com.example.geektrust;


import com.example.geektrust.core.command.Command;
import com.example.geektrust.dto.StockDataDTO;
import com.example.geektrust.core.model.Portfolio;
import com.example.geektrust.logger.Logger;
import com.example.geektrust.service.FundManager;
import com.example.geektrust.service.InputProcessor;
import com.example.geektrust.util.CommandsParser;
import com.example.geektrust.util.FileReader;

import java.util.List;

import static com.example.geektrust.AppConstants.ZERO;

public class Main {

  public static void main(String[] args) {
    FundManager fundManager = new FundManager();
    Portfolio portfolio = new Portfolio();
    Logger logger = new Logger();
    CommandsParser commandsParser = new CommandsParser();
    FileReader fileReader = new FileReader();

    try {
      String filePath = args.length > ZERO ? args[ZERO] : null;
      List<String> inputs = fileReader.readLines(filePath);
      List<Command> commands = commandsParser.parseCommands(inputs);

      String stocksFilePath = "stock_data.json";
      StockDataDTO data = fileReader.readStockDataFromResources(stocksFilePath);
      fundManager.loadFunds(data);

      InputProcessor inputProcessor = new InputProcessor(fundManager, portfolio, logger);
      inputProcessor.processCommands(commands);
    } catch (Exception e) {
      logger.error("An error occurred: " + e.getMessage());
    }
  }
}
