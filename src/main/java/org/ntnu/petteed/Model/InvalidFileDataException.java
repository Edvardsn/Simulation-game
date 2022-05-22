package org.ntnu.petteed.Model;

import java.io.IOException;

/**
 * A InvalidInputException is an exception that indicates that invalid data has been
 * inside file used as an input.
 */
public class InvalidFileDataException extends IOException {

  /**
   * Constructs a InvalidInputException
   *
   * @param message The message with the details of the exception
   */
  public InvalidFileDataException(String message) {
  super(message);
  }
}
