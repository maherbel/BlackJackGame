package blackjack.models.blackjack;

import com.cardgame.blackjack.enums.Suit;
import com.cardgame.blackjack.models.Card;

import static com.cardgame.blackjack.constant.BlackJackConstants.*;

/**
 * Representation of a classic Card in a BlackJack game
 */

public class BlackJackCard extends Card {
    /**
     *
     * @param faceValue
     * @param suit
     */
    public BlackJackCard(int faceValue, Suit suit) {
        super(faceValue, suit);
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

}
