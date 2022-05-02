package org.ntnu.petteed.Model;


import java.util.Collection;
import java.util.HashSet;



public class EventManager {

  private Collection<ActionEventListener> listeners;

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
  public void addEventListener(ActionEventListener listener) {
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
   * @param id       Specifies the event type of the <code>EventListener</code>
   *                   being removed.
   * @param listener   The <code>EventListener</code> parameter indicates the
   *                   <code>Listener </code> to be removed.
   */
  public void removeEventListener(ActionEventListener listener) {
    this.listeners.remove(listener);
  }

  /**
   * Notifies all listeners of a specified id
   *
   * @param event The event to notify the listeners
   * @param context Optional context for the listeners regarding the event
   */
  public void notifyListeners(Event event){
    if(event instanceof ActionEvent actionEvent){
      for (ActionEventListener actionEventListener : listeners){
        actionEventListener.handleEvent(actionEvent);
      }
    }
  }
}