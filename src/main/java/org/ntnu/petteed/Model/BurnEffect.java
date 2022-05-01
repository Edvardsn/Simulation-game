package org.ntnu.petteed.Model;

/**
 * This class represents the Burn debuff which is a status effect that
 * inflicts damage over time (DOT) to a given unit.
 *
 */
public class BurnEffect implements ActionListener {

  private EventIdentifyer eventIdentifyer;
  private static final int DURATION = 5;
  private int ticks = 0;

  /**
   * Applies the burn debuff to given unit.
   *
   * @param unit The unit which the effect if applied to
   */
  public void handleEvent(Unit unit) {
    if (ticks < DURATION) {
        unit.setHealth((unit).getHealth() - 2);
        ticks++;
      } else {
        unit.eventManager.removeEventListener(EventIdentifyer.ATTACK, this);
      }
  }

  /**
   * Assigns the identifyer used for the
   *
   * @param eventIdentifyer
   */
  public void setIdentifyer(EventIdentifyer eventIdentifyer){
    this.eventIdentifyer = eventIdentifyer;
  }

  /**
   * Returns the identifyer of the effect
   *
   * @return The identifyer of the effect
   */
  @Override
  public EventIdentifyer getIdentifyer() {
    return eventIdentifyer;
  }
}
