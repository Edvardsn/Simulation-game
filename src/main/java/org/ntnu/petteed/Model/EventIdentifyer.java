package org.ntnu.petteed.Model;

/**
 * Representations for all the valid command words for the game
 * along with a string in a particular language.
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */
public enum EventIdentifyer
{
  // A value for each command word along with its
  // corresponding user interface string.
  ATTACK("ATTACK");

  // The command string.
  private String commandString;

  /**
   * Initialise with the corresponding command string.
   * @param commandString The command string.
   */
  EventIdentifyer(String commandString)
  {
    this.commandString = commandString;
  }

  /**
   * @return The command word as a string.
   */
  public String toString()
  {
    return commandString;
  }
}

