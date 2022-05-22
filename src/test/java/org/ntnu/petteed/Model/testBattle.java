package org.ntnu.petteed.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Collection;
import org.junit.jupiter.api.Test;

/**
 * This class performs the following tests to ensure that the Battle class is behaving as intended.
 *
 * Positive tests:
 * <ul>
 * <li>testBattleValues, tests that a battle can be created with correct fields.</li>
 * <li>testCorrectBattleScenario, tests that the correct winner is returned from a battle scenario </li>
 *</ul>
 *
 * Negative tests:
 * <ul>
 * <li>testBattleWithNullArmies, tests that a battle cannot be created with null armies </li>
 *</ul>
 */
public class testBattle {

  /**
   * Tests that a battle is created with correct fields
   *
   */
  @Test
  void testBattleValues(){
    Army testArmy1 = new Army("army",testArmy.createTestActors());
    Army testArmy2 = new Army("army",testArmy.createTestActors());
    Terrain testTerrain = new Terrain("Forest");

    Battle testBattle = new Battle(testArmy1,testArmy2,testTerrain);

    assertEquals(testBattle.getArmyOne(),testArmy1);
    assertEquals(testBattle.getArmyTwo(),testArmy2);
    assertEquals(testBattle.getTerrain(),testTerrain);
  }

  /**
   * Tests that correct battle scenario is conveyed from battles
   *
   */
  @Test
  void testCorrectBattleScenario(){
    Army aliveArmy = new Army("army",testArmy.createTestActors());

    // Populating Dead army
    Collection<Actor> deadActors = new ArrayList<>();
    deadActors.add(new MockUnit("deadUnit",0,1,1));
    Army deadArmy = new Army("army",deadActors);

    Terrain testTerrain = new Terrain("Forest");

    Battle intendedWinnerArmyOne = new Battle(aliveArmy,deadArmy,testTerrain);
    Battle intendedTieBreaker = new Battle(deadArmy,deadArmy,testTerrain);
    Battle intendedWinnerArmyTwo = new Battle(deadArmy,aliveArmy,testTerrain);

    assertEquals(intendedWinnerArmyOne.getArmyOne(),intendedWinnerArmyOne.battle());
    assertNull(intendedTieBreaker.battle());
    assertEquals(intendedWinnerArmyTwo.getArmyTwo(),intendedWinnerArmyTwo.battle());
  }

  /**
   * Tests that a battle will throw an exception if created with null armies
   *
   */
  @Test
  void testBattleWithNullArmies(){
    Terrain testTerrain = new Terrain("Forest");

    assertThrows(IllegalArgumentException.class, ()-> {
      new Battle(null,null,testTerrain);
    });
  }

  /**
   * Tests that a battle will throw an exception if created with null terrain
   *
   */
  @Test
  void testBattleWithNullTerrain(){
    Army testArmy1 = new Army("army",testArmy.createTestActors());
    Army testArmy2 = new Army("army",testArmy.createTestActors());

    assertThrows(IllegalArgumentException.class, ()-> {
      new Battle(testArmy1,testArmy2,null);
    });
  }
}
