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
   * Applies the burn effect to a given unit
   *
   * @param unit The unit to apply the effect to
   */
  public void applyEffect(Unit unit) {
    if (ticks < DURATION) {
      unit.setHealth(unit.getHealth() - 1);
      ticks++;
    }
  }

  /**
   * Reacts to the action event by applying its effects
   *
   * @param event The action event
   */
  @Override
  public void handleActionEvent(ActionEvent event) {
    if (event.getContext() instanceof Unit unit) {
      applyEffect(unit);
    }
  }


}