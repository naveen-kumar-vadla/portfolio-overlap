package com.example.geektrust.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.example.geektrust.domain.model.Fund;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FileReader {
  public static List<List<String>> readCommands(String filePath) throws Exception {
    validateFilePath(filePath);

    List<String> lines = new ArrayList<>();
    FileInputStream fileInputStream = new FileInputStream(filePath);
    Scanner scanner = new Scanner(fileInputStream);
    while (scanner.hasNextLine()) {
      lines.add(scanner.nextLine());
    }

    return lines.stream()
        .map(l -> Arrays.asList(l.trim().split(" ")))
        .collect(Collectors.toList());
  }

  private static void validateFilePath(String filePath) {
    if (filePath == null || filePath.isEmpty()) {
      throw new IllegalArgumentException("File path cannot be null or empty");
    }
  }

  public static List<Fund> readStockDataFromResources(String resourcePath) throws Exception {
    validateFilePath(resourcePath);
    InputStream inputStream = ClassLoader.getSystemResourceAsStream(resourcePath);

    if (inputStream == null) {
      throw new FileNotFoundException("Resource not found: " + resourcePath);
    }

    JsonNode data = new ObjectMapper().readTree(inputStream);
    List<Fund> funds = new ArrayList<>();

    for (JsonNode fundNode : data.get("funds")) {
      Fund fund = parseFund(fundNode);
      funds.add(fund);
    }

    return funds;
  }

  private static Fund parseFund(JsonNode fundNode) {
    JsonNode fundName = fundNode.get("name");
    Fund fund = new Fund(fundName.asText());

    for (JsonNode stockName : fundNode.get("stocks")) {
      fund.addStock(stockName.asText());
    }

    return fund;
  }
}
