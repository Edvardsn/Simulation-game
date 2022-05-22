package org.ntnu.petteed.Model.EventMechanics;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * This class serves the purpose of managing subscribers/listeners that wishes to be notified in the
 * event of any change in state regarding other objects.
 *
 * @author Student number
 * @version 02/05/22
 */
public class EventManager {

  private final Collection<EventListener> listeners;

  /**
   * Creates an event manager
   *
   */
  public EventManager() {
    listeners = new CopyOnWriteArraySet<>(); // Thread safe collection
  }

  /**
   *
   *  Adds specified listener to the collection of subscribers
   *
   * @param listener   The <code>listener</code> parameter takes an interface
   *                   implemented by the user which contains the methods to be called
   *                   when the event occurs.
   */
  public void addEventListener(EventListener listener) {
    listeners.add(listener);
  }

  /**
   * Removes specified listener from the eventmanager
   *
   * @param listener   The <code>EventListener</code> parameter indicates the
   *                   <code>Listener </code> to be removed.
   */
  public void removeEventListener(EventListener listener) {
    this.listeners.remove(listener);
  }

  /**
   * Notifies all listeners of a specified event
   *
   * @param event The event to notify the listeners
   */
  public void notifyListeners(Event event){

    if(event instanceof ActionEvent actionEvent){
      listeners.stream()
          .filter(ActionEventListener.class::isInstance)
          .forEach(eventListener ->  eventListener.handleEvent(actionEvent));
    }

    if(event instanceof HealthEvent healthEvent){
      listeners.stream()
          .filter(HealthEventListener.class::isInstance)
          .forEach(eventListener -> eventListener.handleEvent(healthEvent));
    }
  }

  /**
   * Returns an iterator over the eventmanagers subscribers
   *
   * @return An iterator over the eventmanagers subscribers
   */
  public Iterator<EventListener> getListenersIterator(){
    return listeners.iterator();
  }
}