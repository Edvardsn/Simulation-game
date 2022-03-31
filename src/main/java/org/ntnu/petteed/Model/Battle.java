package org.ntnu.petteed.Model;

import java.util.Arrays;
import java.util.Random;

/**
 * Represents a battle between two armies
 *
 */
public class Battle {

  private final Army armyOne;
  private final Army armyTwo;
  private final Random randomCombatGen;
  private static final int ARMY_ONE_WINNER = 1;
  private static final int ARMY_TWO_WINNER = 2;
  private static final int TIE = 3;
  private static final String[] terrains = {"HILLS","FOREST","PLAINS"};
  private final String terrain;

  /**
   * Creates an instance of a battle
   *
   * @param armyOne The first battling army
   * @param armyTwo The second battling army
   */
  public Battle(Army armyOne, Army armyTwo,String battleTerrain) throws IllegalArgumentException {
    if (armyOne.getAll() == null ||
        !(armyTwo.getAll() != null || !validTerrain(battleTerrain))) {
      throw new IllegalArgumentException(
          "Invalid parameters in constructor for Battle");
    } else {
      this.armyOne = armyOne;
      this.armyTwo = armyTwo;
      this.terrain = battleTerrain;
      this.randomCombatGen = new Random();
    }
  }

  /**
   * Checks if a given String is a valid terrain
   *
   * @param battleTerrain The proposed terrain
   * @return A boolean value which is True if given a valid terrain, false if not.
   */
  private boolean validTerrain(String battleTerrain) {
    return Arrays.asList(terrains).contains(battleTerrain);
  }

  /**
   * Simulates a battle between two armies
   *
   * @return {@code Army}, Returns the winning army
   */
  public Army simulate() {

    boolean battling = true;
    Army winner = null;

    while (battling) {

      Unit unitArmyOne = armyOne.getRandom();
      Unit unitArmyTwo = armyTwo.getRandom();

      int combatOrder = randomCombatGen.nextInt(2);

      if(combatOrder == 1 && unitArmyOne != null && unitArmyTwo != null){
        unitArmyOne.attack(unitArmyTwo);
        unitArmyTwo.attack(unitArmyOne);
      }

      if(combatOrder == 0 && unitArmyOne != null && unitArmyTwo != null) {
        unitArmyTwo.attack(unitArmyOne);
        unitArmyOne.attack(unitArmyTwo);
      }

      int scenario = 0; // Initializes the variable to be used in switch-case

      if (armyOne.hasHealthyUnits()) {
        scenario = ARMY_TWO_WINNER;
      }

      if (armyTwo.hasHealthyUnits()) {
        scenario = ARMY_ONE_WINNER;
      }

      if (!(armyOne.hasHealthyUnits()) && !(armyTwo.hasHealthyUnits())) {
        scenario = TIE;
      }

      switch (scenario) {
        case ARMY_ONE_WINNER -> {
          winner = armyOne;
          battling = false;
        }
        case ARMY_TWO_WINNER -> {
          winner = armyTwo;
          battling = false;
        }
        case TIE -> battling = false;
        default -> {
        }
      }
    }
    return winner;
  }
}
