package com.example.geektrust.core.command;

import com.example.geektrust.core.model.Fund;
import com.example.geektrust.core.model.OverlapResult;
import com.example.geektrust.core.model.Portfolio;
import com.example.geektrust.exception.FundNotFound;
import com.example.geektrust.logger.Logger;
import com.example.geektrust.service.FundManager;

import java.util.List;

import static com.example.geektrust.AppConstants.FUND_NOT_FOUND;
import static com.example.geektrust.AppConstants.INDEX_1;

public class CalculateOverlapCommand implements Command {
  final String fundName;

  public CalculateOverlapCommand(String fundName) {
    this.fundName = fundName;
  }

  public static Command create(List<String> params) {
    return new CalculateOverlapCommand(params.get(INDEX_1));
  }

  @Override
  public void execute(FundManager fundManager, Portfolio portfolio, Logger logger) {
    try {
      Fund fund = fundManager.getFundByName(fundName);
      List<OverlapResult> overlapResults = portfolio.calculateOverlaps(fund);
      overlapResults.forEach(logger::info);
    } catch (FundNotFound e) {
      logger.info(FUND_NOT_FOUND);
    }
  }
}
