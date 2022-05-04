package org.ntnu.petteed.Model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a battle between two armies
 *
 * Info:
 * - Armies
 * - Army dead?
 * - Armies fight
 * - Terrain of battle
 *
 */
public class Battle {

  private final Army armyOne;
  private final Army armyTwo;

  private static final int ARMY_ONE_WINNER = 1;
  private static final int ARMY_TWO_WINNER = 2;
  private static final int TIE = 3;
  private final Terrain terrain;


  /**
   * Creates an instance of a battle
   *
   * @param armyOne The first battling army
   * @param armyTwo The second battling army
   */
  public Battle(Army armyOne, Army armyTwo, Terrain battleTerrain) throws IllegalArgumentException {
    if (armyOne.getAll() == null ||
        armyTwo.getAll() == null) {
      throw new IllegalArgumentException(
          "Invalid parameters in constructor for Battle");
    } else {
      this.armyOne = armyOne;
      this.armyTwo = armyTwo;
      this.terrain = battleTerrain;

      assignTerrain();
    }
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

      armiesBattle();

      switch (getCombatScenario()) {
        case ARMY_ONE_WINNER -> {
          winner = armyOne;
          battling = false;
        }
        case ARMY_TWO_WINNER -> {
          winner = armyTwo;
          battling = false;
        }
        case TIE -> {
          battling = false;
        }
        default -> {}
      }
    }
    return winner;
  }

  /**
   * Returns a collection of the members of the battle
   *
   * @return A collection of the battling members
   */
  public Collection<Army> getBattlingMembers() {
    Set<Army> armies = new HashSet<>();
    armies.add(armyOne);
    armies.add(armyTwo);

    return armies;
  }

  /**
   * Assigns the terrain of the battle to its members
   */
  public void assignTerrain() {
    getBattlingMembers().forEach(army -> army.setCurrentTerrain(this.terrain));
  }

  /**
   * Initiates one iteration of a battle between the two armies in the battle
   *
   */
  public void armiesBattle() {
    if (RandomFactory.getRandomInteger(2) == 1) {
      this.armyOne.act(armyTwo);
      this.armyTwo.act(armyOne);
    } else {
      this.armyTwo.act(armyOne);
      this.armyOne.act(armyTwo);
    }
  }

  /**
   * Checks what type of scenario represented by an integer value
   *
   * @return A value representing the scenario of the combat
   */
  private int getCombatScenario() {

    int scenario = 0;

    if (!this.armyTwo.hasHealthyActors()) {
      scenario = ARMY_ONE_WINNER;
    } else if (!this.armyOne.hasHealthyActors()) {
      scenario = ARMY_TWO_WINNER;
    } else if (!(this.armyOne.hasHealthyActors()) && !(this.armyTwo.hasHealthyActors())) {
      scenario = TIE;
    }

    return scenario;
  }

}