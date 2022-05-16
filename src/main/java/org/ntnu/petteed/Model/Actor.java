package org.ntnu.petteed.Model;


/**
 * The actor interface represents objects who have any kind of action
 *
 */
public interface Actor {

  /**
   * @param target Optional target for actions
   */
  void act(Object target);


  /**
   * Checks whether the actor is alive or not.
   */
  boolean isAlive();

}
