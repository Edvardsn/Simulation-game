package org.ntnu.petteed.Model;

/**
 * This class represents the Burn debuff which is a status effect that
 * inflicts damage over time (DOT) to a given unit.
 *
 */
public class BurnEffect implements ActionEventListener {

  private static final int DURATION = 5;
  private int ticks = 0;

  /**
   * Applies the burn debuff to given unit.
   *
   * @param context The unit which the effect if applied to
   */
  public void handleEvent(Object context) {
    if (ticks < DURATION && context instanceof Unit unit) {
      unit.setHealth((unit).getHealth() - 2);
      ticks++;
    }
  }
}
