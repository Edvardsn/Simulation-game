package org.ntnu.petteed.Model.Units;

import java.util.Collection;
import org.ntnu.petteed.Model.Actor;
import org.ntnu.petteed.Model.Unit;

/**
 * Represents a unit of the Cavalry type
 *
 */
public class CavalryUnit extends Unit {

  private static final int ATTACK_VALUE = 20;
  private static final int ARMOUR_VALUE = 12;
  private static final int BASE_ATTACK_BONUS = 2;
  private static final int BASE_RESIST_BONUS = 1;

  /**
   * Creates an instance of CavalryUnit
   *
   * @param name   The name of the unit
   * @param health The health of the unit
   */
  public CavalryUnit(String name, int health) {
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
   * Acts on given target
   *
   * @param target The target to act upon
   */
  @Override
  public void act(Object target) {
    if(target instanceof Unit unit){
      this.attack(unit);
    }
  }

  /**
   * Returns the bonus attack
   *
   * @return The bonus attack
   */
  @Override
  public int getAttackBonus() {

    int finalAttackBonus = BASE_ATTACK_BONUS;

    if(occupiesTerrain("PLAINS")){
      finalAttackBonus += getCurrentTerrain().getSpecificTerrainCondition("PLAINS");
    }

    if (initiatedAttacks < 1) {
      finalAttackBonus += 4;
    }

    return finalAttackBonus;
  }

  /**
   * Returns the bonus resistances
   *
   * @return The bonus resistance
   */
  @Override
  public int getResistBonus() {
    int finalResistanceBonus = BASE_RESIST_BONUS;

    if(occupiesTerrain("FOREST")){
      finalResistanceBonus -= getCurrentTerrain().getSpecificTerrainCondition("FOREST");
    }
    return finalResistanceBonus;
  }

}
