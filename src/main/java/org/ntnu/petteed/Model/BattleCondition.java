package org.ntnu.petteed.Model;

/**
 * Represents a battle condition which holds information about a battle. Anything that can impact
 * the combat during the battle and alter a unit's capabilities.
 *
 * @author Petter Edvardsen
 * @version 02/04/22
 */
public class BattleCondition {

  private String terrain;

  private BattleCondition() {
  }

  /**
   * Returns the combat bonus of the terrain
   *
   * @return The combat bonus of the terrain
   */
  public String getTerrain() {
    return terrain;
  }

  /**
   * Returns a builder to generate status effects
   *
   * @return A builder to generate status effects
   */
  public static Builder getBuilder(){
    return new Builder();
  }

  public static class Builder {

    private String terrain;

    /**
     * Assigns the terrain combat bonus in builder
     *
     * @param terrain The value of the combat bonus
     * @return A builder object with field with given terrainCombatBonus
     */
    public Builder setTerrain(String terrain) {
      this.terrain = terrain;
      return this;
    }

    /**
     * Builds the final statusEffect object
     *
     * @return The final statusEffect object
     */
    public BattleCondition build(){
      BattleCondition battleCondition = new BattleCondition();
      battleCondition.terrain = this.terrain;

      return battleCondition;
    }

  }
}
