package org.ntnu.petteed.Model;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import org.ntnu.petteed.Model.Units.CavalryUnit;
import org.ntnu.petteed.Model.Units.CommanderUnit;
import org.ntnu.petteed.Model.Units.InfantryUnit;
import org.ntnu.petteed.Model.Units.RangedUnit;

public class UnitFactory {

  private static UnitFactory instance = null;

  private UnitFactory(){
  }

  /**
   * Creates an instance of UnitFactory given that no other instance exists.
   *
   * @return The unit factory
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
   * @param numberOfUnits The number of units to create
   * @param name The name of the units
   * @param health The health of the units
   * @param desiredClass The class of the units
   * @return A list of the units created
   * @throws NoSuchMethodException If the method of given class does not exist.
   * @throws InvocationTargetException Exception thrown by any of the methods
   * @throws InstantiationException Thrown if given class does not have a Constructor
   * @throws IllegalAccessException Thrown if user does not have access to reflective commands
   */
  public static List<Unit> createUnits(int numberOfUnits,String name,int health, Class<?extends Unit> desiredClass)
      throws NoSuchMethodException, InvocationTargetException, InstantiationException,
      IllegalAccessException {

    ArrayList<Unit> listOfUnits = new ArrayList<>();
    int counter = 0;
    Class[] constructorType = { String.class ,Integer.TYPE};

    while(counter < numberOfUnits){

      Constructor<?extends Unit> constructor = desiredClass.getConstructor(constructorType);
      Unit newUnit = constructor.newInstance(name,health);

      listOfUnits.add(newUnit);

      counter++;
    }


    return listOfUnits;
  }





}
