package org.ntnu.petteed.Model.Units;

import java.util.List;
import java.util.Random;
import org.ntnu.petteed.Model.Actor;
import org.ntnu.petteed.Model.RandomFactory;
import org.ntnu.petteed.Model.ShieldEffect;
import org.ntnu.petteed.Model.Unit;

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
   * Represents when an entity acts
   *
   * @param target Optional target for actions
   */
  @Override
  public void act(Object target) {
    shieldAlly();
  }

  public void shieldAlly(){

    List<Actor> units = getFriendlyActors()
        .stream()
        .filter(Unit.class::isInstance)
        .toList();

    int randomIndex = RandomFactory.getRandomInteger(units.size());

    Unit ally = (Unit) units.get(randomIndex);

    ally.eventManager.addEventListener(new ShieldEffect());
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
