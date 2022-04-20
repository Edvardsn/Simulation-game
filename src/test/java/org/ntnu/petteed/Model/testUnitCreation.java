package org.ntnu.petteed.Model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

/**
 * Positive tests:
 * <ul>
 * <li>testUnitValues, tests that a unit can be created with valid fields.</li>
 *</ul>
 * Negative tests:
 * <ul>
 * <li> testUnitWithNullName, tests that a unit cannot be created with null as a name.</li>
 * <li> testUnitWithNegativeAttack, tests that a unit cannot have negative attack when created. </li>
 *</ul>
 */

public class testUnitCreation {

  /**
   * Tests that a unit can be created with valid fields.
   *
   */
  @Test
  public void testUnitValues() {
    Unit unit = new InfantryUnit("unitsen", 100);

    assertEquals(100, unit.getHealth());
    assertEquals("unitsen", unit.getName());
    assertEquals(15, unit.getAttackValue());
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