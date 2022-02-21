package org.ntnu.petteed;

/**
 * A class that represents a single unit and its characteristics
 *
 * @author Petter Edvardsen
 * @version 16/02/22
 */
public abstract class Unit {

  private final String name;
  private int health;
  private final int attack;
  private final int armour;
  protected int receivedAttacks;
  protected int initiatedAttacks;

  /**
   * Creates an instance of Unit
   *
   * @param name,   The name of the unit
   * @param health, The health of the unit
   * @param attack, The attack value of the unit
   * @param armour, The armour value of the unit
   */
  protected Unit(String name, int health, int attack, int armour)throws IllegalArgumentException {
      if (name != null){
        this.name = name;
      }else {
        throw new IllegalArgumentException("Name as null is not a valid name");
      }
      if (health < 0){
        throw new IllegalArgumentException("Cannot create a unit with negative health");
      }else{
        this.health = health;
      }
      this.attack = attack;
      this.armour = armour;
      this.receivedAttacks = 0;
      this.initiatedAttacks = 0;
    }

   /**
   * Attacks another unit
   *
   * @param opponent, the unit to attack
   */
   protected void attack(Unit opponent) {

    if(opponent != null && this.isAlive() && !(opponent.equals(this))) {

      int initialDamage = this.getAttack() + this.getAttackBonus();

      int resistances = opponent.getResistBonus() + opponent.getArmour();

      int actualDamage = initialDamage - resistances;

      opponent.setHealth(opponent.getHealth() - actualDamage);

      this.incrementInitiatedAttacks(); // Registers initiated attack
      opponent.incrementReceivedAttacks(); // Registers being attacked
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
   * Sets a new value to the health of a unit
   *
   * @param newHealth, the new value of health
   */
  protected void setHealth(int newHealth) {
    this.health = newHealth;
  }

  /**
   * Checks whether the unit is alive
   *
   * @return True if the unit is alive, false if not.
   */
  public boolean isAlive(){
    boolean isAlive = this.getHealth() > 0;

    return isAlive;
  }

  /**
   * Returns the attack bonus of the unit
   */
  public abstract int getAttackBonus();

  /**
   * Returns the resist bonus of the unit
   */
  public abstract int getResistBonus();

  /**
   * Returns a description of the characteristics of the unit
   *
   * @return String, the details of the characteristics
   */
  @Override
  public String toString() {
    return "Unit{" + "name='" + name + '\'' + ", health=" + health + ", attack=" + attack +
        ", armour=" + armour + '}';
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
  public int getAttack() {
    return attack;
  }

  /**
   * Returns the armour of the unit
   *
   * @return the armour of the unit
   */
  public int getArmour() {
    return armour;
  }
}
