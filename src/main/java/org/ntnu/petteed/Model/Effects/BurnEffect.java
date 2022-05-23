package org.ntnu.petteed.Model.Effects;

import org.ntnu.petteed.Model.EventMechanics.ActionEventListener;
import org.ntnu.petteed.Model.EventMechanics.Event;
import org.ntnu.petteed.Model.Unit;

/**
 * This class represents the Burn debuff which is a status effect that
 * inflicts damage over time (DOT) to a given unit.
 *
 */
public class BurnEffect implements ActionEventListener {

 private static final int DURATION = 5;
 private static final int tickDamage = 1;
 private int ticks = 0;

  /**
   * Applies the burn effect to a given unit
   *
   * @param unit The unit to apply the effect to
   */
  public void applyEffect(Unit unit) {
    if (ticks  < DURATION && unit != null) {
      unit.setHealth(unit.getHealth() - tickDamage);
      ticks++;
    }
    else if(unit != null){
      unit.eventManager.removeEventListener(this);
    }
  }

  /**
   * Reacts to the action event by applying its effects
   *
   * @param event The action event
   */
  @Override
  public void handleEvent(Event event) {
    if (event.getContext() instanceof Unit unit) {
      applyEffect(unit);
    }
  }

  /**
   * Returns the duration of the effect
   *
   * @return The duration of the effect
   */
  public static int getDURATION() {
    return DURATION;
  }

  /**
   * Returns the damage of the effect
   *
   * @return The damage of the effect
   */
  public static int getTickDamage(){
    return tickDamage;
  }
}