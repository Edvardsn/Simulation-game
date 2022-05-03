package org.ntnu.petteed.Model.Units;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.ntnu.petteed.Model.ActionEventListener;
import org.ntnu.petteed.Model.BurnEffect;
import org.ntnu.petteed.Model.FreezeEffect;
import org.ntnu.petteed.Model.Unit;

/**
 * This class represents a Mage Unit
 *
 */
public class MageUnit extends Unit {

  private static final int ATTACK_VALUE = 5;
  private static final int ARMOUR_VALUE = 5;
  private final Random randomGenerator = new Random();

  private final ActionEventListener[] spells = {new BurnEffect(),new FreezeEffect()};

  /**
   * Does a roll on all available spells and returns a statusEffect
   *
   * @return A random status effect from the available spells
   */
  public ActionEventListener castRandomSpell(){
    List<ActionEventListener> spellList = Arrays.stream(spells).toList();
    int randomSpellIndex = randomGenerator.nextInt(spellList.size());
    return spellList.get(randomSpellIndex);
  }

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
   * Attacks another unit and casts spell
   *
   * @param opponent {@code Unit} opponent, the unit to attack
   */
  @Override
  protected void attack(Unit opponent) {
    super.attack(opponent);
    opponent.eventManager.addEventListener(castRandomSpell());
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

    int resistBonus = 0;

    if (receivedAttacks < 2) {
      resistBonus += 4 - (2 * receivedAttacks);
    }

    return resistBonus;
  }
}
