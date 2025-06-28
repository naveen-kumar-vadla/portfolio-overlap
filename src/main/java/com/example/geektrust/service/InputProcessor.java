package com.example.geektrust.service;

import com.example.geektrust.core.command.*;
import com.example.geektrust.exception.FundNotFound;
import com.example.geektrust.core.model.Fund;
import com.example.geektrust.core.model.Portfolio;
import com.example.geektrust.logger.Logger;

import java.util.Collections;
import java.util.List;

import static com.example.geektrust.AppConstants.*;

public class InputProcessor {
  private final FundManager fundManager;
  private final Portfolio portfolio;
  private final Logger logger;

  public InputProcessor(FundManager fundManager, Portfolio portfolio, Logger logger) {
    this.fundManager = fundManager;
    this.portfolio = portfolio;
    this.logger = logger;
  }

  public void processCommands(List<Command> commands) {
    commands.forEach(command -> command.execute(fundManager, portfolio, logger));
  }
}
