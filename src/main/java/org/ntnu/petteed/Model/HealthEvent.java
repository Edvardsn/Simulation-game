package org.ntnu.petteed.Model;

/**
 * This class represents an event that is triggered if the health value of a given unit is modified
 * and the unit is still alive afterwards.
 *
 * An increase in health does not trigger a health event.
 */
public class HealthEvent extends Event{

  /**
   * Creates a health event
   *
   * @param context Context regarding the event if needed
   */
  protected HealthEvent(Object context) {
    super(context);
  }
}
