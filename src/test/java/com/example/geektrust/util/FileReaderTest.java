package com.example.geektrust.util;

import com.example.geektrust.dto.StockDataDTO;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileReaderTest {

  @Nested
  class ReadLinesTests {
    @Test
    void shouldReadCommands() throws Exception {
      String filePath = "sample_input/input1.txt";
      List<String> commands = FileReader.readLines(filePath);
      assertNotNull(commands);
      assertFalse(commands.isEmpty());
    }

    @Test
    void shouldThrowExceptionForInvalidEmptyFilePath() {
      String filePath = "";
      Exception exception = assertThrows(IllegalArgumentException.class, () -> FileReader.readLines(filePath));
      assertEquals("File path cannot be null or empty", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionForInvalidNullFilePath() {
      Exception exception = assertThrows(IllegalArgumentException.class, () -> FileReader.readLines(null));
      assertEquals("File path cannot be null or empty", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionForWhenFileNotFound() {
      String filePath = "sample_input/unknown.txt";
      Exception exception = assertThrows(FileNotFoundException.class, () -> FileReader.readLines(filePath));
      assertEquals(filePath + " (No such file or directory)", exception.getMessage());
    }
  }

  @Nested
  class ReadStockDataTests {
    @Test
    void shouldReadStocksData() throws Exception {
      String filePath = "stock_data.json";
      StockDataDTO data = FileReader.readStockDataFromResources(filePath);
      assertNotNull(data);
      assertFalse(data.getFunds().isEmpty());
    }

    @Test
    void shouldThrowExceptionForInvalidEmptyFilePath() {
      String filePath = "";
      Exception exception = assertThrows(IllegalArgumentException.class, () -> FileReader.readStockDataFromResources(filePath));
      assertEquals("File path cannot be null or empty", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionForInvalidNullFilePath() {
      Exception exception = assertThrows(IllegalArgumentException.class, () -> FileReader.readStockDataFromResources(null));
      assertEquals("File path cannot be null or empty", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionForWhenFileNotFound() {
      String filePath = "unknown.json";
      Exception exception = assertThrows(FileNotFoundException.class, () -> FileReader.readStockDataFromResources(filePath));
      assertEquals("Resource not found: " + filePath, exception.getMessage());
    }
  }
}