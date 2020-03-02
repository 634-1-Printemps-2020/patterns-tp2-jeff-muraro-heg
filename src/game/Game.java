package game;

import materials.Coin;
import materials.CoinState;
import player.Player;
import utils.Statistics;

import java.util.*;

public class Game {

    private Rules rules;
    private Coin coin;
    private Map<Player, List<CoinState>> history;

    public Game() {
        history = new HashMap<>();
        this.coin = Coin.getInstance();
        this.rules = Rules.getInstance();
    }

    /**
     * Ajouter un nouveau joueur au jeu
     *
     * @param player le nouveau joueur
     */
    public void addPlayer(Player player) {
      // TODO: Votre code ici
        history.put(player, new ArrayList<>());
    }

    /**
     * Faire jouer tous les joueurs et stocker chaque partie dans history
     */
    public void play() {
      // TODO: Votre code ici
        List<CoinState> currentCoinState;

        for (Player p : history.keySet()) {
            currentCoinState = new ArrayList<>();

            while (!rules.checkWin(currentCoinState)) {
                coin.throwCoin();
                currentCoinState.add(coin.getState());
            }

            history.put(p, currentCoinState);
        }
    }

    /**
     * Calculer des statistiques de la partie précédente
     *
     * @return Statistics
     */
    public Statistics getStatistics() {
        int fewerMoves = history.get(history.keySet().toArray()[0]).size();

        for (Player p : history.keySet()) {
            int currentFewerMoves = history.get(p).size();

            if (currentFewerMoves < fewerMoves)
                fewerMoves = currentFewerMoves;
        }

        int mostMoves = history.get(history.keySet().toArray()[0]).size();

        for (Player p : history.keySet()) {
            int currentMostMoves = history.get(p).size();

            if (currentMostMoves > mostMoves)
                mostMoves = currentMostMoves;
        }

        int totalMoves = 0;

        for (Player p : history.keySet())
            totalMoves += history.get(p).size();

        float averageToWin = totalMoves / (float)history.keySet().size();

        return new Statistics(averageToWin, fewerMoves, mostMoves, totalMoves);
    }

    /**
     * Obtenir l'historique de tous les joueurs de la partie précédente
     *
     * @return Map contenant chaque joueur et la liste des ses lancers
     */
    public Map<Player, List<CoinState>> getHistory() {
      return this.history;
    }


    /**
     * Obtenir l'historique d'un joueur spécifique
     *
     * @param player instance du joueur pour lequel on veut avoir l'historique
     * @return la liste des lancers d'un joueur
     */
    public List<CoinState> getSpecificHistory(Player player) {
      return this.history.get(player);
    }

}
