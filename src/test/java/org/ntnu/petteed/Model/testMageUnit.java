package org.ntnu.petteed.Model;


import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.ntnu.petteed.Model.Units.MageUnit;

/**
 * This class performs the following tests to ensure that the BurnEffect class is behaving as intended.
 *
 * Positive tests:
 * <ul>
 * <li>testAppliesSpell, tests that the unit applies spells when it does an action</li>
 *</ul>
 *
 * Negative tests:
 * <ul>
 * <li> testAppliedSpellToNull, tests that no Runtime exception is thrown if attempted to apply to null</li>
 *</ul>
 */
public class testMageUnit {

  /**
   * Tests that if MageUnit acts upon a unit an effect is added
   *
   */
  @Test
  void testAppliesSpell(){
    Unit unitWhoAppliesSpell = new MageUnit("test",100);
    Unit testUnit2 = new MockUnit("test",100,1,1);

    unitWhoAppliesSpell.act(testUnit2);

    assertTrue(testUnit2.getEventManager().getListenersIterator().hasNext());

  }

  /**
   * Tests that if Mageunit acts upon null, no Runtime exception will be thrown
   *
   */
  @Test
  void testAppliedSpellToNull(){
    Unit unitWhoAppliesSpell = new MageUnit("test",100);
    unitWhoAppliesSpell.act(null);
  }
}
