package org.ntnu.petteed.Model;

import java.util.Objects;
import org.ntnu.petteed.Model.EventMechanics.ActionEvent;
import org.ntnu.petteed.Model.EventMechanics.EventManager;
import org.ntnu.petteed.Model.EventMechanics.HealthEvent;

/**
 * A class that represents a single unit which is an actor that engages in combat
 *
 * @author Nr. 10049
 * @version 21/05/22
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

  protected Army currentArmy = null;
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
    if (name == null) {
      throw new IllegalArgumentException("Name as null is not a valid name");
    }
    if (health < 0) {
      throw new IllegalArgumentException("Cannot create a unit with negative health");
    }
    if(health == 0){
      this.isAlive = false;
    }
    if(attackValue < 0){
      throw new IllegalArgumentException("Cannot create a unit with negative attack value");
    }
    if(armour < 0){
      throw new IllegalArgumentException("Cannot create a unit with negative armour value");
    }

    this.name = name;
    this.health = health;
    this.attackValue = attackValue;
    this.armour = armour;
    this.receivedAttacks = 0;
    this.initiatedAttacks = 0;
    this.eventManager = new EventManager();
  }

  /**
   * Creates a copy of given unit
   *
   * @param unit The unit to copy
   */
  protected Unit(Unit unit){
    this.name = unit.getName();
    this.health = unit.getHealth();
    this.attackValue = unit.getAttackValue();
    this.armour = unit.getArmour();
    this.temporaryAttackValue = unit.getTemporaryAttackValue();
    this.temporaryDefenseValue = unit.getTemporaryDefenseValue();
    this.receivedAttacks = unit.getReceivedAttacks();
    this.initiatedAttacks = unit.getInitiatedAttacks();
    this.currentArmy = unit.getCurrentArmy();
    this.eventManager = unit.getEventManager();
    this.isAlive = unit.isAlive();
    this.currentTerrain = unit.getCurrentTerrain();
  }

  /**
   * The default action of any unit
   *
   * @param target Optional target for actions
   */
  @Override
  public void act(Object target) {
    eventManager.notifyListeners(new ActionEvent(this));

    if(target instanceof Unit unit){
      attack(unit);
    }
  }

  /**
   * Assigns a new value to the health of a unit
   *
   * @param newHealth, the new value of health
   */
  public void setHealth(int newHealth) {

    eventManager.notifyListeners(new HealthEvent(this));

    if (newHealth < 0 || newHealth == 0) {
      this.health = 0;
      isAlive = false;
    }else {
      this.health = newHealth;
    }
  }


  /**
   * Returns the army the unit is member of, null if no current army.
   *
   * @param currentArmy The army to be set as current army
   */
  public void setCurrentArmy(Army currentArmy) {
    this.currentArmy = currentArmy;
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

    if (opponent != null && this.isAlive() && !(opponent.equals(this))){

      eventManager.notifyListeners(new ActionEvent(this));

      opponent.receiveAttack(new Attack(this.getTotalAttackDamage()));

      this.resetConditionalAttack();

      incrementInitiatedAttacks(); // Registers initiated attack
    }
  }

  /**
   * Receives an attack from a source
   *
   * @param attack The attack to receive
   */
  public void receiveAttack(Attack attack){
    if(attack != null){
      int trueDamage = attack.damage() - getTotalResistances();

      if(trueDamage > 0) {
        this.setHealth(this.getHealth() - trueDamage);
      }

      this.resetConditionalDefense();

      incrementReceivedAttacks(); // Registers received attack
    }
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
   * Returns the value of the units conditional attack
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
   * Resets the conditional combat values of the unit
   *
   */
  private void resetConditionalAttack() {
    this.temporaryAttackValue = 0;
  }

  /**
   * Resets the conditional defensive values of the unit
   *
   */
  private void resetConditionalDefense() {
    this.temporaryDefenseValue = 0;
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
    if(currentTerrain != null){
      this.currentTerrain = currentTerrain;
    }
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

    return newTotalResistance;
  }

  /**
   * Returns the total attack value of the unit
   *
   * @return The total attack value of the unit
   */
  public int getTotalAttackDamage() {
    int newTotalAttackValue = getAttackValue() + this.getAttackBonus() + temporaryAttackValue;

    return newTotalAttackValue;
  }

  /**
   * Returns the number of recivedAttacks
   *
   * @return The number of recivedAttacks
   */
  public int getReceivedAttacks() {
    return receivedAttacks;
  }

  /**
   * Returns the number of initiatedAttacks
   *
   * @return The number of initiatedAttacks
   */
  public int getInitiatedAttacks() {
    return initiatedAttacks;
  }

  /**
   * Returns the current army of the unit
   *
   * @return The current army of the unit
   */
  public Army getCurrentArmy() {
    return currentArmy;
  }

  /**
   * Returns the eventmanager of the unit
   *
   * @return The eventmanager of the unit
   */
  public EventManager getEventManager() {
    return eventManager;
  }

  /**
   * Checks if an object is equal to this
   *
   * @param o The object to check
   * @return True if the same object, false if not.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Unit)) {
      return false;
    }
    Unit unit = (Unit) o;
    return getHealth() == unit.getHealth() && getAttackValue() == unit.getAttackValue() &&
        getArmour() == unit.getArmour() &&
        getTemporaryAttackValue() == unit.getTemporaryAttackValue() &&
        getTemporaryDefenseValue() == unit.getTemporaryDefenseValue() &&
        getReceivedAttacks() == unit.getReceivedAttacks() &&
        getInitiatedAttacks() == unit.getInitiatedAttacks() && isAlive() == unit.isAlive() &&
        getName().equals(unit.getName()) &&
        (getCurrentArmy() == unit.getCurrentArmy()) &&
        Objects.equals(getCurrentTerrain(), unit.getCurrentTerrain()) &&
        getEventManager().equals(unit.getEventManager());
  }

  /**
   * Returns a hascode of the Unit
   *
   * @return A hascode of the Unit
   */
  @Override
  public int hashCode() {
    return Objects.hash(getName(), getHealth(), getAttackValue(), getArmour(),
        getTemporaryAttackValue(), getTemporaryDefenseValue(), getReceivedAttacks(),
        getInitiatedAttacks(), isAlive(), getCurrentArmy(), getCurrentTerrain(), getEventManager());
  }
}
