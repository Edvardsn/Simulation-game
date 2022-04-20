package org.ntnu.petteed.Model;

/**
 * Represents a unit of the Infantry type
 *
 */
public class InfantryUnit extends Unit {

  private static final int ATTACK_VALUE = 15;
  private static final int ARMOUR_VALUE = 10;
  private static final int BASE_ATTACK_BONUS = 2;
  private static final int BASE_RESIST_BONUS = 1;
  private static final String FOREST_TERRAIN_ID = "FOREST";


  /**
   * Creates an instance of an infantry unit
   *
   * @param name, The name of the unit
   * @param health, The health of the unit
   */
  public InfantryUnit(String name, int health) {
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

    if(occupiesTerrain(FOREST_TERRAIN_ID)){
      finalAttackBonus += getCurrentTerrain().getSpecificTerrainCondition(FOREST_TERRAIN_ID);
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

    if(occupiesTerrain(FOREST_TERRAIN_ID)){
      finalResistanceBonus += getCurrentTerrain().getSpecificTerrainCondition(FOREST_TERRAIN_ID);
    }
    return finalResistanceBonus;
  }
}
