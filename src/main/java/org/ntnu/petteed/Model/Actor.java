package org.ntnu.petteed.Model;


/**
 * The actor interface represents objects who have any kind of action
 *
 * !!!!!!!!!!!!!! cloneable interface
 */
public interface Actor {

  /**
   *  Initiates an action by the actor
   *
   * @param target Optional target for actions
   */
  void act(Object target);

  /**
   * Checks whether the actor is alive or not.
   */
  boolean isAlive();

  /**
   * Copies the Actor
   *
   * @return A copied version of the actor
   */
  Actor copy();

}
