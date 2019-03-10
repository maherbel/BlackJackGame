package com.cardgame.blackjack.models.blackjack;

import com.cardgame.blackjack.models.generic.Player;

public class BlackJackPlayer extends Player<BlackJackCard> {

    private boolean playerStanding;

    private Integer currentBet;

    private BlackJackHand hand;

    public BlackJackPlayer(String name, boolean inGame) {
        super(name, inGame);
    }

    public Integer getCurrentBet() {
        return currentBet;
    }

    public void setCurrentBet(Integer currentBet) {
        this.currentBet = currentBet;
    }

    @Override
    public BlackJackHand getHand() {
        return hand;
    }

    public void setHand(BlackJackHand hand) {
        this.hand = hand;
    }

    public boolean isPlayerStanding() {
        return playerStanding;
    }

    public void setPlayerStanding(boolean playerStanding) {
        this.playerStanding = playerStanding;
    }
}
