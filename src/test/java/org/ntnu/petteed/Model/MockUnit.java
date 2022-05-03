package org.ntnu.petteed.Model;

public class MockUnit extends Unit{

  /**
   * Creates an instance of Unit
   *
   * @param name
   * @param health
   * @param attackValue
   * @param armour
   * @throws IllegalArgumentException If there are any invalid parameters
   */
  protected MockUnit(String name, int health, int attackValue, int armour) {
    super(name, health, attackValue, armour);
  }

  /**
   * Returns the attack bonus of the unit
   *
   * @return The attack bonus of the unit
   */
  @Override
  public int getAttackBonus() {
    return 5;
  }

  /**
   * Returns the resist bonus of the unit
   *
   * @return The resist bonus of the unit
   */
  @Override
  public int getResistBonus() {
    return 5;
  }
}
