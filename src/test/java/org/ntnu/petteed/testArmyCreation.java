package org.ntnu.petteed;

import static org.junit.Assert.assertThrows;

import java.util.ArrayList;
import org.junit.Test;

/**
 * Positive tests:
 * - testCreatingArmy, tests that an Army can be created with valid fields
 *
 * Negative tests:
 * - testArmyWithNullName, test that an Army cannot be created without a name
 * - testArmyWithoutUnits, tests that an Army cannot be created without units
 *
 */

public class testArmyCreation {

  /**
   * Tests that an Army can be created with valid fields
   *
   */
  @Test
  public void testCreatingArmy(){
    ArrayList<Unit> testList = new ArrayList<>();
    Army testArmy = new Army("test",testList);
  }

  /**
   * Test that an Army cannot be created without a name
   *
   */
  @Test
  public void testArmyWithNullName(){
    ArrayList<Unit> testList = new ArrayList<>();
    assertThrows(IllegalArgumentException.class, () -> {
      new Army(null,testList);
    });
  }

  /**
   * Tests that an Army cannot be created without units.
   *
   */
  @Test
  public void testArmyWithoutUnits(){
    assertThrows(IllegalArgumentException.class,()->{
      new Army("test",null);
    });
  }

}
