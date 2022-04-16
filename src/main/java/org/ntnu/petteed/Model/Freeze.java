package org.ntnu.petteed.Model;

/**
 * This class represents a Freeze debuff which is a Status effect that
 * assigns a attackdebuff to a its given unit.
 *
 */
public class Freeze implements StatusEffect {

  private static final int DURATION = 5;
  private int ticks = 0;

  private int attackDebuff = -5;

  /**
   * Applies the debuff
   *
   * @param unit The unit which the effect if applied to
   */
  @Override
  public void apply(Unit unit) {
    while(ticks < DURATION){
      attackDebuff += 1;

      unit.setConditionalAttack(unit.getConditionalAttack() + attackDebuff);
      ticks++;
    }
  }
}
