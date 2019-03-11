package blackjack.models;

import java.util.List;

/**
 * Representation of the Hand used by a player in a game
 * @param <T>
 */

public abstract class Hand <T extends Card> {
    /**
     * List of cards that are hold by the hand
     */
    protected List<T> handCards;


    public Hand(List<T> handCards) {
        this.handCards = handCards;
    }

    /**
     * Computes the score of the hand
     * @return
     */
    public abstract int computeHandScore();

    /**
     * Add a new card to the current Hand
     * @param card
     */
    public void addNewCard(T card){
        handCards.add(card);
    }

    public void printHand(){
        for (T card : handCards){
            card.printCard();
            System.out.print(" | ");
        }
        System.out.println("Score : "+ this.computeHandScore());
    }
}
