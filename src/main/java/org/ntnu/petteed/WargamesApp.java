package org.ntnu.petteed;

import java.util.ArrayList;
import java.util.List;

public class WargamesApp {


  public static void main(String[] args) {

    Army test1 = new Army("Humans");
    Army test2 = new Army("Orcs");

    List<Unit> units1 = test1.createTestUnits("Dude",10,2);
    List<Unit> units2 = test1.createTestUnits("Orc",2,3);

    Army humanArmy = new Army("Humans",units1);
    Army orcishArmy = new Army("Orcs",units2);

    Battle battle = new Battle(humanArmy,orcishArmy);

    Army winningArmy = battle.simulate();

    System.out.println(winningArmy.toString());

  }
}
