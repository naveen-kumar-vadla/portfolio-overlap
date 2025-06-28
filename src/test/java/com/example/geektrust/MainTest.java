package com.example.geektrust;

import com.example.geektrust.util.FileReader;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Paths;
import java.util.List;

import static junit.framework.Assert.assertEquals;

public class MainTest {
  private final PrintStream originalOut = System.out;
  private final PrintStream originalErr = System.err;
  private ByteArrayOutputStream outContent;
  private ByteArrayOutputStream errContent;

  @BeforeEach
  void setUpStreams() {
    outContent = new ByteArrayOutputStream();
    errContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    System.setErr(new PrintStream(errContent));
  }

  @AfterEach
  void restoreStreams() {
    System.setOut(originalOut);
    System.setErr(originalErr);
  }

  @ParameterizedTest
  @CsvSource(value = {
      "sample_input/input1.txt,sample_output/output1.txt",
      "sample_input/input2.txt,sample_output/output2.txt",
  })
  void testMainWithSampleInput(String inputPath, String outputPath) throws Exception {
    String inputFilePath = Paths.get(inputPath).toString();
    Main.main(new String[]{inputFilePath});
    String output = outContent.toString().trim();

    List<String> expectedResult = FileReader.readLines(outputPath);

    String expectedOutput = String.join("\n", expectedResult).trim();
    assertEquals(expectedOutput, output);
  }

  @Test
  void shouldLogIfAnyErrorOccur() {
    Main.main(new String[]{});
    String output = errContent.toString().trim();
    String expectedOutput = "An error occurred: File path cannot be null or empty";
    assertEquals(expectedOutput, output);
  }
}