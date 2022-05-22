package org.ntnu.petteed.Model;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.ntnu.petteed.Model.Units.CavalryUnit;
import org.ntnu.petteed.Model.Units.InfantryUnit;
import org.ntnu.petteed.Model.Units.RangedUnit;


/**
 * This class performs the following tests to ensure that all attacking mechanics is behaving as intended.
 *
 * Positive tests:
 * <ul>
 *   <li>testCorrectHealthAfterAttack, tests that another actor has correct health after an attack</li>
 *   <li>testNonNegativeHealth, tests that the health cannot be set to a negative value</li>
 *   <li>testRangedDefensiveBonus, tests that the ranged defensive bonus gives the right values</li>
 *   <li>testCavalryOffensiveBonus, tests that the cavalry offensive bonus gives the right values</li>
 * </ul>
 *-
 *
 * Negative tests:
 * <ul>
 *   <li>testCannotAttackItself, tests that a unit cannot attack itself</li>
 *   <li>testAttackingWhileDead, tests that a unit cannot attack whilst dead</li>
 *   <li>testAttackingNull, tests that a unit cannot attack null</li>
 * </ul>
 *
 */

public class testAttackingMechanics {

  /**
   * Tests that an attack returns the correct values
   *
   */
  @Test
  public void testCorrectHealthAfterAttack() {
    Unit attacker = new MockUnit("attacker", 100,10,10);
    Unit receiver = new MockUnit("reciver", 100,10,10);

    attacker.attack(receiver);

    assertEquals(94, receiver.getHealth());
  }

  /**
   * Tests that the healthvalue is set correctly
   *
   */
  @Test
   void testNonNegativeHealthValue() {
    Unit unit = new MockUnit("unit", 100,1,1);
    unit.setHealth(-20);

    assertEquals(0, unit.getHealth());
  }

  /**
   * Tests the defensive bonus of the Rangedunit
   *
   */
  @Test
   void testRangedUnitDefensiveBonus() {
    RangedUnit unit1 = new RangedUnit("unit", 100);
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
   void testCavalryUnitOffensiveBonus() {
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
   void testCannotAttackItself(){
    MockUnit unit = new MockUnit("unit",100,1,1);
    unit.attack(unit);
    assertEquals(100,unit.getHealth());
  }

  /**
   * Tests that a unit cannot attack while dead
   *
   */
  @Test
   void testAttackingWhileDead(){
    MockUnit unit1 = new MockUnit("unit1", 0,1,1);
    MockUnit unit2 = new MockUnit("unit2", 100,1,1);

    unit1.attack(unit2);
    assertEquals(100,unit2.getHealth());
  }

  /**
   * Tests that a unit cannot attack null
   *
   */
  @Test
   void testAttackingNull(){
    Unit unit = new MockUnit("unitsen", 100,1,1);
    unit.attack(null);
  }
}
