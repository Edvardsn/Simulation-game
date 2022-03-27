package org.ntnu.petteed;

import org.ntnu.petteed.Model.Army;
import org.ntnu.petteed.Model.Battle;

/**
 * The application which the program runs on
 *
 */
public class WargamesApp {

  /**
   * Starts the application
   *
   * @param args List of arguments
   */
  public static void main(String[] args) {
    try {
      Army humans = new Army("test",
          Army.createUnits("Humans", "Dude", 10, Army.unitType.INFANTRY));
      Army orcs = new Army("test",
          Army.createUnits("Orcs", "Urulg", 10, Army.unitType.INFANTRY));

      Battle battle = new Battle(humans, orcs);

      Army winningArmy = battle.simulate();

      System.out.println(winningArmy.toString());
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }
  }
}
