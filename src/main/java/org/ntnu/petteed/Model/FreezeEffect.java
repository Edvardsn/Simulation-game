package org.ntnu.petteed.Model.Effects;

import org.ntnu.petteed.Model.ActionEvent;
import org.ntnu.petteed.Model.ActionEventListener;
import org.ntnu.petteed.Model.Unit;

/**
 * This class represents a Freeze debuff which is a Status effect that
 * assigns an attackdebuff to a unit.
 *
 */
public class FreezeEffect implements ActionEventListener {

  private static final int DURATION = 5;
  private int ticks = 0;
  private int attackDebuff = -5;

  /**
   * Minimizes the attack debuff
   */
  private void minimizeDebuff() {
    this.attackDebuff += 1;
  }

  /**
   * Applies the effect to given unit
   *
   * @param unit The unit to apply the effect to
   */
  public void applyEffect(Unit unit) {
    if (ticks < DURATION) {
      minimizeDebuff();

      int newAttackValue = unit.getTemporaryAttackValue() + attackDebuff;

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
  public void handleActionEvent(ActionEvent event) {
    if(event.getContext() instanceof  Unit unit){
      applyEffect(unit);
    }
  }
}
