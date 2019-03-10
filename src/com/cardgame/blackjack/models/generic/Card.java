package com.cardgame.blackjack.models.generic;

import com.cardgame.blackjack.interfaces.ICardState;
import com.cardgame.blackjack.enums.Suit;

/**
 * Abstract single Card in a Card Game
 */

public abstract class Card {

    /**
     * Value of the current Card
     */
    protected int faceValue;

    /**
     * Category(Suit) of the card
     */
    protected Suit suit;

    /**
     * State of the card
     */
    protected ICardState cardState;

    /**
     * Computes the value of the card
     * @return
     */
    protected abstract int value();

    /**
     * Computes the max value of the card
     * @return
     */
    public abstract int maximumValue();

    /**
     * Computes the min value of the card
     * @return
     */
    public abstract int minimumValue();

    /**
     * Modify the state of the card
     */
    public abstract void modifyState();
    /**
     *
     * @param faceValue
     * @param suit
     * @param cardState
     */
    public Card (int faceValue, Suit suit, ICardState cardState){
        this.faceValue = faceValue;
        this.suit = suit;
        this.cardState = cardState;
    }

    /**
     * Print Card informations
     */
    public abstract void printCard();
}
