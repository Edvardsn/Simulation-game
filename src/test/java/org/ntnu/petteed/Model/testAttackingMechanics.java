package org.ntnu.petteed.Model;



import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.ntnu.petteed.Model.Units.CavalryUnit;
import org.ntnu.petteed.Model.Units.InfantryUnit;
import org.ntnu.petteed.Model.Units.RangedUnit;


/**
 * Positive tests:
 * - test whether units have correct health after attacking
 * - test whether health is set to the right value
 *-
 *
 * Negative tests:
 * ......
 *
 *  TEST UNIT !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ABSTRACT
 *
 */

public class testAttackingMechanics {

  /**
   * Tests that an attack returns the correct values
   *
   */
  @Test
  public void testCorrectHealthAfterAttack() {
    Unit attacker = new InfantryUnit("testGuy", 100);
    Unit receiver = new InfantryUnit("poorVictim", 100);

    attacker.attack(receiver);

    assertEquals(94, receiver.getHealth());
  }

  /**
   * Tests that the healthvalue is set correctly
   *
   */
  @Test
   void testSetCorrectHealthValue() {
    Unit unit = new InfantryUnit("unitsen", 100);
    unit.setHealth(50);

    assertEquals(50, unit.getHealth());
  }

  /**
   * Tests the defensive bonus of the Rangedunit
   *
   */
  @Test
   void testRangedUnitDefensiveBonus() {
    RangedUnit unit1 = new RangedUnit("unisten", 100);
    RangedUnit unit2 = new RangedUnit("test", 100);

    assertEquals(6, unit1.getResistBonus()); // Checking resist bonus on first received attack

    unit2.attack(unit1);

    assertEquals(4, unit1.getResistBonus()); // Checking resist bonus on second received attack

    unit2.attack(unit1);

    assertEquals(2, unit1.getResistBonus()); // Checking resist bonus on third received attack

  }

  /**
   * Tests that the Cavalryunit has correct attack bonus
   *
   */
  @Test
   void testCavalryUnitAttackBonus() {
    CavalryUnit unit1 = new CavalryUnit("unit1", 100);
    CavalryUnit unit2 = new CavalryUnit("unit2", 100);

    assertEquals(6, unit1.getAttackBonus()); // Checking attack bonus on first attack

    unit1.attack(unit2);

    assertEquals(2, unit1.getAttackBonus()); // Checking attack bonus on second attack
  }

  /**
   * Makes sure a unit cannot attack itself
   *
   */
  @Test
   void testAttackingMyself(){
    InfantryUnit unit = new InfantryUnit("unisten",100);
    unit.attack(unit);
    assertEquals(100,unit.getHealth());
  }

  /**
   * Tests that a unit cannot attack while dead
   *
   */
  @Test
   void testAttackingWhileDead(){
    CavalryUnit unit1 = new CavalryUnit("unit1", 0);
    CavalryUnit unit2 = new CavalryUnit("unit2", 100);

    unit1.attack(unit2);
    assertEquals(100,unit2.getHealth());
  }

  /**
   * Tests that a unit cannot attack null
   *
   */
  @Test
   void testAttackingNull(){
    Unit unit = new InfantryUnit("unitsen", 100);
    unit.attack(null);
  }
}
