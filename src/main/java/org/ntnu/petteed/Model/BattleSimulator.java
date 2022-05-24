package org.ntnu.petteed.Model;

import java.util.Iterator;

/**
 * This is an interface which reflects the current functionality of the program.
 *
 */
public interface BattleSimulator {

  /**
   * Adds actors to the simulation into the current army highlighted
   *
   * @param name The name of the unit to create
   * @param health The health of the actor
   * @param actorType The type of actor
   */
  void addActors(int numberOfActors,String name, int health, ActorType actorType);

  /**
   * Removes an actor
   *
   * @param actor The actor to remove
   */
  void removeActor(Actor actor);

  /**
   * Creates an army
   *
   * @param name The name of the army to create
   */
  void createArmy(String name);

  /**
   * Adds an army to the simulator
   *
   * @param army The army to add to the simulator
   */
  void importArmy(Army army);

  /**
   * Returns the first army in the simulator
   *
   * @return The first army in the simulator
   */
  Army getFirstArmy();

  /**
   * Returns the second army in the simulator
   *
   * @return The second army in the simulator
   */
  Army getSecondArmy();

  /**
   * Returns whether there exists temporary actors
   *
   * @return True if a positive number of temporary actors, false if not.
   */
  boolean existsTemporaryActors();

  /**
   * Returns an iterator of the temporary created actors
   *
   * @return An iterator of the temporary created actors
   */
  Iterator<Actor> getTemporaryCreatedActorsIterator();

  /**
   * Returns an iterator of the first army in the simulator
   *
   * @return The first army of the simulator
   */
  Iterator<Actor> getFirstArmyActorIterator();

  /**
   * Returns an iterator of the second army in the simulator
   *
   * @return The second army of the simulator
   */
  Iterator<Actor> getSecondArmyActorIterator();

  /**
   * Returns an iterator of the army register in the simulator
   *
   * @return The iterator of the army register
   */
   Iterator<Army> getArmyRegisterIterator();

  /**
   * Returns a number of the current amount of armies in the simulator
   *
   * @return The number of armies in the simulator
   */
  int getNumberOfArmies();

  /**
   * Returns the total number of actors alive in created armies
   *
   * @return The total number of actors alive in created armies
   */
  double getTotalPercentageOfActorsAlive();

  /**
   * Returns the percentage of actors alive in army one
   *
   * @return The percentage of actors alive in army one
   */
  double getPercentageOfActorsAliveArmyOne();

  /**
   * Returns the percentage of actors alive in army two
   *
   * @return The percentage of actors alive in army two
   */
  double getPercentageOfActorsAliveArmyTwo();

  /**
   * Resets the simulator to the state before combat
   *
   */
  void resetCombat();

  /**
   * Clears any current army being edited on
   *
   */
  void resetArmy();

  /**
   * Creates a battle to simulate
   *
   * @param battleterrain The terrain of the battle
   */
  void createBattle(String battleterrain);


  /**
   *  Returns a battle if created
   *
   * @return The battle
   */
  Battle getBattle();

  /**
   * Initiates a battle
   *
   * @return The winning army from the battle
   */
  Army battle();
}
