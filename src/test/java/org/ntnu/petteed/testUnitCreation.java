package org.ntnu.petteed;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class testUnitCreation {

  @Test
  public void unitFields() {
    Unit unit = new InfantryUnit("unitsen", 100);

    assertEquals(100, unit.getHealth());
    assertEquals("unitsen", unit.getName());
    assertEquals(15, unit.getAttack());
    assertEquals(10, unit.getArmour());
  }

  @Test
  public void testNullInConstructor() {
      assertThrows(IllegalArgumentException.class, ()-> {
        Unit unit = new InfantryUnit(null, 100);
      });


  }
}