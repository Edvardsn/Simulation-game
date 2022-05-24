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
 * This class handles all file-related tasks (Only supporting CSV-format and Units)
 *
 * @author Nr. 10049
 * @version 12/04/22
 */
public class FileHandler {

  private static final String INFO_SEPARATOR = ",";

  /**
   *  Private constructor, all methods are static and do not require an instance of Filehandler
   */
  private FileHandler(){
  }

  /**
   * Writes the specifications of an Army to a given file
   *
   * @param file The file to write to
   * @param army The army which specifications are to be written
   * @throws IOException If no file is not found
   */
  public static void writeToFile(File file, Army army) throws IOException {
    try( FileWriter writer = new FileWriter(file);
         PrintWriter printWriter = new PrintWriter(writer);)
    {
      printWriter.println(army.getName());

      Iterator<Actor> armyActorIterator = army.getArmyActorIterator();

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
    }
  }

  /**
   * Checks if the data in list of strings is valid according to CSV format
   *
   * @param stringList The list of data to check
   * @return True if valid data, false if not.
   */
  public static boolean validCSVStringData(List<String> stringList)  {
    boolean validData = true;

    int dataIndex = 0;

    while(dataIndex < stringList.size() && validData == true){
      String[] lineData = stringList.get(dataIndex).split(INFO_SEPARATOR);

      validData = Arrays.stream(lineData).noneMatch(data -> (data == null || data.isBlank()));

      dataIndex++;
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

    if (!validCSVStringData(fileLines)) {
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

          Actor newUnit = ActorFactory.createSingleUnit(ActorType.valueOfString(unitType), unitName, unitHealth);
          newArmy.addActor(newUnit);
        }
      }
      catch (IllegalArgumentException e){
        throw new InvalidFileDataException("Error while reading file: " + e.getMessage());
      }

      }

    return newArmy;
    }

}
