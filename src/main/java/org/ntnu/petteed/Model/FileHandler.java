package org.ntnu.petteed.Model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * This class handles all file-related tasks (Only supporting CSV-format)
 *
 * @author Student Number
 * @version 12/04/22
 */
public class FileHandler {

  // All available inputTypes to be read in from file, has to be manually changed
  private static final String[] validInputTypes = {"InfantryUnit","RangedUnit","CavalryUnit","MageUnit","CommanderUnit","SupportUnit"};
  private static final String INFO_SEPARATOR = ",";

  /**!!!!!!!!!!!!!!!!!!!!!!!!!!!!! try with resources
   * Writes the specifications of an Army to a given file
   *
   * @param file The file to write to
   * @param army The army which specifications are to be written
   * @throws IOException If no file is not found
   */
  public static void writeToFile(File file, Army army) throws IOException {
    FileWriter writer = new FileWriter(file);
    PrintWriter printWriter = new PrintWriter(writer);

    printWriter.println(army.getName()); // Prints the first line with army name

    Iterator<Actor> armyActorIterator = army.getArmyActorIterator(); // Uses and iterator to only give access to reading information

    while (armyActorIterator.hasNext()) {
      Actor actor = armyActorIterator.next();

      String actorType = actor.getClass().getSimpleName() + INFO_SEPARATOR;

      printWriter.print(actorType);

      if(actor instanceof Unit unit) {
        printWriter.print(unit.getName() +
            INFO_SEPARATOR);
      }
      else{
        printWriter.print(INFO_SEPARATOR + "Unknown" + INFO_SEPARATOR);
      }

      if(actor instanceof Unit unit){
        printWriter.println(unit.getHealth());
      }
      else{
        printWriter.println("NaN");
      }
    }
    printWriter.close();
  }

  /**
   * Checks if the data in list of strings is valid
   *
   * @param stringList The list of data to check
   * @return True if valid data, false if not.
   */
  public static boolean validStringData(List<String> stringList)  {
    boolean validData = true;

    for(String string : stringList){

      String[] lineData = string.split(INFO_SEPARATOR);

       validData = Arrays.stream(lineData).noneMatch(data -> (data == null || data.isBlank()));
    }

    return validData;
  }

  /**
   * Reads an Army from a given file
   *
   * @param file The file to be read
   * @return The army created from the file
   * @throws IOException If any data in given file is not valid
   */
  public static Army readArmyFromFile(File file)
      throws IOException {

    Army newArmy = null;

    List<String> fileLines = Files.readAllLines(file.toPath());

    if (!validStringData(fileLines)) {
      throw new InvalidFileDataException("Empty data fields in given file");
    }
    else
    {
      try {
        newArmy = new Army(fileLines.get(0), new ArrayList<>());
        fileLines.remove(0); // Removes army specific information

        for (String line : fileLines) {

          List<String> unitData = Arrays.stream(line.split(INFO_SEPARATOR)).toList();

          String unitType = unitData.get(0);
          String unitName = unitData.get(1);
          int unitHealth = Integer.parseInt(unitData.get(2));

          Unit newUnit = UnitFactory.createSingleUnit(ActorType.valueOfString(unitType), unitName, unitHealth);
          newArmy.addActor(newUnit);
        }
      }
      catch (IllegalArgumentException e){
        throw new InvalidFileDataException(e.getMessage());
      }
      }

    return newArmy;
    }

}
