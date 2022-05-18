package org.ntnu.petteed.Model;

public enum UnitType {

  INFANTRY_UNIT("InfantryUnit"), RANGED_UNIT("RangedUnit"), CAVALRY_UNIT("CavalryUnit"), COMMANDER_UNIT("CommanderUnit"),
  MAGE_UNIT("MageUnit"),SUPPORT_UNIT("SupportUnit");

  private final String typeOfUnit;

  /**
   * Creates the unit type corresponding to given enum
   *
   * @param unitType The type of unit to refer to
   */
  UnitType(String unitType) {
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
}
