package org.ntnu.petteed.Model;

import java.util.Iterator;

public interface BattleSimulator {

  /**
   * Creates a unit
   *
   * @param unit The unit to create
   */
  void createUnit(Unit unit);

  /**
   * Removes a unit
   *
   * @param unit The unit to remove
   */
  void removeUnit(Unit unit);

  /**
   * Creates an army
   *
   * @param army The army to create
   */
  void createArmy(Army army);

  /**
   * Deletes an army
   *
   * @param army The army to delete
   */
  void deleteArmy(Army army);

  /**
   * Clears any current army being edited on
   *
   */
  void resetArmy();

  /**
   * Creates a battle to simulate
   *
   * @param firstArmy The first member of the battle
   * @param SecondArmy The second member of the battle
   */
  void createBattle(Army firstArmy,Army SecondArmy);

  /**
   *  Returns the members of a battle if created
   *
   * @return The members of the battle
   */
  Iterator<Actor> getBattleMembers();

  /**
   * Initiates a battle
   *
   * @return The winning army from the battle
   */
  Army battle();



}
