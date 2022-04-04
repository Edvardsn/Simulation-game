package org.ntnu.petteed.Model;

import java.nio.file.Files;
import java.nio.file.Path;

public class FileHandler {

  public void createRootDirectory(){
    Path rootDirectory = Path.of("/Users/edvardsen/Documents/Lokale Prosjekter");
    rootDirectory.getClass().getResource()
    Path directory = Files.createDirectory(rootDirectory.resolve());
  }
}
