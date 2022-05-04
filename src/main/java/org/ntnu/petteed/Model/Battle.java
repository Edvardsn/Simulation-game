package org.ntnu.petteed.Model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
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
  private final Random randomCombatGen;

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
  public Battle(Army armyOne, Army armyTwo,Terrain battleTerrain) throws IllegalArgumentException {
    if (armyOne.getAll() == null ||
        armyTwo.getAll() == null) {
      throw new IllegalArgumentException(
          "Invalid parameters in constructor for Battle");
    } else {
      this.armyOne = armyOne;
      this.armyTwo = armyTwo;
      this.terrain = battleTerrain;
      this.randomCombatGen = new Random();

      assignTerrain();
    }
  }

  /**
   * Returns a collection of the members of the battle
   *
   * @return A collection of the battling members
   */
  public Collection<Army> getBattlingMembers(){
    Set<Army> armies = new HashSet<>();
    armies.add(armyOne);
    armies.add(armyTwo);

    return armies;
  }

  /**
   * Assigns the terrain of the battle to its members
   *
   */
  public void assignTerrain(){
    getBattlingMembers().forEach(army -> army.setCurrentTerrain(this.terrain));
  }

  /**!!!!!!!!!!!!!!!!! attack mellom armies istede, battle veit ikke om units
   * Carries out a duel between two units
   *
   * @param unitArmyOne First unit of the duel
   * @param unitArmyTwo Second unit of the duel
   * @param combatOrder An integer representing the order of combat
   */
  private void unitsBattle(Unit unitArmyOne, Unit unitArmyTwo, int combatOrder) {
    if(combatOrder == 1 && unitArmyOne != null && unitArmyTwo != null){
      unitArmyOne.attack(unitArmyTwo);
      unitArmyTwo.attack(unitArmyOne);
    }
    else if(unitArmyOne != null && unitArmyTwo != null) {
      unitArmyTwo.attack(unitArmyOne);
      unitArmyOne.attack(unitArmyTwo);
    }
  }

  /**
   * Returns a random value between 0 and 1 representing combat order
   *
   * @return {@code Integer} Random value between 0 and 1
   */
  private int getRandomCombatOrder() {
    return randomCombatGen.nextInt(2);
  }

  /**
   * Checks what type of scenario represented by an integer value
   *
   * @return A value representing the scenario of the combat
   */
  private int getScenarioCombat() {

    int scenario;

    if (!this.armyOne.hasHealthyActors()) {
      scenario = ARMY_TWO_WINNER;
    }
    else if (!this.armyTwo.hasHealthyActors()) {
      scenario = ARMY_ONE_WINNER;
    }
    else if (!(this.armyOne.hasHealthyActors()) && !(this.armyTwo.hasHealthyActors())) {
      scenario = TIE;
    }
    else{
      scenario = 0;
    }
    return scenario;
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

        armiesBattle(this.armyOne,this.armyTwo);

        switch (getScenarioCombat()) {
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
          default -> {
          }
        }
      }
      return winner;
    }


    public void armiesBattle(Army armyOne,Army armyTwo){
      Actor actorArmyOne = armyOne.getRandom();
      Actor actorArmyTwo = armyTwo.getRandom();

      unitsBattle((Unit) actorArmyOne, (Unit) actorArmyTwo, getRandomCombatOrder());
    }


}
