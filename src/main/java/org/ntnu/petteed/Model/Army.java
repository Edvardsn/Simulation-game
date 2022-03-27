package org.ntnu.petteed.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Army {

  /**
   * Enums for identifying what type of unit to be created
   *
   */
  public enum unitType {
    INFANTRY,CAVALRY,RANGED,COMMANDER
  }

  private final String name;
  private List<Unit> units;
  private ArrayList<Unit> healthyUnits;
  private final Random randomGenerator = new Random();

  /**
   * Creates and instance of an Army
   *
   * @param name The name of the army
   * @throws IllegalArgumentException, Prohibits creating an Army without a name
   *
   */
  public Army(String name) throws IllegalArgumentException {
    if (name != null) {
      this.name = name;
    } else {
      throw new IllegalArgumentException("Cannot create an Army without a name");
    }
  }

  /**
   * Creates and instance of an Army
   *
   * @param name  The name of the army
   * @param units The list of units in the army
   * @throws IllegalArgumentException, Prohibits creating an Army without a name or units.
   */
  public Army(String name, List<Unit> units) throws IllegalArgumentException {
    if (name != null && units != null) {
      this.name = name;
      this.units = units;
      this.healthyUnits = new ArrayList<>();
      initializeHealthyUnits();
    } else if (name == null) {
      throw new IllegalArgumentException("Cannot create an Army without a name");
    } else {
      throw new IllegalArgumentException("Cannot create an Army without any units");
    }
  }

  /**
   * Updates the status of healthy units in the army
   */
  public void initializeHealthyUnits() {
    for (Unit unit : units) {
      if (unit.isAlive()) {
        healthyUnits.add(unit);
      }
    }
  }

  /**
   * Removes any units who are not alive in the collection healthy units
   */
  public void updateHealthyUnits() {
    healthyUnits.removeIf(unit -> !unit.isAlive());
  }

  /**
   * Returns the name of the army
   *
   * @return The name of the army
   */
  public String getName() {
    return name;
  }

  /**
   * Returns the Units in the army
   *
   * @return The unis in the army
   */
  public List<Unit> getAll() {
    return units;
  }

  /**
   * Removes the specified unit from army
   *
   * @param unit Unit to be removed
   */
  public void remove(Unit unit) {
    if (unit != null) {
      units.remove(unit);
    }
  }

  /**
   * Checks if the army has healthy units
   *
   * @return True if any number of units present, false if not.
   */
  public boolean hasHealthyUnits() {
    return !(healthyUnits.isEmpty());
  }

  /**
   * Returns a random healthy Unit from the army
   *
   * @return A random healthy unit from army
   */
  public Unit getRandom() {
    updateHealthyUnits();
    Unit randomUnit;

    if (healthyUnits.isEmpty()) {
      randomUnit = null;
    } else {
      randomUnit = healthyUnits.get(randomGenerator.nextInt(healthyUnits.size()));
    }
    return randomUnit;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Army army = (Army) o;
    return Objects.equals(name, army.name) && Objects.equals(units, army.units);
  }

  /**
   * Returns a hashcode representing the army
   *
   * @return A hashcode representing the army
   */
  @Override
  public int hashCode() {
    return Objects.hash(name, units);
  }

  /**
   * Creates a description of the army
   *
   * @return A description of the army
   */
  @Override
  public String toString() {
    return "Army{" +
        "name='" + name + '\'' +
        ", units=" + units +
        "}'";
  }

  /**
   * Returns a list of every Infantryunit in the Army
   *
   * @return {@code List<Unit>} , The list of InfantryUnits
   */
  public List<Unit> getInfantryUnits(){
    return units.stream().filter(unit -> unit.getClass() != InfantryUnit.class).toList();
  }

  /**
   * Returns a list of every Rangedunit in the Army
   *
   * @return {@code List<Unit>} , The list of RangedUnits
   */
  public List<Unit> getRangedUnits(){
    return units.stream().filter(unit -> unit.getClass() == RangedUnit.class).toList();
  }

  /**
   * Returns a list of every CavalryUnit in the Army
   *
   * @return {@code List<Unit>} , The list of CavalryUnits
   */
  public List<Unit> getCavalryUnits(){
    return units.stream().filter(unit -> unit.getClass() == CavalryUnit.class).toList();
  }

  /**
   * Returns a list of every Commanderunit in the Army
   *
   * @return {@code List<Unit>} , The list of Commanderunits
   */
  public List<Unit> getCommanderUnits(){
    return units.stream().filter(unit -> unit.getClass() == CommanderUnit.class).toList();
  }

  /**
   * Returns any type of unit
   *
   * @param unitType The type of unit to be created
   * @return {@code List<Unit>} A list of units of the given type
   */
  public List<Unit> getAnyUnitType(Unit unitType){
    return units.stream().filter(unit ->unit.getClass() == unitType.getClass()).toList();
  }

  /**
   * Creates a given number of units of a specified unit type.
   *
   * @param armyName , The name of the Army
   * @param nameUnits ,Name of the units
   * @param quantity ,How many of specified unit
   * @param type ,A number representing what class to be made
   * @return {@code List}, the new Army created
   */
  public static List<Unit> createUnits(String armyName, String nameUnits, int quantity, unitType type) {

    int counter = 0;

    List<Unit> unitList = new ArrayList<>();

    if (type == unitType.INFANTRY) {
      while (counter < quantity) {
        String numberedName = nameUnits + (counter + 1); // Gives a number to created units
        unitList.add(new InfantryUnit(numberedName, 100));
        counter++;
      }
    }
    if (type == unitType.RANGED) {
      while (counter < quantity) {
        String numberedName = nameUnits + (counter + 1);
        unitList.add(new RangedUnit(numberedName, 100));
        counter++;
      }
    }
    if (type == unitType.CAVALRY) {
      while (counter < quantity) {
        String numberedName = nameUnits + (counter + 1);
        unitList.add(new CavalryUnit(numberedName, 100));
        counter++;
      }
    }

    return unitList;
  }

  /**
   * Writes to Army to file
   *
   */
  public void writeArmyToFile(){

  }
}