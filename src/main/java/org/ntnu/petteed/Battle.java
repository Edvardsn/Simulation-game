package org.ntnu.petteed;

public class Battle {

  private final Army armyOne;
  private final Army armyTwo;
  private static final int ARMY_ONE_WINNER = 1;
  private static final int ARMY_TWO_WINNER = 2;
  private static final int TIE = 3;

  /**
   * Creates an instance of a battle
   *
   * @param armyOne The first battling army
   * @param armyTwo The second battling army
   */
  public Battle(Army armyOne, Army armyTwo) throws IllegalArgumentException {
    if (armyOne.getAll() == null || armyTwo.getAll() == null) {
      throw new IllegalArgumentException(
          "Cannot arrange battle with an army who does not have any units");
    } else {
      this.armyOne = armyOne;
      this.armyTwo = armyTwo;
    }
  }

  public Army simulate() {

    boolean battling = true;
    Army winner = null;

    while (battling) {

      Unit unitArmyOne = armyOne.getRandom();
      Unit unitArmyTwo = armyTwo.getRandom();

      if (unitArmyOne != null) {
        unitArmyOne.attack(unitArmyTwo);
      }

      if (unitArmyTwo != null) {
        unitArmyTwo.attack(unitArmyOne);
      }


      int scenario = 0; // Initializes the variable

      if (!armyOne.hasHealthyUnits()) {
        scenario = ARMY_TWO_WINNER;
      }

      if (!armyTwo.hasHealthyUnits()) {
        scenario = ARMY_ONE_WINNER;
      }

      if (!armyOne.hasHealthyUnits() && !armyTwo.hasHealthyUnits()) {
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
