package org.ntnu.petteed.Model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a battle between two armies and its surrounding information
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
    if (armyOne.getActors() == null ||
        armyTwo.getActors() == null) {
      throw new IllegalArgumentException(
          "Cannot create a battle without two valid armies");
    }
      if(battleTerrain == null || battleTerrain.getTerrainName().isBlank()){
        throw new IllegalArgumentException("Cannot create a battle in a nonexistent terrain");
      }

      this.armyOne = armyOne;
      this.armyTwo = armyTwo;
      this.terrain = battleTerrain;

      assignTerrain();

  }

  /**
   * Simulates a battle between two armies
   *
   * @return {@code Army}, Returns the winning army
   */
  public Army battle() {

    boolean battling = true;
    Army winner = null;

    while (battling) {

      armiesBattle();

      switch (getCombatScenario()) {
        case TIE -> {
          battling = false;
        }
        case ARMY_ONE_WINNER -> {
          winner = armyOne;
          battling = false;
        }
        case ARMY_TWO_WINNER -> {
          winner = armyTwo;
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
   * Initiates one random iteration of an action between the two armies in the battle
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

    if (!(this.armyOne.hasHealthyActors()) && !(this.armyTwo.hasHealthyActors())){
      scenario = TIE;
    } else if (!this.armyOne.hasHealthyActors()) {
      scenario = ARMY_TWO_WINNER;
    } else if (!this.armyTwo.hasHealthyActors()) {
      scenario = ARMY_ONE_WINNER;
    }

    return scenario;
  }

  /**
   * Returns the army number one in the battle
   *
   * @return The army number one in the battle
   */
  public Army getArmyOne() {
    return armyOne;
  }
  /**
   * Returns the army number two in the battle
   *
   * @return The army number two in the battle
   */
  public Army getArmyTwo() {
    return armyTwo;
  }

  /**
   * Returns the terrain of the battle
   *
   * @return The terrain of the battle
   */
  public Terrain getTerrain() {
    return terrain;
  }
}