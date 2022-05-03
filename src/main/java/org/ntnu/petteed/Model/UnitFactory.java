package org.ntnu.petteed.Model;

import java.util.ArrayList;
import java.util.List;
import org.ntnu.petteed.Model.Units.CavalryUnit;
import org.ntnu.petteed.Model.Units.CommanderUnit;
import org.ntnu.petteed.Model.Units.InfantryUnit;
import org.ntnu.petteed.Model.Units.MageUnit;
import org.ntnu.petteed.Model.Units.RangedUnit;

public class UnitFactory {

  private static UnitFactory instance = null;

  /**
   * Creates a unit factory
   *
   */
  private UnitFactory(){
  }

  /**
   * Creates an instance of UnitFactory given that no other instance exists.
   *
   * @return The new or existing unit factory
   */
  public static UnitFactory getInstance() {
    if (instance == null) {
      instance = new UnitFactory();
    }
    return instance;
  }

  public static Unit createInfantryUnit(String name, int health){
    return new InfantryUnit(name, health);
  }

  public static Unit createRangedUnit(String name, int health){
    return new RangedUnit(name, health);
  }

  public static Unit createCavalryUnit(String name, int health){
    return new CavalryUnit(name, health);
  }

  public static Unit createCommanderUnit(String name, int health){
    return new CommanderUnit(name, health);
  }

  public static Unit createMageUnit(String name, int health){
    return new MageUnit(name, health);
  }

  /**
   * Creates a list of units of specified type, Name and Health.
   *
   * @param numberOfUnits The number of units to create exclusive given number
   * @param name The name of the units
   * @param health The health of the units
   * @param unitType The class of the units
   * @return A {@code List} of the units created
   */
  public static List<Unit> createUnits(int numberOfUnits,String name,int health, UnitType unitType)
     {
       int counter = 0;
       List<Unit> listOfUnits = new ArrayList<>();

       while(counter < numberOfUnits) {
         switch (unitType) {
           case INFANTRYUNIT -> listOfUnits.add(new InfantryUnit(name, health));
           case RANGEDUNIT -> listOfUnits.add(new RangedUnit(name, health));
           case CAVALRYUNIT -> listOfUnits.add(new CavalryUnit(name, health));
           case COMMANDERUNIT -> listOfUnits.add(new CommanderUnit(name, health));
           case MAGEUNIT -> listOfUnits.add(new MageUnit(name, health));
         }

         counter++;
       }

    return listOfUnits;
  }
}
