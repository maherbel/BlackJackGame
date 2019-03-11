package blackjack.models.blackjack;

import com.cardgame.blackjack.models.Player;

public class BlackJackPlayer extends Player<BlackJackCard> {

    private boolean playerStanding;

    private Integer currentBet;

    public BlackJackPlayer(String name, boolean inGame) {
        super(name, inGame);
    }

    public Integer getCurrentBet() {
        return currentBet;
    }

    public void setCurrentBet(Integer currentBet) {
        this.currentBet = currentBet;
    }

    public boolean isPlayerStanding() {
        return playerStanding;
    }

    public void setPlayerStanding(boolean playerStanding) {
        this.playerStanding = playerStanding;
    }
}
