package com.cardgame.blackjack.models.blackjack;

import static com.cardgame.blackjack.enums.blackjack.BlackJackCardState.Unavailable;
import static com.cardgame.blackjack.constant.BlackJackConstants.OF;
import static com.cardgame.blackjack.constant.BlackJackConstants.TWO;
import static com.cardgame.blackjack.constant.BlackJackConstants.THREE;
import static com.cardgame.blackjack.constant.BlackJackConstants.FOUR;
import static com.cardgame.blackjack.constant.BlackJackConstants.FIVE;
import static com.cardgame.blackjack.constant.BlackJackConstants.SIX;
import static com.cardgame.blackjack.constant.BlackJackConstants.SEVEN;
import static com.cardgame.blackjack.constant.BlackJackConstants.EIGHT;
import static com.cardgame.blackjack.constant.BlackJackConstants.NINE;
import static com.cardgame.blackjack.constant.BlackJackConstants.TEN;

import com.cardgame.blackjack.enums.Suit;
import com.cardgame.blackjack.interfaces.ICardState;
import com.cardgame.blackjack.models.generic.Card;

public class BlackJackCard extends Card {
    /**
     *
     * @param faceValue
     * @param suit
     * @param state
     */
    public BlackJackCard(int faceValue, Suit suit, ICardState state) {
        super(faceValue, suit, state);
    }

    @Override
    public void printCard() {
        switch(faceValue){
            case 2:
                System.out.print(TWO + OF + suit);
                break;
            case 3:
                System.out.print(THREE + OF + suit);
                break;
            case 4:
                System.out.print(FOUR + OF + suit);
                break;
            case 5:
                System.out.print(FIVE + OF + suit);
                break;
            case 6:
                System.out.print(SIX + OF + suit);
                break;
            case 7:
                System.out.print(SEVEN + OF + suit);
                break;
            case 8:
                System.out.print(EIGHT + OF + suit);
                break;
            case 9:
                System.out.print(NINE + OF + suit);
                break;
            case 10:
                System.out.print(TEN + OF + suit);
                break;
        }
    }

    @Override
    public int value() {
        return faceValue;
    }

    @Override
    public void modifyState() {
        cardState = Unavailable;
    }

    public int maximumValue(){
        return value();
    }

    public int minimumValue(){
        return value();
    }

}
