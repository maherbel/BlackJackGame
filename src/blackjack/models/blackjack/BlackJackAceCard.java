package blackjack.models.blackjack;

import com.cardgame.blackjack.enums.Suit;

import static com.cardgame.blackjack.constant.BlackJackConstants.ACE;
import static com.cardgame.blackjack.constant.BlackJackConstants.OF;

/**
 * Representation of the Ace cards in BlackJack game
 */

public class BlackJackAceCard extends BlackJackCard {
    /**
     * @param faceValue
     * @param suit
     */
    public BlackJackAceCard(int faceValue, Suit suit) {
        super(faceValue, suit);
    }

    @Override
    public void printCard() {
        System.out.print(ACE + OF + suit);
    }
}
