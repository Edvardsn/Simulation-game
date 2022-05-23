package org.ntnu.petteed.Model;

import java.util.Random;

/**
 * Represents a factory for obtaining Pseudorandom values
 *
 */
public class RandomFactory {

  private static final Random randomGenerator = new Random();

  /**
   * Creates an instance of RandomFactory
   *
   */
  private RandomFactory(){
  }

  /**
   * Returns a random integer (Inclusive 0, Exclusive number n).
   *
   * @param integer The bound of the random value
   * @return The random value
   */
  public static int getRandomInteger(int integer){
    return randomGenerator.nextInt(integer);
  }

}
