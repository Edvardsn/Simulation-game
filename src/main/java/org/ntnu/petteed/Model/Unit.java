package org.ntnu.petteed.Model;

import java.util.Collection;

/**
 * A class that represents a single unit and all of its characteristics
 *
 * @author Student number
 * @version 03/05/22
 */
public abstract class Unit implements Actor {

  protected final String name;
  protected int health;
  protected final int attackValue;
  protected final int armour;
  protected int temporaryAttackValue = 0;
  protected int temporaryDefenseValue = 0;
  protected int receivedAttacks;
  protected int initiatedAttacks;
  protected boolean isAlive = true;

  Collection<Actor> friendlyActors = null;
  protected Terrain currentTerrain = null;
  public final EventManager eventManager;


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
    this.eventManager = new EventManager();
  }

  /**
   * Assigns a new value to the health of a unit
   *
   * @param newHealth, the new value of health
   */
  public void setHealth(int newHealth) {
    if (newHealth < 0) { // Could e.g be a death event
      this.health = 0;
      isAlive = false;

    } else if(newHealth < getHealth()){ // In the event that a unit loses health
      eventManager.notifyListeners(new HealthEvent(this));

      this.setHealth(newHealth);
    }
  }

  /**
   * Returns all actors which this unit considers friendly, or null if none.
   *
   * @return
   */
  public Collection<Actor> getFriendlyActors() {
    return friendlyActors;
  }

  public void setFriendlyActors(Collection<Actor> friendlyActors) {
    this.friendlyActors = friendlyActors;
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

      eventManager.notifyListeners(new ActionEvent(this));

      opponent.receiveAttack(new Attack(this.getTotalAttackDamage())); // endre navn

      this.incrementInitiatedAttacks(); // Registers initiated attack
    }
  }

  /**
   * Receives an attack from a source, can be overridden if chosen.
   *
   * @param attack The attack to receive
   */
  public void receiveAttack(Attack attack){
    int trueDamage = attack.damage() - getTotalResistances();

    if(trueDamage > 0) {
      this.setHealth(this.getHealth() - trueDamage);
    }

    incrementReceivedAttacks();
    // Attack dør her?
  }

  /**
   * Checks whether the unit is alive
   *
   * @return {@code Boolean} True if the unit is alive, false if not.
   */
  public boolean isAlive() {
    return this.isAlive;
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

  /**
   * Returns the value of the units conditional defense
   *
   * @return The value of the units conditional attack
   */
  public int getTemporaryAttackValue() {
    return temporaryAttackValue;
  }

  /**
   * Returns the value of the units conditional defense
   *
   * @return The value of the units conditional defense
   */
  public int getTemporaryDefenseValue() {
    return temporaryDefenseValue;
  }

  /**
   * Sets a new value for the conditional attack value of the unit
   *
   * @param temporaryAttackValue A new value for the conditional attack value of the unit
   */
  public void setTemporaryAttackValue(int temporaryAttackValue) {
    this.temporaryAttackValue = temporaryAttackValue;
  }

  /**
   * Sets a new value for the conditional defense of the unit
   *
   * @param temporaryDefenseValue A new value for the conditional defense of the unit
   */
  public void setTemporaryDefenseValue(int temporaryDefenseValue) {
    this.temporaryDefenseValue = temporaryDefenseValue;
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

  /**
   * Returns the current terrain a unit occupies
   *
   * @return The current terrain of the unit
   */
  public Terrain getCurrentTerrain() {
    return currentTerrain;
  }

  /**
   * Sets a new value for the currents terrain
   *
   * @param currentTerrain The new value of terrain
   */
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

  /**
   * Returns the total resistances of a unit
   *
   * @return The value of the total resistance
   */
  public int getTotalResistances() {
    int newTotalResistance = getArmour() + getResistBonus() + temporaryDefenseValue;

    temporaryDefenseValue = 0; // Resets the conditional defense value after every use

    return newTotalResistance;
  }

  /**
   * Returns the total attack value of the unit
   *
   * @return The total attack value of the unit
   */
  public int getTotalAttackDamage() {
    int newTotalAttackValue = getAttackValue() + this.getAttackBonus() + temporaryAttackValue;

    temporaryAttackValue = 0;// Resets the conditional attack value after every use

    return newTotalAttackValue;
  }
}
