package org.ntnu.petteed.Model;

import java.util.Collection;
import java.util.Set;
import org.reflections.Reflections;

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
  private Collection<BattleCondition> battleConditions;

  protected int receivedAttacks;
  protected int initiatedAttacks;

  /**
   * Creates an instance of Unit
   *
   * @param name,   The name of the unit
   * @param health, The health of the unit
   * @param attack, The attack value of the unit
   * @param armour, The armour value of the unit
   * @throws IllegalArgumentException If there are any invalid parameters
   */
  protected Unit(String name, int health, int attack, int armour) {
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
    this.attack = attack;
    this.armour = armour;
    this.receivedAttacks = 0;
    this.initiatedAttacks = 0;
  }

  /**
   * Assigns a new value to the health of a unit
   *
   * @param newHealth, the new value of health
   */
  protected void setHealth(int newHealth, Object modifier) {
    Reflections reflections  = new Reflections("org.ntnu.petteed"); // Selve reflections i mappen

    Set<Class<?extends Unit>> subTypes =  reflections.getSubTypesOf(Unit.class); // Alle underklasser av Unit

    if(subTypes.stream().anyMatch(type -> type == modifier.getClass())){ // Makes it so the unit itself keeps track of received attacks
      this.receivedAttacks++;
    }
    if(newHealth > 0) {
      this.health = newHealth;
    }
    else{
      newHealth = 0;
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

      int totalAttackDamage = this.getAttack() + this.getAttackBonus();

      int totalResistances = opponent.getResistBonus() + opponent.getArmour();

      int trueDamage = totalAttackDamage - totalResistances; // The actual amount deducted from the opponents health

      opponent.setHealth(opponent.getHealth() - trueDamage,this);

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
   * Assigns the unit a status effect
   *
   * @return A status effect
   */
  public void addBattleCondition(BattleCondition battleCondition){
    battleConditions.add(battleCondition);
  };

  /**
   * Returns the current battle conditions of the user
   *
   * @return The current battle conditions of the user
   */
  public Collection<BattleCondition> getBattleCondition() {
    return this.battleConditions;
  }

  /**
   * Checks for a match on given battle condition effect
   *
   * @param condition The condition to check for
   * @return {@code Boolean} True if present, false if not.
   */
  public boolean hasConditionEffect(String condition) {
    boolean hasCondition;

    if(this.getBattleCondition() != null && this.getBattleCondition()
        .stream()
        .anyMatch(battleCondition ->
            battleCondition.getTerrain().equals(condition))){
      hasCondition = true;
    } else{
      hasCondition = false;
    }
    return hasCondition;
  }

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
