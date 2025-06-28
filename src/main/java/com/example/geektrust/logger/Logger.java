package com.example.geektrust.logger;

public class Logger {
  public void info(Object object) {
    info(object.toString());
  }

  public void info(String message) {
    System.out.println(message);
  }

  public void error(String message) {
    System.err.println(message);
  }
}
