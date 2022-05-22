package org.ntnu.petteed.Model.EventMechanics;

/**
 * An interface to be notified whenever an event takes place.
 *
 */
public interface EventListener {

  /**
   * Handles the given event
   *
   * @param event The event to handle
   */
  void handleEvent(Event event);
}
