package org.ntnu.petteed;

public class InfantryUnit extends Unit {

  /**
   * Creates an instance of InfantryUnit
   *
   * @param name,   The name of the unit
   * @param health, The health of the unit
   */
  protected InfantryUnit(String name, int health) {
    super(name, health, 15, 10);
  }

  /**
   * Returns the attack bonus of the InfantryUnit
   *
   * @return The attack bonus
   */
  @Override
  public int getAttackBonus() {
    return 2;
  }

  /**
   * Returns the resistances' bonus of the InfantryUnit
   *
   * @return The resistance bonus
   */
  @Override
  public int getResistBonus() {
    return 1;
  }
}
