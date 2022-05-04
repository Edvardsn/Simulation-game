package org.ntnu.petteed.Model;

import java.util.Collection;
import java.util.HashSet;

/**
 * This class serves the purpose of managing subscribers/listeners that wishes to be notified in the
 * event of any change in state regarding other objects.
 *
 * Only deals with actionEvents for now, but can easily be expanded.
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
    listeners = new HashSet<>();
  }

  /**
   * This method allows the registration of event listeners on the event
   * target. If an <code>EventListener</code> is added to an
   * <code>EventTarget</code> while it is processing an event, it will not
   * be triggered by the current actions but may be triggered during a
   * later stage of event flow, such as the bubbling phase.
   * <br> If multiple identical <code>EventListener</code>s are registered
   * on the same <code>EventTarget</code> with the same parameters the
   * duplicate instances are discarded. They do not cause the
   * <code>EventListener</code> to be called twice and since they are
   * discarded they do not need to be removed with the
   * <code>removeEventListener</code> method.
   *
   * @param listener   The <code>listener</code> parameter takes an interface
   *                   implemented by the user which contains the methods to be called
   *                   when the event occurs.
   */
  public void addEventListener(EventListener listener) {
    listeners.add(listener);
  }

  /**
   * This method allows the removal of event listeners from the event
   * target. If an <code>EventListener</code> is removed from an
   * <code>EventTarget</code> while it is processing an event, it will not
   * be triggered by the current actions. <code>EventListener</code>s can
   * never be invoked after being removed.
   * <br>Calling <code>removeEventListener</code> with arguments which do
   * not identify any currently registered <code>EventListener</code> on
   * the <code>EventTarget</code> has no effect.
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
          .forEach(eventListener -> ((ActionEventListener) eventListener).handleActionEvent(actionEvent));
    }
    if(event instanceof HealthEvent healthEvent){
      listeners.stream()
          .filter(HealthEventListener.class::isInstance)
          .forEach(eventListener -> ((HealthEventListener) eventListener).handleHealthEvent(healthEvent));
    }
  }
}