package org.ntnu.petteed;

import java.io.IOException;
import java.net.URL;
import java.security.SecureClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.ntnu.petteed.Model.Army;
import org.ntnu.petteed.Model.Battle;
import org.ntnu.petteed.Model.BattleCondition;
import org.ntnu.petteed.Model.InfantryUnit;
import org.ntnu.petteed.Model.Unit;
import org.reflections.Reflections;

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

    Reflections reflections  = new Reflections("org.ntnu.petteed");

    Set<Class<?extends Unit>> subTypes =  reflections.getSubTypesOf(Unit.class);


    ArrayList<String> strings = new ArrayList<>();

    subTypes.forEach(unit ->
    {
      strings.add(unit.getSimpleName());
    });

    strings.stream().forEach(System.out::println);

      // get constructor på  class
      // Class loader
  }
}

