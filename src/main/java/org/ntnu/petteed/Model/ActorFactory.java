package org.ntnu.petteed.Model;

import java.util.ArrayList;
import java.util.Collection;
import org.ntnu.petteed.Model.Units.CavalryUnit;
import org.ntnu.petteed.Model.Units.CommanderUnit;
import org.ntnu.petteed.Model.Units.InfantryUnit;
import org.ntnu.petteed.Model.Units.MageUnit;
import org.ntnu.petteed.Model.Units.RangedUnit;
import org.ntnu.petteed.Model.Units.SupportUnit;

/**
 * A factory used to create instances of units
 *
 */
public class ActorFactory {

  private static ActorFactory instance = null;

  /**
   * Creates a unit factory
   *
   */
  private ActorFactory(){
  }

  /**
   * Creates an instance of ActorFactory given that no other instance exists.
   *
   * @return The new or existing unit factory
   */
  public static ActorFactory getInstance() {
    if (instance == null) {
      instance = new ActorFactory();
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

  public static Unit createSupportUnit(String name,int health){
    return new SupportUnit(name, health);
  }

  /**
   * Returns a copy of given actor
   *
   * @param actor The actor to create a new instance of
   * @return A copy of the given actor
   */
  public static Actor copy(Actor actor){
    return actor.copy();
  }

  /**
   * Creates unit of specified type, Name and Health.
   *
   * @param actorType The class of the unit
   * @param name The name of the unit
   * @param health The health of the unit
   * @return The unit created
   */
  public static Unit createSingleUnit(ActorType actorType, String name, int health)
  {
    Unit desiredUnit = null;

      switch (actorType) {
        case INFANTRY_UNIT -> desiredUnit = new InfantryUnit(name, health);
        case RANGED_UNIT -> desiredUnit = new RangedUnit(name, health);
        case CAVALRY_UNIT -> desiredUnit = new CavalryUnit(name, health);
        case COMMANDER_UNIT -> desiredUnit = new CommanderUnit(name, health);
        case MAGE_UNIT -> desiredUnit = new MageUnit(name, health);
        case SUPPORT_UNIT -> desiredUnit = new SupportUnit(name, health);
      }

    return desiredUnit;
  }

  /**
   * Creates a list of units of specified type, Name and Health.
   *
   * @param numberOfUnits The number of units to create exclusive given number
   * @param name The name of the units
   * @param health The health of the units
   * @param actorType The class of the units
   * @return A {@code List} of the units created
   */
  // !!!!!! Sjekk at break i case
  public static Collection<Actor> createUnits(int numberOfUnits, String name, int health, ActorType actorType)
     {
       int counter = 0;

       Collection<Actor> listOfUnits = new ArrayList<>();

       while(counter < numberOfUnits) {
         switch (actorType) {
           case INFANTRY_UNIT -> listOfUnits.add(new InfantryUnit(name, health));
           case RANGED_UNIT -> listOfUnits.add(new RangedUnit(name, health));
           case CAVALRY_UNIT -> listOfUnits.add(new CavalryUnit(name, health));
           case COMMANDER_UNIT -> listOfUnits.add(new CommanderUnit(name, health));
           case MAGE_UNIT -> listOfUnits.add(new MageUnit(name, health));
           case SUPPORT_UNIT -> listOfUnits.add(new SupportUnit(name, health));
         }

         counter++;
       }

    return listOfUnits;
  }
}