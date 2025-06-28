package com.example.geektrust.core.command;

import com.example.geektrust.core.model.Portfolio;
import com.example.geektrust.logger.Logger;
import com.example.geektrust.service.FundManager;

public interface Command {
  void execute(FundManager fundManager, Portfolio portfolio, Logger logger);
}
