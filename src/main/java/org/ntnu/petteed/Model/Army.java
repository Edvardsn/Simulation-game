package org.ntnu.petteed.Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import org.ntnu.petteed.Model.Units.*;

/**
 * This class represents an army of Actors
 *
 */
public class Army {

  private final String name;
  private Collection<Actor> actors;
  private Terrain currentTerrain;

  /**
   * Creates and instance of an Army
   *
   * @param name  The name of the army
   * @param actors The list of units in the army
   *
   */
  public Army(String name, Collection<Actor> actors) throws IllegalArgumentException {
    if (name != null && !name.isBlank() && actors != null) {
      this.name = name;
      this.actors = actors;

      updateArmy();
    } else if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("Cannot create an Army without a name");
    } else {
      throw new IllegalArgumentException("Cannot create an Army without any units");
    }
  }

  /**
   * Creates and instance of an Army without actors
   *
   * @param name The name of the army
   *
   */
  public Army(String name) throws IllegalArgumentException {
    if (name != null && !name.isBlank()) {
      this.name = name;
    } else {
      throw new IllegalArgumentException("Cannot create an Army without a name");
    }
  }

  /**
   * Copy constructor for copying another army
   *
   * @param army The army to copy
   */
  public Army(Army army){
    this.name = army.getName();
    this.actors = army.getCopiedActors();

    if(this.currentTerrain != null){
      this.currentTerrain = new Terrain(army.getCurrentTerrain().getTerrainName());
    }
  }

  /**
   * Copies actors in army
   *
   * @return A list of army's copied actors
   */
  private ArrayList<Actor> getCopiedActors() {

    ArrayList<Actor> copiedActors = new ArrayList<>();

    for(Actor actor : actors){
      copiedActors.add(ActorFactory.copy(actor));
    }
    return copiedActors;
  }

  /**
   * Returns a copy of this army
   *
   * @return A copy of this army
   */
  public Army copy(){
    return new Army(this);
  }

  /**
   * Updates the army by giving the armies information to its actors
   *
   */
  private void updateArmy() {
    this.actors.forEach(actor -> {
      if (actor instanceof Unit unit ){
        unit.setCurrentArmy(this);
        unit.setCurrentTerrain(this.currentTerrain);
      }
    });
  }

  /**
   * A random actor in this army attacks a random actor in given army
   *
   * @param army The army to attack
   */
  public void act(Army army){
    if(this.getRandom() != null && army.getRandom() != null){
      this.getRandom().act(army.getRandom());
    }
  }

  /**
   * Returns the Units in the army
   *
   * @return The unis in the army
   */
  public Collection<Actor> getActors() {
    return actors;
  }

  /**
   * Returns an iterator of the collection of units
   *
   * @return The iterator of the collection of units
   */
  public Iterator<Actor> getArmyActorIterator(){
    return getActors().iterator();
  }

  /**
   * Adds a unit to the army
   *
   * @param actor The unit to be added
   */
  public void addActor(Actor actor){
    if(actor != null){
      getActors().add(actor);

      updateArmy();
    }
  }

  /**
   * Removes the specified unit from army
   *
   * @param actor Unit to be removed
   */
  public void remove(Actor actor) {
    if (actor != null) {
      actors.remove(actor);
    }
  }

  /**
   * Checks if the army has healthy units
   *
   * @return True if any number of units present, false if not.
   */
  public boolean hasHealthyActors() {
    return actors
        .stream()
        .anyMatch(Actor::isAlive);
  }

  /**
   * Assigns the terrain to the units in the army
   *
   */
  public void assignTerrain(){
    actors.forEach(actor -> {
      if(actor instanceof Unit unit){
        unit.setCurrentTerrain(this.currentTerrain);}
    });
  }

  /**
   * Returns the current terrain of the army
   *
   * @return The current Terrain
   */
  public Terrain getCurrentTerrain() {
    return currentTerrain;
  }

  /**
   * Sets the current terrain the army is occupying
   *
   * @param currentTerrain The new value of terrain
   */
  public void setCurrentTerrain(Terrain currentTerrain) {
    if(currentTerrain != null){
      this.currentTerrain = currentTerrain;
      assignTerrain();
    }
  }

  /**
   * Returns a random healthy Unit from the army
   *
   * @return A random healthy unit from army
   */
  public Actor getRandom() {
    if(!this.hasHealthyActors()){ // Guard condition
      return null;
    }

    List<Actor> aliveActors = actors
          .stream()
          .filter(Actor::isAlive)
          .toList();

    Actor randomActor = aliveActors.get(RandomFactory.getRandomInteger(aliveActors.size()));

    return randomActor;
  }

  /**
   * Returns the percentage of actors alive in army in decimal form
   *
   * @return The percentage in decimal form
   */
  public double getPercentageActorsAlive(){
    double actorsAlive = this.getActors()
        .stream()
        .filter(Actor::isAlive)
        .count();

    int currentSize = this.getActors().size();

    double onePercentage = currentSize / 100.0;

    Double percentageAlive = (actorsAlive / onePercentage ) / 100.0;

    return percentageAlive;
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
   * Returns a list of every Infantry in the Army
   *
   * @return {@code List<Actor>} , The list of InfantryUnits
   */
  public List<Actor> getInfantryUnits(){
    return actors.stream()
        .filter(actor -> actor.getClass() == InfantryUnit.class)
        .toList();
  }

  /**
   * Returns a list of every Ranged unit in the Army
   *
   * @return {@code List<Actor>} , The list of RangedUnits
   */
  public List<Actor> getRangedUnits(){
    return actors.stream()
        .filter(actor -> actor.getClass() == RangedUnit.class)
        .toList();
  }

  /**
   * Returns a list of every CavalryUnit in the Army
   *
   * @return {@code List<Actor>} , The list of CavalryUnits
   */
  public List<Actor> getCavalryUnits(){
    return actors.stream()
        .filter(actor -> actor.getClass() == CavalryUnit.class)
        .toList();
  }

  /**
   * Returns a list of every Commanderunit in the Army
   *
   * @return {@code List<Actor>} , The list of Commanderunits
   */
  public List<Actor> getCommanderUnits(){
    return actors.stream()
        .filter(actor -> actor.getClass() == CommanderUnit.class)
        .toList();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Army)) {
      return false;
    }
    Army army = (Army) o;
    return Objects.equals(getActors(), army.getActors()) &&
        Objects.equals(getCurrentTerrain(), army.getCurrentTerrain());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName());
  }

  /**
   * Creates a description of the army
   *
   * @return A description of the army
   */
  @Override
  public String toString() {
    return "Army{" +
        "name='" + name + '\'' +
        ", units=" + actors +
        "}'";
  }

  }
