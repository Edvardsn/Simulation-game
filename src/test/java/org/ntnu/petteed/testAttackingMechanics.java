package org.ntnu.petteed;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class testAttackingMechanics {

  @Test
  public void correctHealthAfterAttack() {
    Unit attacker = new InfantryUnit("testGuy", 100);
    Unit receiver = new InfantryUnit("poorVictim", 100);

    attacker.attack(receiver);

    assertEquals(94, receiver.getHealth());
  }

  @Test
  public void correctHealthValue() {
    Unit unit = new InfantryUnit("unitsen", 100);
    unit.setHealth(50);

    assertEquals(50, unit.getHealth());
  }

  @Test
  public void rangedUnitDefensiveBonus() {
    RangedUnit unit1 = new RangedUnit("unisten", 100);
    RangedUnit unit2 = new RangedUnit("test", 100);

    assertEquals(6, unit1.getResistBonus());

    unit2.attack(unit1);

    assertEquals(4, unit1.getResistBonus());

    unit2.attack(unit1);

    assertEquals(2, unit1.getResistBonus());

  }

  @Test
  public void cavalryUnitAttackBonus() {
    CavalryUnit unit1 = new CavalryUnit("unit1", 100);
    CavalryUnit unit2 = new CavalryUnit("unit2", 100);

    assertEquals(6, unit1.getAttackBonus());

    unit1.attack(unit2);

    assertEquals(2, unit1.getAttackBonus());
  }


}
