package org.ntnu.petteed.Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Simulator implements BattleSimulator {

  private Battle currentBattle;

  private final List<Army> armyRegister;

  private Army currentArmy;

  private Army firstArmy;
  private Army secondArmy;

  public Simulator() {
    this.armyRegister = new ArrayList<>();
    this.currentArmy = new Army("Army",new ArrayList<>());
    this.currentBattle = null;
  }

  /**
   * Adds actors to the simulation into the current army highlighted
   *
   * @param name The name of the unit to create
   */
  public void addActors(int numberOfActors,String name, int health, ActorType actorType){
    this.currentArmy
        .getActors()
        .addAll(ActorFactory.createUnits(numberOfActors, name,health,actorType));
  }

  /**
   * Removes a unit
   *
   * @param actor The unit to remove
   */
  @Override
  public void removeActor(Actor actor) {
    this.currentArmy.remove(actor);
  }


  /**
   * Creates an army
   *
   * @param name   The name of the army to create
   * @param actors The actors in the army
   */
  @Override
  public void createArmy(String name, Collection<Actor> actors) {
    Army newArmy = new Army(name, actors);

    if(getNumberOfArmies() == 1){
      this.secondArmy = newArmy;
      this.armyRegister.add(secondArmy.copy());
    }

    if(getNumberOfArmies() < 1){
      this.firstArmy = newArmy;
      this.armyRegister.add(firstArmy.copy());
    }
  }

  public void setFirstArmy(Army firstArmy) {
    this.firstArmy = firstArmy;
  }

  public void setSecondArmy(Army secondArmy) {
    this.secondArmy = secondArmy;
  }

  /**
   * Returns the current army highlighted in the simulator
   *
   * @return The current army highlighted in the simulator
   */
  @Override
  public Army getCurrentArmy() {
    return this.currentArmy;
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
   * Returns a number of the current amount of armies in the simulator
   *
   * @return The number of armies in the simulator
   */
  @Override
  public int getNumberOfArmies() {
    return this.armyRegister.size();
  }

  /**
   * Returns the total number of actors alive in created armies
   *
   * @return The total number of actors alive in created armies
   */
  @Override
  public double getTotalPercentageOfActorsAlive() {

    double percentage;

    if(getPercentageOfActorsAliveArmyOne() == 0 || getPercentageOfActorsAliveArmyTwo() == 0){
      percentage = 1;
    }
    else{
      percentage = 1 - ((getPercentageOfActorsAliveArmyOne() / 2) +
          (getPercentageOfActorsAliveArmyTwo() / 2));
    }

    return percentage;
  }

  /**
   * Returns the number of actors alive in army one
   *
   * @return The number of actors alive in army one
   */
  @Override
  public double getPercentageOfActorsAliveArmyOne() {
    double actorsAlive = this.firstArmy.getActors().stream().filter(Actor::isAlive).count();

    int currentSize = this.firstArmy.getActors().size();

    double onePercentage = currentSize / 100.0;

    Double percentageAlive = (actorsAlive / onePercentage ) / 100;

    return percentageAlive;
  }

  /**
   * Returns the number of actors alive in army two
   *
   * @return The number of actors alive in army two
   */
  @Override
  public double getPercentageOfActorsAliveArmyTwo() {
    double actorsAlive = this.secondArmy.getActors().stream().filter(Actor::isAlive).count();

    int currentSize = this.secondArmy.getActors().size();

    double onePercentage = currentSize / 100.0;

    Double percentageAlive = (actorsAlive / onePercentage ) / 100;

    return percentageAlive;
  }

  /**
   * Returns a collection of all the armies in the simulator
   *
   * @return A collection of all the armies in the simulator
   */
  @Override
  public List<Army> getArmiesRegister() {
    return this.armyRegister;
  }

  public Army getFirstArmy() {
    return firstArmy;
  }

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
    this.currentArmy = army;
  }

  /**
   * Clears any current army being edited on
   */
  @Override
  public void resetArmy() {
    this.currentArmy = new Army("Army",new ArrayList<>());
  }

  /**
   * Creates a battle to simulate
   *
   * @param battleTerrain The terrain of the battle
   */
  @Override
  public void createBattle(String battleTerrain) {
    currentBattle= new Battle(firstArmy,secondArmy,new Terrain(battleTerrain));
  }

  /**
   * Returns the members of a battle if created
   *
   * @return The members of the battle
   */
  @Override
  public Battle getCurrentBattle() {
    return currentBattle;
  }

  /**
   * Initiates a battle
   *
   * @return The winning army from the battle
   */
  @Override
  public Army battle() {
    Army winningArmy = getCurrentBattle().battle();

    return winningArmy;
  }
}
