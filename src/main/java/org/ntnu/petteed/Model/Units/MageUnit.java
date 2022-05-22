package org.ntnu.petteed.Model.Units;

import java.util.Arrays;
import java.util.List;
import org.ntnu.petteed.Model.Actor;
import org.ntnu.petteed.Model.Effects.BurnEffect;
import org.ntnu.petteed.Model.EventMechanics.EventListener;
import org.ntnu.petteed.Model.Effects.FreezeEffect;
import org.ntnu.petteed.Model.RandomFactory;
import org.ntnu.petteed.Model.Unit;

/**
 * This class represents a Mage Unit which has the unique ability to cast spells onto other units
 *
 */
public class MageUnit extends Unit {

  private static final int ATTACK_VALUE = 5;
  private static final int ARMOUR_VALUE = 5;

  private final EventListener[] spells = {new BurnEffect(),new FreezeEffect()};

  /**
   * Creates a mage unit
   *
   * @param name The name of the unit
   * @param health The health of the unit
   */
  public MageUnit(String name, int health){
    super(name,health,ATTACK_VALUE,ARMOUR_VALUE);
  }

  /**
   * Copy constructor of MageUnit
   *
   * @param unit The unit to copy
   */
  public MageUnit(Unit unit){
    super(unit);
  }

  /**
   * @param target Optional target for actions
   */
  @Override
  public void act(Object target) {
    super.act(target);

    if(target instanceof Unit unit){
      unit.eventManager.addEventListener(castRandomSpell());
    }
  }

  /**
   * Copies the Actor
   *
   * @return A copied version of the actor
   */
  @Override
  public Actor copy() {
    return new MageUnit(this);
  }

  /**
   * Does a roll on all available spells and returns a statusEffect
   *
   * @return A random status effect from the available spells
   */
  public EventListener castRandomSpell(){
    List<EventListener> spellList = Arrays.stream(spells).toList();

    int randomSpellIndex = RandomFactory.getRandomInteger(spellList.size());

    return spellList.get(randomSpellIndex);
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
