package com.cardgame.blackjack.engine;

import com.cardgame.blackjack.interfaces.ICardGameEngine;

import java.util.List;

public class  PokerEngine implements ICardGameEngine {
    @Override
    public String displayRules() {
        return "Still not implemented.";
    }

    @Override
    public void initPlayers(int playersNbr) {

    }

    @Override
    public void firstDistribution() {

    }

    @Override
    public boolean playSpecificHand(int handNumber) {
        return false;
    }

    @Override
    public boolean playAllAvailableHands() {
        return false;
    }

    @Override
    public void initializeDeck() {

    }

    @Override
    public List<Integer> getWinners() {
        return null;
    }

    @Override
    public void playGame() {

    }

    @Override
    public int getNumberOfPlayers() {
        return 0;
    }
}
