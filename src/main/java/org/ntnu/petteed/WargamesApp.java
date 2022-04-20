package org.ntnu.petteed;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import org.ntnu.petteed.Model.InfantryUnit;
import org.ntnu.petteed.Model.MageUnit;
import org.ntnu.petteed.Model.Unit;
import org.ntnu.petteed.Model.UnitFactory;


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
  public static void main(String[] args)
      throws InvocationTargetException, NoSuchMethodException, InstantiationException,
      IllegalAccessException {
//    try {
//      Army humans = new Army("Humans", Army.createUnits("Dude", 10, Army.unitType.INFANTRY));
//      Army orcs = new Army("Orcs", Army.createUnits( "Urulg", 10, Army.unitType.INFANTRY));
//      Battle battle = new Battle(humans, orcs,new Terrain("FOREST"));
//
//      Army winningArmy = battle.simulate();
//
//     winningArmy.getInfantryUnits().forEach(System.out::println);
//    } catch (IllegalArgumentException e) {
//      System.out.println(e.getMessage());
//    }

    //UnitFactory factory = new UnitFactory();

    //List<Unit> units = factory.createUnits(10,"dude",100, InfantryUnit.class);
    //units.forEach(System.out::println);

    Class clsarray[] =  {String.class,Integer.TYPE};  //creating array
    Constructor cnstrctr = InfantryUnit.class.getConstructor(clsarray); //get the constructor
    InfantryUnit petter = (InfantryUnit) cnstrctr.newInstance("Petter",100);
    System.out.println(petter.getName() + petter.getAttackValue());
    System.out.println(cnstrctr); //print the constructor


    List<Unit> unitList = UnitFactory.createUnits(5,"test",100, MageUnit.class);
    unitList.forEach(System.out::println);
  }
}

