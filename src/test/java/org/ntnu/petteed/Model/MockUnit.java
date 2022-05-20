package org.ntnu.petteed.Model;

public class MockUnit extends Unit{

  /**
   * Creates an instance of Unit
   *
   * @param name The name of the unit
   * @param health The health of the unit
   * @param attackValue The attack value of the unit
   * @param armour The armour of the unit
   * @throws IllegalArgumentException If there are any invalid parameters
   */
  public MockUnit(String name, int health, int attackValue, int armour) {
    super(name, health, attackValue, armour);
  }

  /**
   * Returns the attack bonus of the unit
   *
   * @return The attack bonus of the unit
   */
  @Override
  public int getAttackBonus() {
    return 5;
  }

  /**
   * Returns the resist bonus of the unit
   *
   * @return The resist bonus of the unit
   */
  @Override
  public int getResistBonus() {
    return 5;
  }

  /**
   * @param target Optional target for actions
   */
  @Override
  public void act(Object target) {
    if(target instanceof Unit unit){
      attack(unit);
    }
  }

  /**
   * Copies the Actor
   *
   * @return A copied version of the actor
   */
  @Override
  public Actor copy() {
    return null;
  }
}
