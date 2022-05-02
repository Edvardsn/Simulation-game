package org.ntnu.petteed.Model;

/**
 * Represents a collection of effects which holds information about anything that can impact
 * the combat during battle or alter a unit's capabilities.
 *
 * @author Petter Edvardsen
 * @version 02/04/22
 */
public interface ActionEventListener {

  /**
   * Notifies that an action has been initiated
   *
   * @param event Necessary context of the event
   */
  void handleEvent(ActionEvent event);
}
