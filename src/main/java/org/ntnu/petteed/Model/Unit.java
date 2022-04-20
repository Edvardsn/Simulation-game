package org.ntnu.petteed.Model;

import java.util.HashSet;
import java.util.Set;
import org.reflections.Reflections;

/**
 * A class that represents a single unit and its characteristics
 *
 * @author Petter Edvardsen
 * @version 16/02/22
 */
public abstract class Unit {

  protected final String name;
  protected int health;
  protected final int attackValue;
  protected final int armour;
  protected Terrain currentTerrain = null;

  private int conditionalAttackValue = 0;
  private int conditionalDefenseValue = 0;
  private Set<StatusEffect> statusEffects;

  protected int receivedAttacks;
  protected int initiatedAttacks;

  /**
   * Creates an instance of Unit
   *
   * @param name,   The name of the unit
   * @param health, The health of the unit
   * @param attackValue, The attack value of the unit
   * @param armour, The armour value of the unit
   * @throws IllegalArgumentException If there are any invalid parameters
   */
  protected Unit(String name, int health, int attackValue, int armour) {
    if (name != null) {
      this.name = name;
    } else {
      throw new IllegalArgumentException("Name as null is not a valid name");
    }
    if (health < 0) {
      throw new IllegalArgumentException("Cannot create a unit with negative health");
    } else {
      this.health = health;
    }
    this.attackValue = attackValue;
    this.armour = armour;
    this.receivedAttacks = 0;
    this.initiatedAttacks = 0;
    this.statusEffects = new HashSet<>();
  }

  /**
   * Assigns a new value to the health of a unit
   *
   * @param newHealth, the new value of health
   */
  protected void setHealth(int newHealth, Object modifier) {

    boolean unit = modifier instanceof Unit;

    if (subTypes.stream().anyMatch(type -> type == modifier.getClass())) {
      if (newHealth > 0) {
        this.health = newHealth;
      }
      else {
        newHealth = 0;
      }
    }
  }

  /**
   * Increments the number of times an attack has been received
   */
  public void incrementReceivedAttacks() {
    this.receivedAttacks++;
  }

  /**
   * Increments the number of times an attack has been initiated
   */
  public void incrementInitiatedAttacks() {
    this.initiatedAttacks++;
  }

  /**
   * Attacks another unit
   *
   * @param opponent {@code Unit} opponent, the unit to attack
   */
  protected void attack(Unit opponent) {

    if (opponent != null && this.isAlive() && !(opponent.equals(this))) {

      statusEffects.forEach(statusEffect -> statusEffect.initiatesAction(this));

      int totalAttackDamage = this.getAttackValue() + this.getAttackBonus() + conditionalAttackValue;

      int totalResistances = opponent.getResistBonus() + opponent.getArmour() +
          conditionalDefenseValue;

      int trueDamage = totalAttackDamage - totalResistances; // The actual amount deducted from the opponents health

      opponent.setHealth(opponent.getHealth() - trueDamage,this);
      opponent.incrementReceivedAttacks();


      // Greit å si ifra

      this.incrementInitiatedAttacks(); // Registers initiated attack
    }
  }

  /**
   * Checks whether the unit is alive
   *
   * @return {@code Boolean} True if the unit is alive, false if not.
   */
  public boolean isAlive() {
    return this.getHealth() > 0;
  }

  /**
   * Returns the current battle conditions of the user
   *
   * @return The current battle conditions of the user
   */
  public Set<StatusEffect> getStatusEffects() {
    return this.statusEffects;
  }

  public void addStatusEffect(StatusEffect effect){
    this.statusEffects.add(effect);
  }

  public void removeStatusEffect(StatusEffect effect){
    // Modifyer?
    this.statusEffects.remove(effect);
  }

  /**
   * Checks if the unit is in given terrain
   *
   * @param terrainIdentity Identifier of the terrain to check
   * @return True if identity matches, false if not.
   */
  public boolean occupiesTerrain(String terrainIdentity){
    boolean occupiesTerrain = false;

    if(this.currentTerrain != null){
      occupiesTerrain = terrainIdentity.equals(getCurrentTerrain().getTerrainName());
    }

    return occupiesTerrain;
  }

  public int getConditionalAttackValue() {
    return conditionalAttackValue;
  }

  public int getConditionalDefenseValue() {
    return conditionalDefenseValue;
  }

  public void setConditionalAttackValue(int conditionalAttackValue) {
    this.conditionalAttackValue = conditionalAttackValue;
  }

  public void setConditionalDefenseValue(int conditionalDefenseValue) {
    this.conditionalDefenseValue = conditionalDefenseValue;
  }

  /**
   * Returns a description of the characteristics of the unit
   *
   * @return String, the details of the characteristics
   */
  @Override
  public String toString() {
    return "Unit{" + "name='" + name + '\'' + ", health=" + health + ", attack=" + attackValue +
        ", armour=" + armour + '}';
  }



  public Terrain getCurrentTerrain() {
    return currentTerrain;
  }

  public void setCurrentTerrain(Terrain currentTerrain) {
    this.currentTerrain = currentTerrain;
  }

  /**
   * Returns the name of the unit
   *
   * @return the name of the unit
   */
  public String getName() {
    return name;
  }

  /**
   * Returns the health of the unit
   *
   * @return the health of the unit
   */
  public int getHealth() {
    return health;
  }

  /**
   * Returns the attack of the unit
   *
   * @return the attack of the unit
   */
  public int getAttackValue() {
    return attackValue;
  }

  /**
   * Returns the armour of the unit
   *
   * @return the armour of the unit
   */
  public int getArmour() {
    return armour;
  }

  /**
   * Returns the attack bonus of the unit
   *
   * @return The attack bonus of the unit
   */
  public abstract int getAttackBonus();

  /**
   * Returns the resist bonus of the unit
   *
   * @return The resist bonus of the unit
   */
  public abstract int getResistBonus();

}
