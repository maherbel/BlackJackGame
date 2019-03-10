package com.cardgame.blackjack.models.blackjack;

import static com.cardgame.blackjack.constant.BlackJackConstants.ACE;
import static com.cardgame.blackjack.constant.BlackJackConstants.ACE_CARD_MAX_VALUE;
import static com.cardgame.blackjack.constant.BlackJackConstants.ACE_CARD_MIN_VALUE;
import static com.cardgame.blackjack.constant.BlackJackConstants.OF;

import com.cardgame.blackjack.enums.Suit;
import com.cardgame.blackjack.interfaces.ICardState;

public class BlackJackAceCard extends BlackJackCard {
    /**
     * @param faceValue
     * @param suit
     * @param state
     */
    public BlackJackAceCard(int faceValue, Suit suit, ICardState state) {
        super(faceValue, suit, state);
    }

    public int maximumValue(){
        return ACE_CARD_MAX_VALUE;
    }

    public int minimumValue(){
        return ACE_CARD_MIN_VALUE;
    }

    @Override
    public void printCard() {
        System.out.print(ACE + OF + suit);
    }
}
