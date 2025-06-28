package com.example.geektrust.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.example.geektrust.dto.StockDataDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FileReader {
  private static void validateFilePath(String filePath) {
    if (filePath == null || filePath.isEmpty()) {
      throw new IllegalArgumentException("File path cannot be null or empty");
    }
  }

  public static List<String> readLines(String filePath) throws Exception {
    validateFilePath(filePath);

    List<String> lines = new ArrayList<>();
    FileInputStream fileInputStream = new FileInputStream(filePath);
    Scanner scanner = new Scanner(fileInputStream);
    while (scanner.hasNextLine()) {
      lines.add(scanner.nextLine());
    }
    return lines;
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
