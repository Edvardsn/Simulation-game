package org.ntnu.petteed.Model.EventMechanics;

/**
 * This class represents an event that occurs whenever a unit initiates an action
 *
 */
public class ActionEvent extends Event{

  /**
   * Creates an action event
   *
   * @param context The context regarding the event if needed
   */
  public ActionEvent(Object context) {
    super(context);
  }
}
