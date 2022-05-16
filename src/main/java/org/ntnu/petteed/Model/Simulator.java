package org.ntnu.petteed.Model;

import java.util.Iterator;

public class Simulator implements BattleSimulator {

  private Battle battle;

  /**
   * Creates a unit
   *
   * @param unit The unit to create
   */
  @Override
  public void createUnit(Unit unit) {
  }

  /**
   * Removes a unit
   *
   * @param unit The unit to remove
   */
  @Override
  public void removeUnit(Unit unit) {

  }

  /**
   * Creates an army
   *
   * @param army The army to create
   */
  @Override
  public void createArmy(Army army) {

  }

  /**
   * Deletes an army
   *
   * @param army The army to delete
   */
  @Override
  public void deleteArmy(Army army) {

  }

  /**
   * Clears any current army being edited on
   */
  @Override
  public void resetArmy() {

  }

  /**
   * Creates a battle to simulate
   *
   * @param firstArmy  The first member of the battle
   * @param SecondArmy The second member of the battle
   */
  @Override
  public void createBattle(Army firstArmy, Army SecondArmy) {

  }

  /**
   * Returns the members of a battle if created
   *
   * @return The members of the battle
   */
  @Override
  public Iterator<Actor> getBattleMembers() {
    return null;
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
