package org.ntnu.petteed.Model.EventMechanics;

/**
 * This interface represents a category of eventListeners which
 * handles healthEvents
 *
 */
public interface HealthEventListener extends EventListener{

  @Override
  void handleEvent(Event event);

}
