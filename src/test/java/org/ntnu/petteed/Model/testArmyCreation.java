package org.ntnu.petteed.Model;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Collection;
import org.junit.jupiter.api.Test;

/**
 * Unit-tests of the hand of cards class.
 * The following tests are performed:
 * <ul>
 *   <li>Successful creation of an instance with valid suit and face (positive)</li>
 *   <li>Successful handling of creation of an instance with invalid suit or face (negative)</li>
 *   <li>Test five-card-flush - on both a winning hand and a non-winning hand (both test are positive)</li>
 *   <li>Test card of hearts present - on both a winning hand and a non-winning hand</li>
 *   <li>Test Queen of Spades present - on both a winning hand and a non-winning hand</li>
 *   <li>Test sum of faces</li>
 *   <li>Test all 4 on an empty hand (still a positive test)</li>
 * </ul>


/**
 * Positive tests:
 * - testCreatingArmy, tests that an Army can be created with valid fields
 *
 * Negative tests:
 * - testArmyWithNullName, test that an Army cannot be created without a name
 * - testArmyWithoutUnits, tests that an Army cannot be created without units
 *
 */

class testArmyCreation {

  /**
   * Tests that an Army can be created with valid fields
   *
   */
  @Test
   void testCreatingArmy(){
    Collection<Actor> testList = new ArrayList<>();
    Army testArmy = new Army("test",testList);
  }

  /**
   * Test that an Army cannot be created without a name
   *
   */
  @Test
   void testArmyWithNullName(){
    Collection<Actor> testList = new ArrayList<>();
    assertThrows(IllegalArgumentException.class, () -> {
      new Army(null,testList);
    });
  }

  /**
   * Tests that an Army cannot be created without units.
   *
   */
  @Test
  void testArmyWithoutUnits(){
    assertThrows(IllegalArgumentException.class,()->{
      new Army("test",null);
    });
  }

}
