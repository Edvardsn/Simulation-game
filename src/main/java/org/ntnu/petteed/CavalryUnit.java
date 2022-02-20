package org.ntnu.petteed;

public class CavalryUnit extends Unit {

  /**
   * Creates an instance of CavalryUnit
   *
   * @param name   The name of the unit
   * @param health The health of the unit
   */
  protected CavalryUnit(String name, int health) {
    super(name, health, 20, 12);
  }

  /**
   * Creates an instance of CavalryUnit
   *
   * @param name   The name of the unit
   * @param health The health of the unit
   * @param armour The armour of the unit
   * @param attack The attack of the unit
   */
  protected CavalryUnit(String name, int health, int attack, int armour) {
    super(name, health, attack, armour);
  }

  /**
   * Returns the bonus attack
   *
   * @return The bonus attack
   */
  @Override
  public int getAttackBonus() {

    int attackBonus = 2;

    if (initiatedAttacks < 1) {
      attackBonus += 4;
    }

    return attackBonus;
  }

  /**
   * Returns the bonus resistances
   *
   * @return The bonus resistance
   */
  @Override
  public int getResistBonus() {
    return 1;
  }
}
