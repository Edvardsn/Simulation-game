package org.ntnu.petteed.Model;

public class ShieldEffect implements HealthEventListener{
  /**
   * A reaction to the given health event
   *
   * @param healthEvent
   */
  @Override
  public void handleHealthEvent(HealthEvent healthEvent) {
    if(healthEvent.getContext() instanceof Unit unit){
      unit.setHealth(unit.getHealth() + 10);
    }
  }
}
