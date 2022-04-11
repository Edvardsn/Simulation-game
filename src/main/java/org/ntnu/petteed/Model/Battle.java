package org.ntnu.petteed.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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

  private final String terrain;

  private final String[] terrains = {"HILLS","FOREST","PLAINS"};

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

      assignBattleConditions();
    }
  }

  /**
   * Initializes every member in the battle
   *
   */
  public void assignBattleConditions(){
    getBattlingMembers().forEach(army -> army.getAll()
        .forEach(unit -> unit.addBattleCondition(BattleCondition.getBuilder()
            .setTerrain(terrain) // Sets all current battle effects on units here
            .build())));
  }


  /**
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
   * Checks what type of scenario represented by an integer value
   *
   * @return A value representing the scenario of the combat
   */
  private int getScenarioCombat() {

    int scenario;

    if (!this.armyOne.hasHealthyUnits()) {
      scenario = ARMY_TWO_WINNER;
    }
    else if (!this.armyTwo.hasHealthyUnits()) {
      scenario = ARMY_ONE_WINNER;
    }
    else if (!(this.armyOne.hasHealthyUnits()) && !(this.armyTwo.hasHealthyUnits())) {
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

        Unit unitArmyOne = armyOne.getRandom();
        Unit unitArmyTwo = armyTwo.getRandom();

        int combatOrder = randomCombatGen.nextInt(2);

        unitsBattle(unitArmyOne, unitArmyTwo, combatOrder);

        int battleScenario = getScenarioCombat();

        switch (battleScenario) {
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
   * Returns a collection of the members of the battle
   *
   * @return A collection of the battling members
   */
  public Collection<Army> getBattlingMembers(){
    ArrayList<Army> armies = new ArrayList<>();
    armies.add(armyOne);
    armies.add(armyTwo);

    return armies;
  }



  /**
   * Returns the terrain of the battle
   *
   * @return The terrain of the battle
   */
  public String getTerrain() {
    return terrain;
  }

}
