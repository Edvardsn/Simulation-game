package org.ntnu.petteed.Model.Units;

import org.ntnu.petteed.Model.Unit;

/**
 * Represents a unit of the Ranged type
 *
 */
public class RangedUnit extends Unit {

  private final static int ATTACK_VALUE = 15;
  private final static int ARMOUR_VALUE = 8;
  private final static int BASE_ATTACK_BONUS = 3;
  private final static int BASE_RESIST_BONUS = 2;


  /**
   * Creates an instance of Unit
   *
   * @param name,   The name of the unit
   * @param health, The health of the unit
   */
  public RangedUnit(String name, int health) {
    super(name, health, ATTACK_VALUE, ARMOUR_VALUE);
  }

  /**
   * Returns the attack bonus of the unit
   *
   * @return The attack bonus
   */
  @Override
  public int getAttackBonus() {
    int finalAttackBonus = BASE_ATTACK_BONUS;

    if(occupiesTerrain("FOREST")){
      finalAttackBonus -= getCurrentTerrain().getSpecificTerrainCondition("FOREST");
    }

    if(occupiesTerrain("HILLS")){
      finalAttackBonus += getCurrentTerrain().getSpecificTerrainCondition("HILLS");
    }

    return finalAttackBonus;
  }

  /**
   * Returns the resistances based on how many attacks the unit has sustained
   *
   * @return The resistance bonus
   */
  @Override
  public int getResistBonus() {

    int resistBonus = BASE_RESIST_BONUS;

    if (receivedAttacks < 2) {
      resistBonus += 4 - (2 * receivedAttacks);
    }

    return resistBonus;
  }

}
