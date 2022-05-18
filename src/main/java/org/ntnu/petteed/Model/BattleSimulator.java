package org.ntnu.petteed.Model;

import java.util.Collection;
import java.util.Iterator;

public interface BattleSimulator {

  /**
   * Creates a unit
   *
   * @param name The name of the unit to create
   */
  void createUnit(String name,UnitType unitType);

  /**
   * Removes a unit
   *
   * @param unit The unit to remove
   */
  void removeUnit(Unit unit);

  /**
   * Creates an army
   *
   * @param name The name of the new army
   * @param actors The actors in the new army
   */
  void createArmy(String name, Collection<Actor> actors);

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
  Iterator<Battle> getBattles();

  /**
   * Initiates a battle
   *
   * @return The winning army from the battle
   */
  Army battle();


  Army getCurrentArmy();
}
