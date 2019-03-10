package com.cardgame.blackjack.models.blackjack;

import static com.cardgame.blackjack.constant.BlackJackConstants.FACE_CARD_VALUE;
import static com.cardgame.blackjack.constant.BlackJackConstants.OF;

import com.cardgame.blackjack.enums.FaceType;
import com.cardgame.blackjack.enums.Suit;
import com.cardgame.blackjack.interfaces.ICardState;

public class BlackJackFaceCard extends BlackJackCard {
    /**
     * The Face type of the current card
     */
    protected FaceType faceType;

    /**
     * @param faceValue
     * @param suit
     * @param state
     */
    public BlackJackFaceCard(int faceValue, Suit suit, ICardState state, FaceType faceType) {
        super(faceValue, suit, state);
        this.faceType = faceType;
    }

    @Override
    public void printCard() {
        System.out.print(faceType.getFaceName() + OF + suit);
    }

    @Override
    public int value() {
        return FACE_CARD_VALUE;
    }
}
