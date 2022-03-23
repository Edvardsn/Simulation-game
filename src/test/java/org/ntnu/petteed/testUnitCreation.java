package org.ntnu.petteed;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

/**
 * Positive tests:
 * - testUnitCreation, tests that a unit can be created with valid fields.
 *
 * Negative tests:
 * - testUnitWithNullName, tests that a unit cannot be created with null as a name.
 * - testUnitWithNegativeAttack, tests that a unit cannot have negative attack when created.
 *
 */

public class testUnitCreation {

  /**
   * Tests that a unit can be created with valid fields.
   *
   */
  @Test
  public void testUnitCreation() {
    Unit unit = new InfantryUnit("unitsen", 100);

    assertEquals(100, unit.getHealth());
    assertEquals("unitsen", unit.getName());
    assertEquals(15, unit.getAttack());
    assertEquals(10, unit.getArmour());
  }

  /**
   * Tests that a unit cannot be created with null as a name.
   *
   */
  @Test
  public void testUnitWithNullName() {
      assertThrows(IllegalArgumentException.class, ()-> {
        Unit unit = new InfantryUnit(null, 100);
      });
  }

  /**
   * Tests that a unit cannot have negative attack when created.
   *
   */
  @Test
  public void testUnitWithNegativeAttack(){
    assertThrows(IllegalArgumentException.class, ()->{
      Unit unit = new InfantryUnit("Test",-100);
    });
  }
}