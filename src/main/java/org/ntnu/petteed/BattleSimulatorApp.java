package org.ntnu.petteed;

import org.ntnu.petteed.ui.MainWindow;


/**
 * The application which the program runs on
 * JDK 17.02 feil med dependencies, Java relatert
 */
public class BattleSimulatorApp {

  /**
   * Starts the application
   *
   * @param args List of arguments
   */
  public static void main(String[] args) {
    MainWindow.main(args);
//    try {
//      Army humans = new Army("Humans", ActorFactory.createUnits(10,"Dude", 100, UnitType.INFANTRY_UNIT));
//      Army orcs = new Army("Orcs",ActorFactory.createUnits(10,"Dude", 100, UnitType.INFANTRY_UNIT));
//      Battle battle = new Battle(humans, orcs,new Terrain("FOREST"));
//
//      Army winningArmy = battle.simulate();
//
//     winningArmy.getInfantryUnits().forEach(System.out::println);
//    } catch (IllegalArgumentException e) {
//      System.out.println(e.getMessage());
//    }
//
//    List<Unit> unitList = ActorFactory.createUnits(5,"test",100, UnitType.MAGEUNIT);
//    unitList.forEach(System.out::println);
  }
}

