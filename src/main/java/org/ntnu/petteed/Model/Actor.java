package org.ntnu.petteed.Model;


public interface Actor {

  /**
   * @param target Optional target for actions
   */
  void act(Object target);

  /**
   * Returns whether an actor is dead or alive
   *
   * @return True if the actor is alive, false if not.
   */
  boolean isAlive();
}
