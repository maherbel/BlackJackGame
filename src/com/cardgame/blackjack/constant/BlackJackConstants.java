package com.cardgame.blackjack.constant;

/**
 * Constants for BlackJackGame
 */

public final class BlackJackConstants {

    /**
     * Face value for (Jack, Queen, King)
     */
    public static final int FACE_CARD_VALUE = 10;
    /**
     * Ace can be counted 1 if couting 11 goes beyond 21
     */
    public static final int ACE_CARD_MIN_VALUE = 1;
    /**
     * Ace can be counted 11 if couting 11 does not go beyond 21
     */
    public static final int ACE_CARD_MAX_VALUE = 11;


    /**
     * String representation of the "normal" cards
     */
    public static final String TWO = "Two";
    public static final String THREE = "Three";
    public static final String FOUR = "Four";
    public static final String FIVE = "Five";
    public static final String SIX = "Six";
    public static final String SEVEN = "Seven";
    public static final String EIGHT = "Eight";
    public static final String NINE = "Nine";
    public static final String TEN = "Ten";

    /**
     * String representation of the Ace and Face cards
     */
    public static final String ACE = "Ace";
    public static final String JACK = "Jack";
    public static final String QUEEN = "Queen";
    public static final String KING = "King";

    public static final String OF = " of ";

    private BlackJackConstants(){
        // In case this constructor is called
        // it will throw an error
        throw new AssertionError();
    }
}
