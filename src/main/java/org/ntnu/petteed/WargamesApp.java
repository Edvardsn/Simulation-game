package org.ntnu.petteed;

import org.ntnu.petteed.Model.Army;
import org.ntnu.petteed.Model.Battle;
import org.ntnu.petteed.Model.Terrain;
import org.ntnu.petteed.Model.Unit;
import org.ntnu.petteed.Model.UnitFactory;
import org.ntnu.petteed.Model.UnitType;


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
    try {
      Army humans = new Army("Humans", UnitFactory.createUnits(10,"Dude", 100, UnitType.INFANTRYUNIT));
      Army orcs = new Army("Orcs",UnitFactory.createUnits(10,"Dude", 100, UnitType.INFANTRYUNIT));
      Battle battle = new Battle(humans, orcs,new Terrain("FOREST"));

      Army winningArmy = battle.simulate();

     winningArmy.getInfantryUnits().forEach(System.out::println);
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }

//
//    List<Unit> unitList = UnitFactory.createUnits(5,"test",100, UnitType.MAGEUNIT);
//    unitList.forEach(System.out::println);
  }
}

