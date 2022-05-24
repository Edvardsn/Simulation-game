package org.ntnu.petteed.Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * This class represents a simulator which brings about the functionality set out in the
 * Battlesimulator interface, and interacts directly with the Model classes.
 *
 */
public class Simulator implements BattleSimulator {

  private Battle battle;

  private final List<Army> armyRegister;

  private Collection<Actor> temporaryActors;

  private Army firstArmy;
  private Army secondArmy;

  /**
   * Creates an instance of the simulator
   *
   */
  public Simulator() {
    this.armyRegister = new ArrayList<>();
    this.temporaryActors = new ArrayList<>();
    this.battle = null;
  }

  /**
   * Adds actors to the simulation into the current army highlighted
   *
   * @param numberOfActors The number of actors to create
   * @param name The name of the unit to create
   * @param health The health of the actors
   * @param actorType The type of actors to crate
   */
  @Override
  public void addActors(int numberOfActors,String name, int health, ActorType actorType) throws IllegalArgumentException{
    this.temporaryActors.addAll(ActorFactory.createUnits(numberOfActors, name,health,actorType));
  }

  /**
   * Removes a unit
   *
   * @param actor The unit to remove
   */
  @Override
  public void removeActor(Actor actor) {
    this.temporaryActors.remove(actor);
  }

  /**
   * Creates an army
   *
   * @param name   The name of the army to create
   */
  @Override
  public void createArmy(String name) throws IllegalArgumentException {
    if(!existsTemporaryActors()){
      throw new IllegalArgumentException("Cannot create army with no units");
    }

    Army newArmy = new Army(name, temporaryActors);

    if(getNumberOfArmies() == 1){
      this.secondArmy = newArmy;
      this.armyRegister.add(secondArmy.copy());
    }

    if(getNumberOfArmies() < 1){
      this.firstArmy = newArmy;
      this.armyRegister.add(firstArmy.copy());
    }
  }

  /**
   * Checks if the current army as input is valid
   *
   * @return True if valid current army, false if not.
   */
  public boolean existsTemporaryActors()  {
    return !temporaryActors.isEmpty();
  }

  /**
   * Sets what army is considered the first army
   *
   * @param firstArmy The army to be considered first army
   */
  public void setFirstArmy(Army firstArmy) {
    this.firstArmy = firstArmy;
  }

  /**
   * Sets what army is considered the second army
   *
   * @param secondArmy The army to be considered the second army
   */
  public void setSecondArmy(Army secondArmy) {
    this.secondArmy = secondArmy;
  }

  /**
   * Returns an iterator of the first army in the simulator
   *
   * @return The first army of the simulator
   */
  @Override
  public Iterator<Actor> getFirstArmyActorIterator() {
    return this.firstArmy.getArmyActorIterator();
  }

  /**
   * Returns an iterator of the second army in the simulator
   *
   * @return The second army of the simulator
   */
  @Override
  public Iterator<Actor> getSecondArmyActorIterator() {
    return this.secondArmy.getArmyActorIterator();
  }

  /**
   * Returns an iterator of the army register
   *
   * @return An iterator of the army register
   */
  @Override
  public Iterator<Army> getArmyRegisterIterator() {
    return armyRegister.iterator();
  }

  /**
   * Returns a number of the current amount of armies in the simulator
   *
   * @return The number of armies in the simulator
   */
  @Override
  public int getNumberOfArmies() {
    return this.armyRegister.size();
  }

  /**
   * Returns the percentage of actors alive in created armies in decimal form, or 1 if any army
   * does not have any actors alive
   *
   *
   * @return The percentage of actors alive in created armies in decimal form, or 1 in any army
   * does not have any actors alive
   */
  @Override
  public double getTotalPercentageOfActorsAlive() {

    double percentage;

    if(getPercentageOfActorsAliveArmyOne() == 0 || getPercentageOfActorsAliveArmyTwo() == 0){
      percentage = 1;
    }
    else{
      percentage = 1 - ((getPercentageOfActorsAliveArmyOne()  + getPercentageOfActorsAliveArmyTwo()) / 2);
    }

    return percentage;
  }

  /**
   * Returns the number of actors alive in army one in decimal form
   *
   * @return The number of actors alive in army one in decimal form
   */
  @Override
  public double getPercentageOfActorsAliveArmyOne() {
   return this.firstArmy.getPercentageActorsAlive();
  }

  /**
   * Returns the number of actors alive in army two in decimal form
   *
   * @return The number of actors alive in army two in decimal form
   */
  @Override
  public double getPercentageOfActorsAliveArmyTwo() {
    return this.secondArmy.getPercentageActorsAlive();
  }

  /**
   * Returns the first army
   *
   * @return The first army
   */
  public Army getFirstArmy() {
    return firstArmy;
  }

  /**
   * Returns the second army
   *
   * @return The second army
   */
  public Army getSecondArmy() {
    return secondArmy;
  }

  /**
   * Imports an army to the simulator
   *
   * @param army The army to import to the simulator
   */
  @Override
  public void importArmy(Army army) {

    if(getNumberOfArmies() == 1){
      this.secondArmy = army;
      this.getTemporaryActors().addAll(army.getActors());
    }

    if(getNumberOfArmies() < 1){
      this.firstArmy = army;
      this.getTemporaryActors().addAll(army.getActors());
    }
  }

  /**
   * Replaces any current army being edited on
   */
  @Override
  public void resetArmy() {
    this.temporaryActors = new ArrayList<>();
  }

  /**
   * Resets the simulator state to before combat was initiated
   *
   */
  @Override
  public void resetCombat() {
    setFirstArmy(armyRegister.get(0).copy());
    setSecondArmy(armyRegister.get(1).copy());
  }

  /**
   * Returns the collection of temporary actors created by the user
   *
   * @return The collection of temporary actors created by the user
   */
  public Collection<Actor> getTemporaryActors() {
    return temporaryActors;
  }

  /**
   * Returns an iterator of the temporary actors currently created
   *
   * @return An iterator of the temporary actors currently created
   */
  @Override
  public Iterator<Actor> getTemporaryCreatedActorsIterator() {
    return getTemporaryActors().iterator();
  }

  /**
   * Creates a battle with the first army and second army
   *
   * @param battleTerrain The terrain of the battle
   */
  @Override
  public void createBattle(String battleTerrain) {
    battle = new Battle(firstArmy,secondArmy,new Terrain(battleTerrain));
  }

  /**
   * Returns the battle if created
   *
   * @return The  battle
   */
  @Override
  public Battle getBattle() {
    return battle;
  }

  /**
   * Initiates a battle
   *
   * @return The winning army from the battle
   */
  @Override
  public Army battle() {
    Army winningArmy = null;

    if(getBattle() != null){
       winningArmy = getBattle().battle();
    }

    return winningArmy;
  }

}
