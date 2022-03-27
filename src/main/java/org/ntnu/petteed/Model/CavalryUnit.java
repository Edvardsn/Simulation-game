package org.ntnu.petteed.Model;

/**
 * Represents a unit of the Cavalry type
 *
 */
public class CavalryUnit extends Unit {

  private final static int ATTACK_VALUE = 20;
  private final static int ARMOUR_VALUE = 12;
  private final static int BASE_ATTACK_BONUS = 2;
  private final static int BASE_RESIST_BONUS = 1;

  /**
   * Creates an instance of CavalryUnit
   *
   * @param name   The name of the unit
   * @param health The health of the unit
   */
  protected CavalryUnit(String name, int health) {
    super(name, health, ATTACK_VALUE, ARMOUR_VALUE);
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

    int attackBonus = BASE_ATTACK_BONUS;

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
    return BASE_RESIST_BONUS;
  }
}