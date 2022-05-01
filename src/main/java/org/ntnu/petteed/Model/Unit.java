package org.ntnu.petteed.Model;

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
  protected int conditionalAttackValue = 0;
  protected int conditionalDefenseValue = 0;

  protected Terrain currentTerrain = null;
  public EventManager eventManager;


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
    this.eventManager = new EventManager();
  }

  /**
   * Assigns a new value to the health of a unit
   *
   * @param newHealth, the new value of health
   */
  protected void setHealth(int newHealth) {
    this.health = newHealth;

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

      eventManager.notifyListeners(EventIdentifyer.ATTACK,this);

      int trueDamage = getTotalAttackDamage() - getTotalResistances(opponent); // The actual amount deducted from the opponents health

      opponent.setHealth(opponent.getHealth() - trueDamage);

      opponent.incrementReceivedAttacks();
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

  /**
   * Returns the total resistances of a unit
   *
   * @param unit The unit which resistances to get
   * @return The value of the total resistance
   */
  public int getTotalResistances(Unit unit) {
    return unit.getResistBonus() + unit.getArmour() + conditionalDefenseValue;
  }

  /**
   * Returns the total attack value of the unit
   *
   * @return The total attack value of the unit
   */
  public int getTotalAttackDamage() {
    return this.getAttackValue() + this.getAttackBonus() + conditionalAttackValue;
  }
}
