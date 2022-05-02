package org.ntnu.petteed.Model;

public abstract class Event {

  private Object context;

  protected Event(Object context){
    this.context = context;
  }

  public Object getContext() {
    return context;
  }
}
