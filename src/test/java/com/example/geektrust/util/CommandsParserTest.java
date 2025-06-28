package com.example.geektrust.util;

import com.example.geektrust.core.command.Command;
import com.example.geektrust.exception.UnknownCommandException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommandsParserTest {
  private final CommandsParser commandsParser = new CommandsParser();

  @Test
  void shouldParseValidCommands() {
    List<String> lines = Arrays.asList(
        "CURRENT_PORTFOLIO ICICI_PRU_NIFTY_NEXT_50_INDEX PARAG_PARIKH_CONSERVATIVE_HYBRID ICICI_PRU_BLUECHIP",
        "CALCULATE_OVERLAP AXIS_MIDCAP",
        "CALCULATE_OVERLAP SBI_LARGE_&_MIDCAP");
    List<Command> commands = commandsParser.parseCommands(lines);
    assertNotNull(commands);
    assertFalse(commands.isEmpty());
  }

  @Test
  void shouldThrowForUnknownCommand() {
    List<String> lines = Collections.singletonList("UNKNOWN_COMMAND PARAM1 PARAM2");
    assertThrows(UnknownCommandException.class, () -> commandsParser.parseCommands(lines));
  }
}