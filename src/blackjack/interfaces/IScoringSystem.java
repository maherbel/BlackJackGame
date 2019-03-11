package blackjack.interfaces;

import com.cardgame.blackjack.models.Player;

/**
 * Interface representing the Scoring System used by every game
 */

public interface IScoringSystem {

    /**
     * Initialize the scoring system as it can be an offline one
     * or a structure in memory that would be reset a each restart of the program
     */
    void init();

    /**
     * Add A new entry of a new gain for a given player
     * @param player
     */
    void logGain(Player player, double newGain);

    /**
     * Add A new entry of a new lost for a given player
     * @param player
     */
    void logLost(Player player, double lost);

    /**
     * Display Scores for a game
     */
    void displayScores();
}
