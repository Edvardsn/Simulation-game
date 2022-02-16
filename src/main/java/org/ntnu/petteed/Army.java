package org.ntnu.petteed;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.IntStream;

public class Army {

  private String name;
  private List<Unit> units;

  /**
   * Creates and instance of an Army
   *
   * @param name The name of the army
   */
  public Army(String name){
    this.name = name;

  }

  /**
   * Creates and instance of an Army
   *
   * @param name The name of the army
   * @param units The list of units in the army
   */
  public Army(String name, List<Unit> units){
    this.name = name;
    this.units = units;
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
  public void remove(Unit unit){
    units.remove(unit);
  }

  /**
   * Checks if the army has units
   *
   * @return True if any number of units present, false if not.
   */
  public boolean hasUnits(){
    return (!units.isEmpty());
  }

//  public Unit getRandom(){
//    Random randomGenerator = new Random();
//
//    IntStream numbers = randomGenerator.ints(1);
//  }

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
}



