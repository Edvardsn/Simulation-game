package org.ntnu.petteed.Model;

/**
 * This class represents a Freeze debuff which is a Status effect that
 * assigns a attackdebuff to a its given unit.
 *
 */
public class Freeze implements StatusEffect {

  private static final int DURATION = 5;
  private int ticks = 0;

  private int attackDebuff = -5;


  /**
   * Registers that a unit has initiated an acton
   *
   * @param unitInitiatingAction The object which initiates an action
   */
  @Override
  public void initiatesAction(Unit unitInitiatingAction) {
    apply(unitInitiatingAction);
  }


  /**
   * Applies the freeze debuff to given unit
   *
   * @param unit The unit which the effect if applied to
   */
  @Override
  public void apply(Unit unit) {
    if(ticks < DURATION){
      attackDebuff += 1;

      unit.setConditionalAttackValue(unit.getConditionalAttackValue() + attackDebuff);
      ticks++;
    }
    else{
      unit.removeStatusEffect(this);
    }
  }
}
