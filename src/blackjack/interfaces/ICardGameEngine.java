package blackjack.interfaces;

import com.cardgame.blackjack.models.Player;
import com.cardgame.blackjack.utils.ScannerUtil;

/**
 * Interface representing the Engine used by every game
 */

public interface ICardGameEngine {
    /**
     * Display the rules of the game
     * @return
     */
    void displayRules();

    /**
     * Init Players
     */
    void initPlayers(int playersNbr);

    /**
     * First distribution of the cards to Hands
     */
    void firstDistribution();

    /**
     *  Initialize the Deck before the game
     */
    void initializeDeck();


    /**
     * Play the game
     */
    void playGame();

    /**
     * Start playing the game
     */
    void startGame();

    /**
     * Process Hand of a single player
     * @param player
     */
    void processPlayerHand(Player player);

    /**
     * Request number of players
     * @return
     */
    int getNumberOfPlayers();

    /**
     * Play the game again
     */
    void playAgain();

    /**
     * Set the scanner utility
     * @param scannerUtil
     */
    void setPrinterUtil(ScannerUtil scannerUtil);

    /**
     * Set the scoring system
     * @param scoringSystem
     */
    void setScoringSystem(IScoringSystem scoringSystem);
}
