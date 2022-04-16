package org.ntnu.petteed.Model;

import java.util.Arrays;
import java.util.Set;

/**
 * This class represents a Mage Unit
 *
 */
public class MageUnit extends Unit{

  private static final int ATTACK_VALUE = 10;
  private static final int ARMOUR_VALUE = 5;

  private final String[] availableSpells = {"BURN","FREEZE"};

  private Set<String> spells;

  /**
   * Creates a mage unit
   *
   * @param name The name of the unit
   * @param health The health of the unit
   */
  public MageUnit(String name, int health){
    super(name,health,ATTACK_VALUE,ARMOUR_VALUE);
    spells.addAll(Arrays.asList(availableSpells));
  }

  /**
   * Attacks another unit
   *
   * @param opponent {@code Unit} opponent, the unit to attack
   */
  @Override
  protected void attack(Unit opponent) {
    if (opponent != null && this.isAlive() && !(opponent.equals(this))) {

      int totalAttackDamage = this.getAttack() + this.getAttackBonus();

      int totalResistances = opponent.getResistBonus() + opponent.getArmour();

      int trueDamage = totalAttackDamage - totalResistances; // The actual amount deducted from the opponents health

      opponent.setHealth(opponent.getHealth() - trueDamage,this);
      opponent.addStatusEffect(new Burn());

      this.incrementInitiatedAttacks(); // Registers initiated attack
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

    int resistBonus = 0;

    if (receivedAttacks < 2) {
      resistBonus += 4 - (2 * receivedAttacks);
    }

    return resistBonus;
  }
}
