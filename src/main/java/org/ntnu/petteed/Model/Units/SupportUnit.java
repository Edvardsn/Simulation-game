package org.ntnu.petteed.Model.Units;

import java.util.List;
import org.ntnu.petteed.Model.Actor;
import org.ntnu.petteed.Model.RandomFactory;
import org.ntnu.petteed.Model.Effects.ShieldEffect;
import org.ntnu.petteed.Model.Unit;

/**
 * This class represents the SupportUnit which is a unique unit that will buff its allies.
 *
 * @author Nr. 10049
 * @version 04/05/22
 */
public class SupportUnit extends Unit {

  private static final int ATTACK_VALUE = 5;
  private static final int ARMOUR_VALUE = 5;

  /**
   * Creates an instance of SupportUnit
   *
   * @param name The name of the unit
   * @param health The health of the unit
   * @throws IllegalArgumentException If there are any invalid parameters
   */
  public SupportUnit(String name, int health) {
    super(name, health, ATTACK_VALUE, ARMOUR_VALUE);
  }

  /**
   * Copy constructor for supportUnit
   *
   * @param copy The unit to copy
   */
  public SupportUnit(Unit copy){
    super(copy);
  }

  /**
   * Represents when an entity acts
   *
   * @param target Optional target for actions
   */
  @Override
  public void act(Object target) {
   shieldAlly();
  }

  /**
   * Copies the Actor
   *
   * @return A copied version of the actor
   */
  @Override
  public Actor copy() {
    return new SupportUnit(this);
  }

  /**
   * Applies a shield to a random ally
   *
   */
  public void shieldAlly(){
    if(this.currentArmy != null){
      List<Actor> actors = this.currentArmy.getActors()
          .stream()
          .filter(Unit.class::isInstance)
          .filter(actor -> !(actor.equals(this)))
          .toList();

      int randomIndex = RandomFactory.getRandomInteger(actors.size());

      Unit ally = (Unit) actors.get(randomIndex);

      ally.eventManager.addEventListener(new ShieldEffect());
    }
  }

  /**
   * Returns the attack bonus of the unit
   *
   * @return The attack bonus of the unit
   */
  @Override
  public int getAttackBonus() {
    return 0;
  }

  /**
   * Returns the resist bonus of the unit
   *
   * @return The resist bonus of the unit
   */
  @Override
  public int getResistBonus() {
    return 0;
  }

}
