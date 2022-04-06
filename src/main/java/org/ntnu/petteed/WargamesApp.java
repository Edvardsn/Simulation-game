package org.ntnu.petteed;

import java.io.IOException;
import java.net.URL;
import java.security.SecureClassLoader;
import org.ntnu.petteed.Model.Army;
import org.ntnu.petteed.Model.Battle;
import org.ntnu.petteed.Model.BattleCondition;
import org.ntnu.petteed.Model.InfantryUnit;
import org.ntnu.petteed.Model.Unit;

/**
 * The application which the program runs on
 * JDK 17.02 feil med dependencies, Java relatert
 */
public class WargamesApp {

  /**
   * Starts the application
   *
   * @param args List of arguments
   */
  public static void main(String[] args) {
//    try {
//      Army humans = new Army("Humans", Army.createUnits("Dude", 10, Army.unitType.INFANTRY));
//      Army orcs = new Army("Orcs", Army.createUnits( "Urulg", 10, Army.unitType.INFANTRY));
//      Battle battle = new Battle(humans, orcs,"FOREST");
//
//      Army winningArmy = battle.simulate();
//
//     winningArmy.getInfantryUnits().forEach(System.out::println);
//    } catch (IllegalArgumentException e) {
//      System.out.println(e.getMessage());
//    }

    Class[] classes = Unit.class.getPermittedSubclasses().;

    SecureClassLoader loader = new SecureClassLoader();

    for(Class c : classes){
      System.out.println(c.getSimpleName());
      // get constructor på  class
      // Class loader
    }
  }
}
