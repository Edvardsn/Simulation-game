package org.ntnu.petteed;

public class RangedUnit extends Unit {

  private int numberOfTimesBeenAttacked;

  /**
   * Creates an instance of Unit
   *
   * @param name,   The name of the unit
   * @param health, The health of the unit
   */
  public RangedUnit(String name, int health) {
    super(name, health, 15, 8);
  }

  /**
   * Returns the attack bonus of the unit
   *
   * @return The attack bonus
   */
  @Override
  public int getAttackBonus() {
    return 3;
  }

  /**
   * Returns the resistances based on how many attacks the unit has sustained
   *
   * @return The resistance bonus
   */
  @Override
  public int getResistBonus() {

    int newResistBonus = 2;

    this.numberOfTimesBeenAttacked++;

    if (this.numberOfTimesBeenAttacked < 2) {
      newResistBonus = 6 - (this.numberOfTimesBeenAttacked * 2);
    }

    return newResistBonus;
  }
}
