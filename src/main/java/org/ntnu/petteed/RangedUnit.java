package org.ntnu.petteed;

public class RangedUnit extends Unit {

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

    int resistBonus = 2;

    if (receivedAttacks < 2) {
      resistBonus += 4 - (2 * receivedAttacks);
    }

    return resistBonus;
  }
}
