package blackjack.engine.poker;

import com.cardgame.blackjack.interfaces.ICardGameEngine;
import com.cardgame.blackjack.interfaces.IScoringSystem;
import com.cardgame.blackjack.models.Player;
import com.cardgame.blackjack.utils.ScannerUtil;

public class  PokerEngine implements ICardGameEngine {
    @Override
    public void displayRules() {
        // NOT SUPPORTED
    }

    @Override
    public void initPlayers(int playersNbr) {
        // NOT SUPPORTED
    }

    @Override
    public void firstDistribution() {
        // NOT SUPPORTED
    }

    @Override
    public void initializeDeck() {
        // NOT SUPPORTED
    }

    @Override
    public void playGame() {

    }

    @Override
    public void startGame() {
        // NOT SUPPORTED
    }

    @Override
    public void processPlayerHand(Player player) {
        // NOT SUPPORTED
    }

    @Override
    public int getNumberOfPlayers() {
        return 0;
    }

    @Override
    public void playAgain() {
        // NOT SUPPORTED
    }

    @Override
    public void setPrinterUtil(ScannerUtil scannerUtil) {
        // NOT SUPPORTED
    }

    @Override
    public void setScoringSystem(IScoringSystem scoringSystem) {
        // NOT SUPPORTED
    }
}
