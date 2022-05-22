package org.ntnu.petteed.Model;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;
import org.ntnu.petteed.Model.Effects.BurnEffect;

/**
 * This class performs the following tests to ensure that the BurnEffect class is behaving as intended.
 *
 * Positive tests:
 * <ul>
 * <li>testCorrectDuration, tests that the effect wears off at the right time</li>
 * <li>testEffectDebuff, tests that the debuff's effect takes action</li>
 *</ul>
 *
 */

public class testBurnEffect {

  /**
   * Tests that the effect is removed when its duration is over
   *
   */
  @Test
  void testCorrectDuration(){
    Unit testUnit1 = new MockUnit("test",100,1,1);
    Unit testUnit2 = new MockUnit("test",100,1,1);
    testUnit1.getEventManager().addEventListener(new BurnEffect());

    int counter = 0;

    while(counter <= BurnEffect.getDURATION()){
      testUnit1.attack(testUnit2);

      counter++;
    }
    assertFalse(testUnit1.getEventManager().getListenersIterator().hasNext());
  }


  /**
   * Tests that the debuff is working as intended s
   *
   */
  @Test
 void testEffectDebuff(){
    int startHealth = 100;

    int intendedDamage = BurnEffect.getTickDamage() * BurnEffect.getDURATION();

    Unit unitWithDebuff = new MockUnit("test",startHealth,1,1);
    Unit testUnit = new MockUnit("test",100,1,1);
    unitWithDebuff.getEventManager().addEventListener(new BurnEffect());

    int counter = 0;

    while(counter < BurnEffect.getDURATION()){
      unitWithDebuff.attack(testUnit);
      counter++;
    }
    assertEquals(unitWithDebuff.getHealth(),startHealth - intendedDamage);
  }
}
