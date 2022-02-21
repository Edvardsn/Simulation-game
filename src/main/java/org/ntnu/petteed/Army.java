package org.ntnu.petteed;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Army {

  private final String name;
  private List<Unit> units;
  private ArrayList<Unit> healthyUnits;
  private final Random randomGenerator = new Random();

  /**
   * Creates and instance of an Army
   *
   * @param name The name of the army
   */
  public Army(String name) {
    this.name = name;

  }

  /**
   * Creates and instance of an Army
   *
   * @param name  The name of the army
   * @param units The list of units in the army
   */
  public Army(String name, List<Unit> units) {
    this.name = name;
    this.units = units;
    this.healthyUnits = new ArrayList<>();
    initializeHealthyUnits();
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
    units.remove(unit);
  }

  /**
   * Checks if the army has healthy units
   *
   * @return True if any number of units present, false if not.
   */
  public boolean hasHealthyUnits() {
    return (!healthyUnits.isEmpty());
  }

  /**
   * Returns a random Unit from the army
   *
   * @return A random unit from army
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

  @Override
  public int hashCode() {
    return Objects.hash(name, units);
  }

  @Override
  public String toString() {
    return "Army{" +
        "name='" + name + '\'' +
        ", units=" + units +
        "}'";
  }

  /**
   *
   * Creates test units for armies
   * 1. InfantryUnit
   * 2. RangedUnit
   * 3. CavalryUnit
   *
   * @param nameUnits Name of the units
   * @param quantity How many of specified unit
   * @param chosenClass A number representing what class to be made
   * @return Army the army to be created
   */
  public static Army createTestUnits(String armyName, String nameUnits, int quantity, int chosenClass) {

    int counter = 0;

    List<Unit> list = new ArrayList<>();

    if (chosenClass == 1) {
      while (counter < quantity) {
       String numberedName = nameUnits + (counter + 1);
        list.add(new InfantryUnit(numberedName, 100));
        counter++;
      }
    }
      if (chosenClass == 2) {
        while (counter < quantity) {
          String numberedName = nameUnits + (counter + 1);
          list.add(new RangedUnit(numberedName, 100));
          counter++;
        }
      }
        if (chosenClass == 3) {
          while (counter < quantity) {
            String numberedName = nameUnits + (counter + 1);
            list.add(new CavalryUnit(numberedName, 100));
            counter++;
          }
        }

        Army newArmy =  new Army(armyName,list);
        newArmy.initializeHealthyUnits();

    return new Army(armyName,list);
  }
}