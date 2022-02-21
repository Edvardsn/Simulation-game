package org.ntnu.petteed;

public class WargamesApp {

  public static void main(String[] args) {
    try {
      Army humans = Army.createTestArmy("Humans", "Dude", 7, 1);
      Army orcs = Army.createTestArmy("Orcs", "Urulg", 10, 1);

      Battle battle = new Battle(humans, orcs);

      Army winningArmy = battle.simulate();

      System.out.println(winningArmy.toString());
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }
  }
}
