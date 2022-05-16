package org.ntnu.petteed.Model;

import java.util.Random;

/**
 * Represents a factory for obtaining random values
 *
 */
public class RandomFactory {

  private static RandomFactory instance = null;
  private static Random randomGenerator;

  /**
   * Creates a unit factory
   *
   */
  private RandomFactory(){
  }

  /**
   * Creates an instance of RandomFactory given that no other instance exists.
   *
   * @return The new or existing factory
   */
  public static RandomFactory getInstance() {
    if (instance == null) {
      instance = new RandomFactory();
      randomGenerator = new Random();
    }
    return instance;
  }

  /**
   * Returns a random integer between 0 and the bound (Exclusive).
   *
   * @param integer The bound of the random value
   * @return The random value
   */
  public static int getRandomInteger(int integer){
    return randomGenerator.nextInt(integer);
  }

}
