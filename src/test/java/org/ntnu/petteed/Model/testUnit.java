package org.ntnu.petteed.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * This class performs the following tests to ensure that the Unit class is behaving as intended.
 *
 * Positive tests:
 * <ul>
 * <li>testUnitValues, tests that a unit can be created with valid fields.</li>
 * <li>testDistinctCopy, tests that a unit copied is distinct from the original</li>
 *</ul>
 * Negative tests:
 * <ul>
 * <li> testUnitWithNullName, tests that a unit cannot be created with null as a name.</li>
 * <li> testUnitWithNegativeAttack, tests that a unit cannot have negative attack when created. </li>
 * <li> testUnitWithNegativeArmour, tests that a unit cannot have negative armour when created. </li>
 * <li> testUnitReceiveNullAttack, tests that a unit can receive a null attack with no exceptions thrown</li>
 *</ul>
 */

 class testUnit {

  /**
   * Tests that a unit can be created with valid fields.
   *
   */
  @Test
   void testUnitValues() {
    Unit unit = new MockUnit("unit", 100,15,10);

    assertEquals(100, unit.getHealth());
    assertEquals("unit", unit.getName());
    assertEquals(15, unit.getAttackValue());
    assertEquals(10, unit.getArmour());
  }

  /**
   * Tests that a distinct copy is made which does not reference to same unit
   */
  @Test
  void testDistinctCopy(){
    MockUnit unit = new MockUnit("unit", 100,15,10);
    Unit copiedUnit = (Unit) unit.copy();

    copiedUnit.setHealth(50);
    unit.setCurrentTerrain(new Terrain("Terrain Not to be included in copy"));

    assertNotEquals(unit.getHealth(), copiedUnit.getHealth());
    assertNotEquals(unit.getCurrentTerrain(), copiedUnit.getCurrentTerrain());
  }

  /**
   * Tests that a unit cannot be created with null as a name.
   *
   */
  @Test
   void testUnitWithNullName() {
      assertThrows(IllegalArgumentException.class, ()-> {
        Unit unit = new MockUnit(null, 100,1,1);
      });
  }

  /**
   * Tests that a unit cannot have negative attack when created.
   *
   */
  @Test
   void testUnitWithNegativeAttack(){
    assertThrows(IllegalArgumentException.class, ()->{
      Unit unit = new MockUnit("Test",100,-10,15);
    });
  }

 /**
  * Tests that a unit who recives a null attack won't throw an exception
  *
  */
 @Test
 void testUnitRecieveNullAttack(){
   Unit unit = new MockUnit("Test", 100,1,1);
   unit.receiveAttack(null);
  }
}