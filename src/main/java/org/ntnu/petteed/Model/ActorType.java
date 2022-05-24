package org.ntnu.petteed.Model;

import java.util.Arrays;
import java.util.List;

/**
 * Enums to identify Actor types
 */
public enum ActorType {

  INFANTRY_UNIT("InfantryUnit"), RANGED_UNIT("RangedUnit"), CAVALRY_UNIT("CavalryUnit"), COMMANDER_UNIT("CommanderUnit"),
  MAGE_UNIT("MageUnit"),SUPPORT_UNIT("SupportUnit");

  private final String typeOfUnit;

  /**
   * Creates the unit type corresponding to given enum
   *
   * @param unitType The type of unit to refer to
   */
  ActorType(String unitType) {
    this.typeOfUnit = unitType;
  }

  /**
   * @return The Unit type as a string.
   */
  @Override
  public String toString()
  {
    return typeOfUnit;
  }

  /**
   * Returns the ActorType corresponding to given string
   *
   * @param identification The string which unitType to get value of
   * @return The Actortype corresponding to the string, or null if nonexistent.
   * @throws IllegalArgumentException if no matching type to given identification
   */
  public static ActorType valueOfString(String identification) throws IllegalArgumentException{
    List<ActorType> types = Arrays.stream(values()).toList();

    ActorType matchingType = null;

    int index = 0;

    boolean found = false;

    while(!found && index < types.size()){

      if(types.get(index).toString().equals(identification)){

        matchingType = types.get(index);

        found = true;
      }
      index++;
    }

    if(matchingType == null){
      throw new IllegalArgumentException("No matching Actortype");
    }

    return matchingType;
  }

}
