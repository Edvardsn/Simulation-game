package org.ntnu.petteed.Model;

public enum UnitType {

  INFANTRYUNIT("InfantryUnit"),RANGEDUNIT("RangedUnit"),CAVALRYUNIT("CavalryUnit"),COMMANDERUNIT("CommanderUnit"),MAGEUNIT("MageUnit");

  private String unitType;

  /**
   * Creates the unit type corresponding to given enum
   *
   * @param unitType The type of unit to refer to
   */
  UnitType(String unitType) {
    this.unitType = unitType;
  }

  /**
   * @return The command word as a string.
   */
  @Override
  public String toString()
  {
    return unitType;
  }
}
