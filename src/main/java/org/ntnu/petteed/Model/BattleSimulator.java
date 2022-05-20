package org.ntnu.petteed.Model;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

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
   * @param actors The actors in the army
   */
  void createArmy(String name,Collection<Actor> actors);

  /**
   * Adds an army to the simulator
   *
   * @param army The army to add to the simulator
   */
  void importArmy(Army army);

  /**
   * Returns the current army highlighted in the simulator
   *
   * @return The current army highlighted in the simulator
   */
  Army getCurrentArmy();

  public Army getFirstArmy();

  public Army getSecondArmy();

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
   * Returns a collection of all the armies in the simulator
   *
   * @return A collection of all the armies in the simulator
   */
  List<Army> getArmiesRegister();

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
   *  Returns the members of a battle if created
   *
   * @return The members of the battle
   */
  Battle getCurrentBattle();

  /**
   * Initiates a battle
   *
   * @return The winning army from the battle
   */
  Army battle();

}
