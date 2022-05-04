package org.ntnu.petteed.Model;

public interface HealthEventListener extends EventListener{

  /**
   * A reaction to the given health event
   *
   */
  void handleHealthEvent(HealthEvent healthEvent);
}
