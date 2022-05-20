package org.ntnu.petteed.Model;

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
   * Returns the UnitType corresponding to given string
   *
   * @param string The string which unitType to get value of
   * @return The unittype of the string, or null if nonexistent.
   */
  public static ActorType valueOfString(String string){
    for(ActorType type : values()){
      if(type.toString().equals(string)){
        return type;
      }
    }
    return null;
  }

}
