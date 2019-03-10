package com.cardgame.blackjack.models.generic;

/**
 * Representation of one player in the game
 */

public class Player <T extends Card> {

    /**
     * Player's Name
     */
    private String name;

    /**
     * Player's Hand
     */
    private Hand<T> hand;

    /**
     * Indicates if the Player is still playing the game
     */
    private boolean inGame;
    /**
     * Player constructor
     * @param name
     * @param hand
     */
    public Player(String name, Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    public Player(String name, boolean inGame) {
        this.name = name;
        this.inGame = inGame;
    }

    /**
     * Add new card to the Hand's player
     * @param newCard
     */
    public void addNewCard(T newCard){
        hand.addNewCard(newCard);
    }

    public String getName() {
        return name;
    }

    public boolean isInGame() {
        return inGame;
    }

    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }

    public Hand<T> getHand() {
        return hand;
    }

    public void setHand(Hand<T> hand) {
        this.hand = hand;
    }

    public void displayHand(){
        hand.printHand();
    }
}
