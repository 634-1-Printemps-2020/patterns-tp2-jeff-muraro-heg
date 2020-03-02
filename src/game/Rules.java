package game;

import materials.Coin;
import materials.CoinState;

import java.util.List;

public class Rules {
  private static Rules singletonRules;

  private Rules() {
  }

  public static Rules getInstance() {
    if (singletonRules == null) { singletonRules = new Rules(); }
    return singletonRules;
  }

  /**
   * Cette méthode permet de déterminer si une suite d'états de pièce permet de gagner à une partie
   * @param states liste d'états pour un joueur
   * @return true si un joueur a gagné, false sinon
   */
  public boolean checkWin(List<CoinState> states) {
    int headsCounter = 0;

    for (CoinState coinState : states) {
      if (coinState == CoinState.HEADS)
        headsCounter += 1;
      else
        headsCounter = 0;
    }

    if (headsCounter >= 3)
      return true;
    else
      return false;
  }
}
