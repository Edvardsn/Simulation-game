package org.ntnu.petteed.Model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.ntnu.petteed.Model.Units.SupportUnit;

/**
 * This class performs the following tests to ensure that the BurnEffect class is behaving as intended.
 *
 * Positive tests:
 * <ul>
 * <li>testAppliesShieldEffect, Tests that the SupportUnit applies a shieldEffect when in an army</li>
 *</ul>
 *
 * Negative tests:
 * <ul>
 * <li>testCurrentArmyIsNull, tests that an exception is not thrown when current army is null</li>
 *</ul>
 */
public class testSupportUnit {

  /**
   * Tests that the SupportUnit applies a shieldEffect when in an army
   *
   */
  @Test
  void testAppliesShieldEffect(){
    Unit unitWhoAppliesSpell = new SupportUnit("test",100);
    Army armyWithAllies = new Army("Test",testArmy.createTestActors());

    armyWithAllies.addActor(unitWhoAppliesSpell);

    unitWhoAppliesSpell.act(null);

    assertTrue(armyWithAllies
        .getActors()
        .stream()
        .filter(Unit.class::isInstance)
        .anyMatch(unit -> ((Unit) unit).getEventManager().getListenersIterator().hasNext()));
  }

  /**
   * Tests that an exception is not thrown if currently in no army
   */
  @Test
  void testCurrentArmyIsNull(){
    Unit unitWhoAppliesSpell = new SupportUnit("test",100);
    unitWhoAppliesSpell.act(null);
  }
}
