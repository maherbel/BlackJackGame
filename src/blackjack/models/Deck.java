package blackjack.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents the Deck used by the Game to draw cards
 * @param <T>
 */

public class Deck <T extends Card>{
    /**
     * Deck size by default
     */
    private static final int DECK_SIZE = 52;
    /**
     * Holds the last dealt card number
     * So will start with 0 when no card have been delt
     * And it will end with the value 52 which means all the card
     * have been dealt
     */
    private int lastDealtCard;
    /**
     * Cards contained in the Deck
     */
    private List<T> deckCards;

    public Deck(List<T> deckCards, int lastDealtCard){
        this.deckCards = deckCards;
        this.lastDealtCard = lastDealtCard;
    }

    /**
     * Shuffles the cards contained in the Deck using Fisher-Yates shuffle algorithm
     * This implementation traverses the list backwards, from the last element up to the second,
     * repeatedly swapping a randomly selected element into the "current position".
     * Elements are randomly selected from the portion of the list
     * that runs from the first element to the current position, inclusive.
     */
    public void shuffleCards(){
        Collections.shuffle(deckCards);
    }

    /**
     * @return the number of cards to be dealt before all are used from Deck
     */
    public int cardsRemaining(){
        return DECK_SIZE - lastDealtCard;
    }

    /**
     * Deal a Hand and returns the cards for it
     * @param cardsNbr number of cards to be dealt
     * @return List of cards for the hand
     */
    public List<T> dealHand(int cardsNbr){
        if (cardsRemaining() < cardsNbr){
            return null;
        }

        List<T> handCards = new ArrayList<T>();
        while (handCards.size() < cardsNbr){
            T newCard = dealCard();
            handCards.add(newCard);
        }
        return handCards;
    }

    /**
     * Deal a new card from the Deck
     * The new card becomes unavailable
     * @return the new card dealt
     */
    public T dealCard() {
        if (cardsRemaining() == 0){
            return null;
        }

        T newCard = deckCards.get(lastDealtCard);
        System.out.print("The card drawn is : ");
        newCard.printCard();
        System.out.println();
        lastDealtCard++;

        return newCard;
    }
}
