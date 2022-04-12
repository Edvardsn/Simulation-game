package org.ntnu.petteed.Model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.reflections.Reflections;

/**
 * This class handles all file-related tasks
 *
 * @author Petter Edvardsen
 * @version 12/04/22
 */
public class FileHandler {

  private static final String INFO_SEPARATOR = ",";

  private final List<Class<? extends Unit>> subTypes = getSubTypesOfUnit();

  /**
   * Returns the subtypes of the Unit class
   *
   * @return List of the subtypes of the Unit class
   */
  private List<Class<? extends Unit>> getSubTypesOfUnit() {
    Reflections reflections = new Reflections("org.ntnu.petteed"); // Selve reflections i mappen
    return reflections.getSubTypesOf(Unit.class).stream().toList();
  }

  /**
   * Creates a directory in the at the specified path
   *
   * @param path The path in which the directory will be created
   * @throws IOException If path is not found
   */
  public void createFileDirectory(Path path) throws IOException {
    //Path directory = Files.createDirectory(rootDirectory.resolve());
    Path directory = Files.createDirectory(path.getParent().resolve("ArmyFiles"));
  }

  /**
   * Creates a file at the specified path
   *
   * @param fileName  The name of the new file
   * @param directory The path to create the file at
   * @throws IOException If path is not found
   */
  public void createArmyFile(String fileName, Path directory) throws IOException {
    Path newFile = Files.createFile(directory.resolve(fileName + ".txt"));
  }

  /**
   * Writes the specifications of an Army to a given file
   *
   * @param file The file to write to
   * @param army The army which specifications are to be written
   * @throws IOException If no file is not found
   */
  public void writeToFile(File file, Army army) throws IOException {
    FileWriter writer = new FileWriter(file);
    PrintWriter printWriter = new PrintWriter(writer);

    printWriter.println(army.getName());

    Iterator<Unit> unitIterator =
        army.getUnitIterator(); // Uses and iterator to only give access to reading information

    while (unitIterator.hasNext()) {
      Unit unit = unitIterator.next();

      String unitName = unit.getName() + INFO_SEPARATOR;


      Files.write(file.toPath(), unitName.getBytes());

      printWriter.print(unit.getClass().getSimpleName() + INFO_SEPARATOR + unit.getName() +
          INFO_SEPARATOR);

      printWriter.println(unit.getHealth());
    }
    printWriter.close();
  }

  /**
   * Checks if given file has valid information in each line
   *
   * @param file The file to check included info
   * @return True if a valid file, false if not.
   * @throws IOException
   */
  public boolean checkValidFileInfo(File file) throws IOException {

    boolean validFile = true;

    int LineErrorIndex = 0;

    List<String> fileLines = Files.readAllLines(file.toPath());

    for (int i = 1; i < fileLines.size(); i++) {
      List<String> unitInformation = Arrays.stream(fileLines.get(i).split(INFO_SEPARATOR)).toList();

      boolean validUnitType =
          this.subTypes.stream().anyMatch(type -> type.getSimpleName().equals(unitInformation.get(0)));

      boolean validUnitName =
          (unitInformation.get(1) != null) && !(unitInformation.get(1).isBlank());

      boolean validUnitHealth;

      if (unitInformation.get(2) != null && !unitInformation.get(2).isBlank()) {
        int healthValue = Integer.parseInt(unitInformation.get(2));
        validUnitHealth = healthValue > 0;
      } else {
        validUnitHealth = false;
      }

      validFile = validUnitType && validUnitName &&
          validUnitHealth; // Makes the file invalid if it dosent meet the conditions
    }
    return validFile;
  }

  /**
   * Reads an Army from a given file
   *
   * @param file The file to be read
   * @return The army created from the file
   * @throws IOException
   * @throws InvocationTargetException
   * @throws InstantiationException
   * @throws IllegalAccessException
   */
  public Army readArmyFromFile(File file)
      throws IOException, InvocationTargetException, InstantiationException,
      IllegalAccessException {

    if (!checkValidFileInfo(file)) {
      return null;
    }

    List<String> fileLines = Files.readAllLines(file.toPath());

    Army newArmy = new Army(fileLines.get(0), new ArrayList<>()); // Name of army on the first line

    int lineIndex = 0;

    while (lineIndex < fileLines.size()) {

      List<String> unitInformation = Arrays.stream(fileLines.get(lineIndex + 1).split(INFO_SEPARATOR)).toList(); // Informasjon på linjen

      String unitType = unitInformation.get(0);
      String unitName = unitInformation.get(1);
      String unitHealth = unitInformation.get(2);

      Class<? extends Unit> currentSubtype = this.subTypes.get(getSubTypeclassIndex(unitType)); // Subtype som samsvarer med dette navnet

      Constructor<?> consturctor = Arrays.stream(currentSubtype.getConstructors()).toList().get(0); // Henter konstruktøren til den subtypen

      Unit newUnit = (Unit) consturctor.newInstance(unitName,unitHealth); // Kall deretter konstruktøren til denne typen unit med informasjonen

      newArmy.addUnit(newUnit);

      lineIndex++;
    }

    return newArmy;
  }

  /**
   * Retruns the index of specified subclass by its name
   *
   * @param unitType The name of the subclass
   * @return Number representing the index
   */
  public int getSubTypeclassIndex(String unitType) {

    int index = 0;

    boolean foundExistingUnitType = false;

    int counter = 0;

    while (!foundExistingUnitType && counter < this.subTypes.size()) {

      if (subTypes.get(counter).getSimpleName().equals(unitType)) {
        index = counter;
        foundExistingUnitType = true;
      }

      counter++;
    }
      return index;

  }
}
