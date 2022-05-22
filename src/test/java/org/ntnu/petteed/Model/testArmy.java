package org.ntnu.petteed.Model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import org.junit.jupiter.api.Test;

/**
 * This class performs the following tests to ensure that the Army class is behaving as intended.
 *
 * Positive tests:
 * <ul>
 *   <li>testCreatingArmy, tests that an Army is created with valid fields </li>
 *   <li>testCreationOfDistinctCopy, tests that the army returns a copy with same fields</li>
 *   <li>testActorAdded, tests that an actor is added successfully to the army</li>
 *   <li>testActorRemoved, tests that an actor is remove successfully from the army</li>
 *   <li>testGetInfantryUnits, tests that the army returns infantry units on method call</li>
 *   <li>testGetRangedUnits, tests that the army returns ranged units on method call</li>
 *   <li>testGetCavalrynits, tests that the army returns cavalry units on method call</li>
 *   <li>testGetCommanderynits, tests that the army returns commander units on method call</li>
 * </ul>
 *
 * Negative tests:
 * <ul>
 *    <li>testArmyNotWithNullName, test that an Army cannot be created without a name</li>
 *    <li>testArmyWithNonBlankName, tests that the Army will not be created with a blank name</li>
 *    <li>testArmyNotWithoutUnits, tests that an Army cannot be created with null units</li>
 *    <li>testGetRandomIsNotNull, tests that getRandom will not return a null value</li>
 *    <li>testGetRandomIsNotDead, tests that getRandom cannot return a dead actor</li>
 *  </ul>
 */
class testArmy {

  /**
   * Tests that an Army is created with valid fields
   *
   */
  @Test
   void testCreatingArmy(){
    Collection<Actor> testList = new ArrayList<>();
    Army testArmy = new Army("test",testList);

    assertEquals(testList, testArmy.getActors());
    assertEquals("test",testArmy.getName());
  }

  /**
   * Test that a copied army with non-primitive fields will not reference to the same objects
   *
   */
  @Test
  void testCreationOfDistinctCopy(){
    Army testArmy = new Army("test",createTestActors());
    
    Army copiedArmy = testArmy.copy();

    // Extra data not to be included in original army
    copiedArmy.addActor(new MockUnit("extraUnit",1,1,1));
    copiedArmy.setCurrentTerrain(new Terrain("Hills"));

    assertNotEquals(testArmy.getActors(),copiedArmy.getActors());
    assertNotEquals(testArmy.getCurrentTerrain(),copiedArmy.getCurrentTerrain());
  }

  /**
   * Tests that an actor is added to the army
   *
   */
  @Test
  void testActorAdded(){
    Army testArmy = new Army("test",createTestActors());

    Actor actorToAdd = new MockUnit("addedExtra",1,1,1);

    testArmy.addActor(actorToAdd);

    assertTrue(testArmy.getActors().contains(actorToAdd));
  }

  /**
   * Tests that an actor is removed
   *
   */
  @Test
  void testActorRemoved(){
    Army testArmy = new Army("test",createTestActors());

    Actor actorToremove = new MockUnit("addedExtra",1,1,1);

    testArmy.remove(actorToremove);

    assertFalse(testArmy.getActors().contains(actorToremove));
  }

  /**
   * Tests that getInfantryUnits returns correct units
   *
   */
  @Test
  void testGetInfantryUnits(){
    Collection<Actor> infantryUnits = ActorFactory
        .createUnits(5,"Infantry",100,ActorType.INFANTRY_UNIT);

    Army testArmy = new Army("test",infantryUnits);

    Actor nonInfantryUnit = new MockUnit("NonInfantry",1,1,1);

    testArmy.addActor(nonInfantryUnit);

    assertTrue(testArmy.getInfantryUnits().containsAll(infantryUnits));
    assertTrue(testArmy.getInfantryUnits().stream().noneMatch(actor -> actor.equals(nonInfantryUnit)));
  }

  /**
   * Tests that getRangedUnits returns correct units
   *
   */
  @Test
  void testGetRangedUnits(){
    Collection<Actor> rangedUnits = ActorFactory
        .createUnits(5,"Ranged",100,ActorType.RANGED_UNIT);

    Army testArmy = new Army("test",rangedUnits);

    Actor nonRangedUnit = new MockUnit("NonRanged",1,1,1);

    testArmy.addActor(nonRangedUnit);

    assertTrue(testArmy.getRangedUnits().containsAll(rangedUnits));
    assertTrue(testArmy.getRangedUnits().stream().noneMatch(actor -> actor.equals(nonRangedUnit)));
  }

  /**
   * Tests that getCavalryUnits returns correct units
   *
   */
  @Test
  void testGetCavalryUnits(){
    Collection<Actor> cavalryUnits = ActorFactory
        .createUnits(5,"Cavalry",100,ActorType.CAVALRY_UNIT);

    Army testArmy = new Army("test",cavalryUnits);

    Actor nonCavalry = new MockUnit("NonCavalry",1,1,1);

    testArmy.addActor(nonCavalry);

    assertTrue(testArmy.getCavalryUnits().containsAll(cavalryUnits));
    assertTrue(testArmy.getCavalryUnits().stream().noneMatch(actor -> actor.equals(nonCavalry)));
  }

  /**
   * Tests that getCommanderUnits returns correct units
   *
   */
  @Test
  void testGetCommanderUnits(){
    Collection<Actor> commanderUnits = ActorFactory
        .createUnits(5,"Commander",100,ActorType.COMMANDER_UNIT);

    Army testArmy = new Army("test",commanderUnits);

    Actor nonCommander = new MockUnit("NonCommander",1,1,1);

    testArmy.addActor(nonCommander);

    assertTrue(testArmy.getCommanderUnits().containsAll(commanderUnits));
    assertTrue(testArmy.getCommanderUnits().stream().noneMatch(actor -> actor.equals(nonCommander)));
  }

  // Negative tests

  /**
   * Test that an Army cannot be created without a name
   *
   */
  @Test
   void testArmyWithNullName(){
    Collection<Actor> testList = new ArrayList<>();
    assertThrows(IllegalArgumentException.class, () -> {
      new Army(null,testList);
    });
  }

  /**
   * Test that an Army cannot be created without a blank name
   *
   */
  @Test
  void testArmyWithNotBlankName(){
    Collection<Actor> testList = new ArrayList<>();
    assertThrows(IllegalArgumentException.class, () -> {
      new Army("",testList);
    });
  }

  /**
   * Tests that an Army cannot be created with null units.
   *
   */
  @Test
  void testArmyWithoutUnits(){
    assertThrows(IllegalArgumentException.class,()->{
      new Army("test",null);
    });
  }

  /**
   * Tests that getRandom will never return null
   *
   */
  @Test
  void testGetRandomIsNotNull(){
    Army testArmy = new Army("test",null);

    assertNotNull(testArmy.getRandom());
  }

  @Test
  void testGetRandomIsNotDead(){
    Collection<Actor> deadActor = new ArrayList<Actor>();
    deadActor.add(new MockUnit("deadActor",0,1,1));

    Army testArmy = new Army("test",deadActor);

    assertNull(testArmy.getRandom());
  }

  /**
   * Creates a testCollection of MockUnits 
   * 
   * @return A testCollection of MockUnits 
   */
  static Collection<Actor> createTestActors(){
    Collection<Actor> testCollection = new ArrayList<>();
    testCollection.add(new MockUnit("test",100,20,20));
    testCollection.add(new MockUnit("test",100,20,20));
    testCollection.add(new MockUnit("test",100,20,20));
    testCollection.add(new MockUnit("test",100,20,20));
    testCollection.add(new MockUnit("test",100,20,20));
    
    return testCollection;
  }

}
