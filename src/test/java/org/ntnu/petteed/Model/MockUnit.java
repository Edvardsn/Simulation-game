package org.ntnu.petteed.Model;


/**
 * This class represents a "Mock Unit" which has the purpose of testing the unit class
 * given that its abstract.
 *
 */
public class MockUnit extends Unit{

  /**
   * Creates an instance of MockUnit
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
   * Copy constructor for the mock unit
   *
   * @param unit The unit to copy
   */
  public MockUnit(MockUnit unit){
    super(unit);
  }


  /**
   * Returns the attack bonus of the unit
   *
   * @return The attack bonus of the unit
   */
  @Override
  public int getAttackBonus() {
    return 0;
  }

  /**
   * Returns the resist bonus of the unit
   *
   * @return The resist bonus of the unit
   */
  @Override
  public int getResistBonus() {
    return 0;
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
    return new MockUnit(this);
  }
}
