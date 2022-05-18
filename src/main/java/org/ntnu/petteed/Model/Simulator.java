package org.ntnu.petteed.Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Simulator implements BattleSimulator {

  private Collection<Battle> battles;

  private Collection<Army> armies;

  private Army currentArmy;


  public Simulator() {
    this.battles = new ArrayList<>();
    this.armies = new ArrayList<>();
    this.currentArmy = null;
  }

  public Army getCurrentArmy() {
    return currentArmy;
  }

  /**
   * Creates a unit
   *
   * @param name The name of the unit to create
   * @param unitType The type of unit to create
   */
  @Override
  public void createUnit(String name, UnitType unitType) {
    this.currentArmy.addUnit(UnitFactory.createSingleUnit(name,100,unitType));
  }

  /**
   * Removes a unit
   *
   * @param unit The unit to remove
   */
  @Override
  public void removeUnit(Unit unit) {
    this.currentArmy.remove(unit);
  }

  /**
   * Creates an army
   *
   * @param name The army to create
   * @param actors Actors in the new army
   */
  @Override
  public void createArmy(String name, Collection<Actor> actors) {
    currentArmy = new Army(name, actors);
  }

  /**
   * Clears any current army being edited on
   */
  @Override
  public void resetArmy() {
    currentArmy.getAll().clear();
  }

  /**
   * Creates a battle to simulate
   *
   * @param firstArmy  The first member of the battle
   * @param secondArmy The second member of the battle
   */
  @Override
  public void createBattle(Army firstArmy, Army secondArmy) {
    battles.add(new Battle(firstArmy,secondArmy,null));
  }

  /**
   * Returns the members of a battle if created
   *
   * @return The members of the battle
   */
  @Override
  public Iterator<Battle> getBattles() {
    return battles.iterator();
  }

  /**
   * Initiates a battle
   *
   * @return The winning army from the battle
   */
  @Override
  public Army battle() {
    return null;
  }
}
