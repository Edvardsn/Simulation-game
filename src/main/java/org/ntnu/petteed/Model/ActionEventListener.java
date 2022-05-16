package org.ntnu.petteed.Model;

/**
 * The {@code ActionEventListener} interface represents an object who wishes to know about events of the type
 * {@code ActionEvent}.
 *
 * @author Petter Edvardsen
 * @version 02/04/22
 */
public interface ActionEventListener extends EventListener {

  /**
   * A reaction to given action event
   *
   * @param event Necessary context of the event
   */
  void handleActionEvent(ActionEvent event);
}
