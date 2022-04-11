package org.ntnu.petteed.Model;

/**
 * Represents a unit of the Infantry type
 *
 */
public class InfantryUnit extends Unit {

  private final static int ATTACK_VALUE = 15;
  private final static int ARMOUR_VALUE = 10;
  private final static int BASE_ATTACK_BONUS = 2;
  private final static int BASE_RESIST_BONUS = 1;

  /**
   * Creates an instance of an infantry unit
   *
   * @param name, The name of the unit
   * @param health, The health of the unit
   */
  protected InfantryUnit(String name, int health) {
    super(name, health, ATTACK_VALUE, ARMOUR_VALUE);
  }

  /**
   * Returns the attack bonus of the InfantryUnit
   *
   * @return The attack bonus
   */
  @Override
  public int getAttackBonus() {
    int finalAttackBonus = BASE_ATTACK_BONUS;

    if(hasConditionEffect("FOREST")){
      finalAttackBonus += 2;
    }
    return finalAttackBonus;
  }

  /**
   * Returns the resistances' bonus of the InfantryUnit
   *
   * @return The resistance bonus
   */
  @Override
  public int getResistBonus() {
    int finalResistanceBonus = BASE_RESIST_BONUS;

    if(this.hasConditionEffect("FOREST")){
      finalResistanceBonus += 2;
    }
    return finalResistanceBonus;
  }
}
