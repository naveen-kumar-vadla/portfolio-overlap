package com.example.geektrust.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.example.geektrust.dto.StockDataDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import static com.example.geektrust.AppConstants.SPACE_DELIMITER;

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
        .map(l -> Arrays.asList(l.trim().split(SPACE_DELIMITER)))
        .collect(Collectors.toList());
  }

  private static void validateFilePath(String filePath) {
    if (filePath == null || filePath.isEmpty()) {
      throw new IllegalArgumentException("File path cannot be null or empty");
    }
  }

  public static StockDataDTO readStockDataFromResources(String resourcePath) throws Exception {
    validateFilePath(resourcePath);
    URL url = ClassLoader.getSystemResource(resourcePath);
    if (url == null) {
      throw new FileNotFoundException("Resource not found: " + resourcePath);
    }
    return new ObjectMapper().readValue(url, StockDataDTO.class);
  }
}
