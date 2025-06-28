package com.example.geektrust.util;

import com.example.geektrust.AppConstants;
import com.example.geektrust.core.command.AddStockCommand;
import com.example.geektrust.core.command.CalculateOverlapCommand;
import com.example.geektrust.core.command.Command;
import com.example.geektrust.core.command.CurrentPortfolioCommand;
import com.example.geektrust.exception.UnknownCommandException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.geektrust.AppConstants.*;

public class CommandsParser {
  public List<Command> parseCommands(List<String> inputLines) {
    List<Command> commands = new ArrayList<>();
    for (String line : inputLines) {
      List<String> params = Arrays.asList(line.trim().split(AppConstants.SPACE_DELIMITER));
      commands.add(extractCommand(params));
    }
    return commands;
  }

  private Command extractCommand(List<String> params) {
    String commandType = params.get(ZERO);
    switch (commandType) {
      case ADD_STOCK:
        return AddStockCommand.create(params);
      case CALCULATE_OVERLAP:
        return CalculateOverlapCommand.create(params);
      case CURRENT_PORTFOLIO:
        return CurrentPortfolioCommand.create(params);
      default:
        throw new UnknownCommandException(commandType);
    }
  }
}
