package org.ntnu.petteed.Model.Effects;

import org.ntnu.petteed.Model.EventMechanics.Event;
import org.ntnu.petteed.Model.EventMechanics.HealthEvent;
import org.ntnu.petteed.Model.EventMechanics.HealthEventListener;
import org.ntnu.petteed.Model.Unit;

/**
 * This class represents the shield effect which is an effect that gives shields an actor for
 * specified amount of damage
 *
 */
public class ShieldEffect implements HealthEventListener {

  private static final int SHIELDBUFF = 10;

  /**
   * A reaction to the given health event
   *
   * @param event The health event to react to
   */
  @Override
  public void handleEvent(Event event) {
    if(event.getContext() instanceof Unit unit && event instanceof HealthEvent){
      unit.setTemporaryDefenseValue(unit.getTemporaryDefenseValue() + SHIELDBUFF);
    }
  }

  /**
   * Returns the value of the shieldbuff
   *
   * @return The value of the shieldbuff
   */
  public static int getSHIELDBUFF() {
    return SHIELDBUFF;
  }
}
