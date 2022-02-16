package org.ntnu.petteed;

/**
 * A class that represents a single unit and its characteristics
 *
 * @author Petter Edvardsen
 * @version 16/02/22
 */
public abstract class Unit {

  private String name;
  private int health;
  private int attack;
  private int armour;

  /**
   * Creates an instance of Unit
   *
   * @param name,   The name of the unit
   * @param health, The health of the unit
   * @param attack, The attack value of the unit
   * @param armour, The armour value of the unit
   */
  protected Unit(String name, int health, int attack, int armour) {
    this.name = name;
    this.health = health;
    this.attack = attack;
    this.armour = armour;
  }

  /**
   * Attacks another unit
   *
   * @param opponent, the unit to attack
   */
  protected void attack(Unit opponent) {
    int damage = this.getAttack() + this.getAttackBonus();

    int resistances = opponent.getHealth() + opponent.getResistBonus();

    opponent.setHealth(resistances - damage);
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
