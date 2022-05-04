package org.ntnu.petteed.Model;

import org.ntnu.petteed.Model.HealthEvent;
import org.ntnu.petteed.Model.HealthEventListener;
import org.ntnu.petteed.Model.Unit;

/**
 * This class represents the shield effect which is an effect that gives shields an actor for
 * specified amount of damage
 *
 */
public class ShieldEffect implements HealthEventListener {

  /**
   * A reaction to the given health event
   *
   * @param healthEvent The health event to react to
   */
  @Override
  public void handleHealthEvent(HealthEvent healthEvent) {
    if(healthEvent.getContext() instanceof Unit unit){
      unit.setTemporaryDefenseValue(unit.getTemporaryDefenseValue() + 10);
    }
  }
}
