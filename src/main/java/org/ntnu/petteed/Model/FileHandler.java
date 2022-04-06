package org.ntnu.petteed.Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.Iterator;
import java.util.List;


public class FileHandler {

  private static final String INFO_SEPARATOR = ",";
  Class[] classes = Unit.class.getPermittedSubclasses();
  // private static final String[] validUnitTypes = Unit.class.getPermittedSubclasses();


  public void writeToFile(File file,Army army) throws IOException {
    FileWriter writer = new FileWriter(file);
    PrintWriter printWriter = new PrintWriter(writer); //Printwriter ettersom dette skal skje umiddelbart og ikke
    // under kjøring slik at performance har noe å si

    printWriter.println("ArmyName: " + army.getName());

    Iterator<Unit> unitIterator = army.getUnitIterator(); // Uses and iterator to only give acess to reading information

    while(unitIterator.hasNext()){
      Unit unit = unitIterator.next();

      String unitName = unit.getName() + INFO_SEPARATOR;

      Files.write(file.toPath(),unitName.getBytes());
      printWriter.print(unit.getClass().getSimpleName() + INFO_SEPARATOR + unit.getName() +
          INFO_SEPARATOR);
      printWriter.println(unit.getHealth());
    }
    printWriter.close();
  }

  public Army readFromFile(File file) throws IOException {
    BufferedReader bfreader = new BufferedReader(Files.newBufferedReader(file.toPath()));
    List<String> fileLines = Files.readAllLines(file.toPath());

    Army importedArmy = new Army(fileLines.get(0)); // Name of army on the first line

    int index = 1;

    while(index < fileLines.size()){
      String[] unitInformation = fileLines.get(index).split(INFO_SEPARATOR);
      Unit newUnit;
    }
    return importedArmy;
  }

  public void createRootDirectory(){
//    Path rootDirectory = Path.of("/Users/edvardsen/Documents/Lokale Prosjekter");
//    rootDirectory.getClass().getResource()
//    Path directory = Files.createDirectory(rootDirectory.resolve());
  }
}
