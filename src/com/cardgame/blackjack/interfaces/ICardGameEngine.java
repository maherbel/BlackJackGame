package com.cardgame.blackjack.interfaces;

import java.util.List;

public interface ICardGameEngine {
    /**
     * Display the rules of the game
     * @return
     */
    String displayRules();

    /**
     * Init Players
     */
    void initPlayers(int playersNbr);

    /**
     * First distribution of the cards to Hands
     */
    void firstDistribution();

    /**
     * Play cards to a specific hand
     * @param handNumber
     * @return
     */
    boolean playSpecificHand(int handNumber);

    /**
     * Play all the hands on the game
     * @return
     */
    boolean playAllAvailableHands();

    /**
     *  Initialize the Deck before the game
     */
    void initializeDeck();

    /**
     * Retrieve the winners depending on the state of the hands/Game
     * @return
     */
    List<Integer> getWinners();

    /**
     * Play the game
     */
    void playGame();

    /**
     * Request number of players
     * @return
     */
    int getNumberOfPlayers();
}
