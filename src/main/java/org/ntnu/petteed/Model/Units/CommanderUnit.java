package org.ntnu.petteed.Model.Units;

/**
 * Represents a unit of the Commander type
 *
 */
public class CommanderUnit extends CavalryUnit {

  private static final int ATTACK_VALUE = 25;
  private static final int ARMOUR_VALUE = 15;

  /**
   * Creates an instance of CommanderUnit
   *
   * @param name   The name of the unit
   * @param health The health of the unit
   */
  public CommanderUnit(String name, int health) {
    super(name, health, ATTACK_VALUE, ARMOUR_VALUE);
  }

}


