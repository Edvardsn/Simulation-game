package org.ntnu.petteed.Model;

import java.util.Random;

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
   * Creates an instance of UnitFactory given that no other instance exists.
   *
   * @return The new or existing unit factory
   */
  public static RandomFactory getInstance() {
    if (instance == null) {
      instance = new RandomFactory();
      randomGenerator = new Random();
    }
    return instance;
  }

  public static int getRandomInteger(int integer){
    return randomGenerator.nextInt(integer);
  }

}
