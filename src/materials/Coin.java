package materials;

import java.util.Random;

public class Coin {

  private CoinState coinState;
  private static Coin singletonCoin;

  private Coin() {

  }

  public static Coin getInstance() {
    if (singletonCoin == null) { singletonCoin = new Coin(); }
    return singletonCoin;
  }

  /**
   * Change l'état de la pièce.
   * 50% de probabilité d'obtenir HEADS, 50% de probabilité d'obtenir TAILS
   */
  public void throwCoin() {
    Random rand = new Random();

    if (rand.nextInt() % 2 == 0)
      coinState = CoinState.HEADS;
    else
      coinState = CoinState.TAILS;
  }

  public CoinState getState() {
    return coinState;
  }


}
