package com.example.geektrust.util;

import com.example.geektrust.core.Fund;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileReaderTest {

  @Nested
  class ReadCommandsTests {
    @Test
    void shouldReadCommands() throws Exception {
      String filePath = "sample_input/input1.txt";
      List<List<String>> commands = FileReader.readCommands(filePath);
      assertNotNull(commands);
      assertFalse(commands.isEmpty());
    }

    @Test
    void shouldThrowExceptionForInvalidEmptyFilePath() {
      String filePath = "";
      Exception exception = assertThrows(IllegalArgumentException.class, () -> FileReader.readCommands(filePath));
      assertEquals("File path cannot be null or empty", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionForInvalidNullFilePath() {
      String filePath = null;
      Exception exception = assertThrows(IllegalArgumentException.class, () -> FileReader.readCommands(filePath));
      assertEquals("File path cannot be null or empty", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionForWhenFileNotFound() {
      String filePath = "sample_input/unknown.txt";
      Exception exception = assertThrows(FileNotFoundException.class, () -> FileReader.readCommands(filePath));
      assertEquals(filePath + " (No such file or directory)", exception.getMessage());
    }
  }

  @Nested
  class ReadStockDataTests {
    @Test
    void shouldReadStocksData() throws Exception {
      String filePath = "stock_data.json";
      List<Fund> funds = FileReader.readStockDataFromResources(filePath);
      assertNotNull(funds);
      assertFalse(funds.isEmpty());
    }

    @Test
    void shouldThrowExceptionForInvalidEmptyFilePath() {
      String filePath = "";
      Exception exception = assertThrows(IllegalArgumentException.class, () -> FileReader.readStockDataFromResources(filePath));
      assertEquals("File path cannot be null or empty", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionForInvalidNullFilePath() {
      String filePath = null;
      Exception exception = assertThrows(IllegalArgumentException.class, () -> FileReader.readStockDataFromResources(filePath));
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