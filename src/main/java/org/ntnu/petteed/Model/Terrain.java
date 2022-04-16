package org.ntnu.petteed.Model;

import java.util.Arrays;
import java.util.HashMap;

/**
 * This class represents a terrain and what conditions follows when interacting in its environment.
 *
 * @author Petter Edvardsen
 * @version 16/04/22
 */
public class Terrain{

  private final String terrainName;

  private final int STANDARD_TERRAIN = 2;
  private static final String[] terrains = {"FOREST","PLAINS","HILLS"};

  private final HashMap<String,Integer> terrainValues;

  /**
   * Creates an instance of a Terrain
   *
   * @param terrainName The name of the terrain
   */
  public Terrain(String terrainName) {
    this.terrainName = terrainName;
    terrainValues = new HashMap<>();
    Arrays.stream(terrains).forEach(terrainId -> terrainValues.put(terrainId,STANDARD_TERRAIN));
  }

  /**
   * Checks if a given String is a valid terrain
   *
   * @param battleTerrain The proposed terrain
   * @return A boolean value which is True if given a valid terrain, false if not.
   */
  private boolean validTerrain(String battleTerrain) {
    return Arrays.asList(terrains).contains(battleTerrain);
  }

  /**
   * Returns the name of the Terrain
   *
   * @return The name of the terrain
   */
  public String getTerrainName() {
    return terrainName;
  }

  /**
   * Returns the value assigned to specified terrain
   *
   * @param terrainIdentity The terrainIdentity of the terrain which value to get
   * @return The value of the terrain condition
   */
  public int getSpecificTerrainCondition(String terrainIdentity){
    return terrainValues.get(terrainIdentity);
  }

  /**
   * Returns the value assigned to this terrain
   *
   * @return The value assigned to this terrain
   */
  public int getThisTerrainCondition(){
    return terrainValues.get(this.getTerrainName());
  }
}
