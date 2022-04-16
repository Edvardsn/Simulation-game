package org.ntnu.petteed.Model;

/**
 * Represents a collection of effects which holds information about anything that can impact
 * the combat during battle or alter a unit's capabilities.
 *
 * @author Petter Edvardsen
 * @version 02/04/22
 */
public interface StatusEffect {

  /**
   * Applies the status effect to given unit
   *
   * @param unit The unit which the effect if applied to
   */
    void apply(Unit unit);


//  private Collection<String> currentEffects;
//
//  public StatusEffect() {
//    this.currentEffects = new HashSet<>();
//  }
//
//  public Collection<String> getCurrentEffects() {
//    return currentEffects;
//  }

  //  /**
//   * Returns the combat bonus of the terrain
//   *
//   * @return The combat bonus of the terrain
//   */
//  public String getTerrainEffect() {
//    return terrainEffect;
//  }

//
//  /**
//   * Returns a builder to generate status effects
//   *
//   * @return A builder to generate status effects
//   */
//  public static Builder getBuilder(){
//    return new Builder();
//  }
//
//  public static class Builder {
//
//    private String terrainEffect;
//    private String spellEffect;
//
//    /**
//     * Assigns the terrain effect in the builder
//     *
//     * @param terrainEffect The value of the combat bonus
//     * @return A builder object with field with given terrainCombatBonus
//     */
//    public Builder setTerrainEffect(String terrainEffect) {
//      this.terrainEffect = terrainEffect;
//      return this;
//    }
//
//    /**
//     * Assigns the spell effect in the builder
//     *
//     * @param spellEffect The spell effect to be assigned
//     * @return A builder object with specified spellEffect assigned
//     */
//    public Builder setSpellEffect(String spellEffect){
//      this.spellEffect = spellEffect;
//      return this;
//    }
//
//    /**
//     * Builds the final statusEffect object
//     *
//     * @return The final statusEffect object
//     */
//    public StatusEffect build(){
//      StatusEffect statusEffects = new StatusEffect();
//      statusEffects.terrainEffect = this.terrainEffect;
//      statusEffects.spellEffect = this.spellEffect;
//
//      return statusEffects;
//    }
//
//  }
}
