package org.ntnu.petteed;

public class WargamesApp {

  public static void main(String[] args) {
    try {
      Army humans = Army.createTestUnits("Humans", "Dude", 3, 1);
      Army orcs = Army.createTestUnits("Orcs", "Gundabad", 5, 1);

      Battle battle = new Battle(humans, orcs);

      Army winningArmy = battle.simulate();

      System.out.println(winningArmy.toString());
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }
  }
}
