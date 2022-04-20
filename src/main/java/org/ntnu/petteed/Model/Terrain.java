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

  private final int STANDARD_TERRAIN = 4;
  private static final String[] terrainIdentities = {"FOREST","PLAINS","HILLS"};

  private final HashMap<String,Integer> terrainValues;


  /**
   * Creates an instance of a Terrain
   *
   * @param terrainName The name of the terrain
   * @throws IllegalArgumentException If user creates terrain with name null or invalid terrain name
   */
  public Terrain(String terrainName) {
    if(validTerrain(terrainName) && terrainName != null && !terrainName.isBlank())  {
      this.terrainName = terrainName;
      terrainValues = new HashMap<>();
      Arrays.stream(terrainIdentities).forEach(terrainId -> terrainValues.put(terrainId, STANDARD_TERRAIN));
    }
    else{
      throw new IllegalArgumentException("Invalid name in Terrain Constructor");
    }
  }

  /**
   * Checks if a given String is a valid terrain
   *
   * @param battleTerrain The proposed terrain
   * @return A boolean value which is True if given a valid terrain, false if not.
   */
  private boolean validTerrain(String battleTerrain) {
    return Arrays.asList(terrainIdentities).contains(battleTerrain);
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
}
