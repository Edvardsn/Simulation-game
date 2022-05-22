package org.ntnu.petteed.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.ntnu.petteed.Model.Effects.FreezeEffect;

/**
 * This class performs the following tests to ensure that the FreezeEffect class is behaving as intended.
 *
 * Positive tests:
 * <ul>
 * <li>testCorrectDuration, tests that the effect wears off at the right time</li>
 * <li>testEffectDebuff, tests that the debuff's effect takes action</li>
 *</ul>
 *
 */
public class testFreezeEffect {

  /**
   * Tests that the effect wears off at the right time
   */
  @Test
  void testCorrectDuration(){
      Unit testUnit1 = new MockUnit("test",100,1,1);
      Unit testUnit2 = new MockUnit("test",100,1,1);
      testUnit1.getEventManager().addEventListener(new FreezeEffect());

      int counter = 0;

      while(counter <= FreezeEffect.getDURATION()){
        testUnit1.attack(testUnit2);

        counter++;
      }
      assertFalse(testUnit1.getEventManager().getListenersIterator().hasNext());
  }

  /**
   * Tests that the debuff's effect takes action
   */
  @Test
  void testEffectDebuff(){
    int starthealth = 100;

    Unit unitToBeDebuffed = new MockUnit("debuffed",100,10,10);
    Unit unitToBeAttacked = new MockUnit("attacked",starthealth,1,0);

    FreezeEffect freezeEffect = new FreezeEffect();

    int startAttackValue = unitToBeDebuffed.getAttackValue();

    int intendedAttackValue = unitToBeDebuffed.getAttackValue() + freezeEffect.getAttackDebuff();

    unitToBeDebuffed.getEventManager().addEventListener(freezeEffect);

    unitToBeDebuffed.attack(unitToBeAttacked);

    assertEquals(starthealth - unitToBeDebuffed.getAttackValue() - freezeEffect.getAttackDebuff(), unitToBeAttacked.getHealth());
  }

}
