package org.ntnu.petteed.Model.Effects;

import org.ntnu.petteed.Model.EventMechanics.ActionEvent;
import org.ntnu.petteed.Model.EventMechanics.ActionEventListener;
import org.ntnu.petteed.Model.EventMechanics.Event;
import org.ntnu.petteed.Model.Unit;

/**
 * This class represents a Freeze debuff which is a Status effect that
 * assigns an attackdebuff to a unit.
 *
 */
public class FreezeEffect implements ActionEventListener {

  private static final int DURATION = 1;
  private int ticks = 0;
  private int ATTACK_DEBUFF = -5;

  /**
   * Minimizes the attack debuff
   */
  private void minimizeDebuff() {
    this.ATTACK_DEBUFF += 1;
  }

  /**
   * Applies the effect to given unit
   *
   * @param unit The unit to apply the effect to
   */
  public void applyEffect(Unit unit) {
    if (ticks < DURATION) {
      minimizeDebuff();

      int newAttackValue = unit.getTemporaryAttackValue() + ATTACK_DEBUFF;

      unit.setTemporaryAttackValue(newAttackValue);

      this.ticks++;

    } else {
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
    if(event.getContext() instanceof  Unit unit
        && event instanceof ActionEvent actionEvent){
      applyEffect(unit);
    }
  }

  /**
   * Returns the value of the attack debuff
   *
   * @return The value of the attack debuff
   */
  public int getAttackDebuff() {
    return ATTACK_DEBUFF;
  }

  /**
   * Returns the duration of the effect
   *
   * @return The duration of the effect
   */
  public static int getDURATION() {
    return DURATION;
  }
}
