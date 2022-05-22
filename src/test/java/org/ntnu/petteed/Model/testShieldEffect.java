package org.ntnu.petteed.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import org.junit.jupiter.api.Test;
import org.ntnu.petteed.Model.Units.SupportUnit;

/**
 * This class performs the following tests to ensure that the ShieldEffect class is behaving as intended.
 *
 * Positive tests:
 * <ul>
 * <li>testEffectBuff, tests that the Buff's effect takes action</li>
 *</ul>
 *
 */
public class testShieldEffect {

  /**
   * Tests that the shield effect negates the correct amount of damage
   */
  @Test
  void testEffectBuff(){
    int starthealth = 100;

    Unit unitWhoAppliesSpell = new SupportUnit("test",100);
    Unit unitToBeShielded = new MockUnit("Shielded",starthealth,10,1);

    Collection<Actor> units = new ArrayList<>();
    units.add(unitWhoAppliesSpell);
    units.add(unitToBeShielded);

    Army armyWithAllies = new Army("Test",units);

    unitWhoAppliesSpell.act(null);
    unitWhoAppliesSpell.attack(unitToBeShielded);

    assertEquals(starthealth,unitToBeShielded.getHealth());
  }
}
