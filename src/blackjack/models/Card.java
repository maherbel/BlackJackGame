package blackjack.models;

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
     * Computes the value of the card
     * @return
     */
    protected abstract int value();

    /**
     *
     * @param faceValue
     * @param suit
     */
    public Card (int faceValue, Suit suit){
        this.faceValue = faceValue;
        this.suit = suit;
    }

    /**
     * Print Card informations
     */
    public abstract void printCard();
}
