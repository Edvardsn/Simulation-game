package org.ntnu.petteed.Model;

/**
 * This class represents the Burn debuff which is a status effect that
 * inflicts damage over time (DOT) to a given unit.
 *
 */
public class Burn implements StatusEffect {

  private static final int DURATION = 5;
  private int ticks = 0;

  /**
   * Applies the burn debuff to given unit.
   *
   * @param unit The unit which the effect if applied to
   */
  @Override
  public void apply(Unit unit) {
    while (ticks < DURATION) {
      unit.setHealth(unit.getHealth() - 2, this);
      ticks++;
    }
  }
}
