package org.ntnu.petteed.Model;

/**
 * This class represents an event that has the aim of conveying information between objects when
 * certain events of a specific type happens.
 *
 * @author Student number
 * @version 04/05/22
 */
public abstract class Event {

  private final Object context;

  /**
   * Creates an event
   *
   * @param context Context regarding the event if needed
   */
  protected Event(Object context){
    this.context = context;
  }

  /**
   * Returns any necessary context regarding event if needed
   *
   * @return The context regarding event
   */
  public Object getContext() {
    return context;
  }
}
