package org.ntnu.petteed;

public class WargamesApp {

  public static void main(String[] args) {
    try {
      Army humans = Army.createTestArmy("Humans", "Dude", 10, Army.unitType.INFANTRY);
      Army orcs = Army.createTestArmy("Orcs", "Urulg", 10, Army.unitType.INFANTRY);

      Battle battle = new Battle(humans, orcs);

      Army winningArmy = battle.simulate();

      System.out.println(winningArmy.toString());
      System.out.println(orcs.toString());
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }
  }
}
