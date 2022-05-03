package org.ntnu.petteed.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.ntnu.petteed.Model.Units.InfantryUnit;

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

 class testUnitCreation {

  /**
   * Tests that a unit can be created with valid fields.
   *
   */
  @Test
   void testUnitValues() {
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
   void testUnitWithNullName() {
      assertThrows(IllegalArgumentException.class, ()-> {
        Unit unit = new InfantryUnit(null, 100);
      });
  }

  /**
   * Tests that a unit cannot have negative attack when created.
   *
   */
  @Test
   void testUnitWithNegativeAttack(){
    assertThrows(IllegalArgumentException.class, ()->{
      Unit unit = new InfantryUnit("Test",-100);
    });
  }
}