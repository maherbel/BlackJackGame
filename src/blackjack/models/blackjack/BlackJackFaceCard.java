package blackjack.models.blackjack;

import com.cardgame.blackjack.enums.FaceType;
import com.cardgame.blackjack.enums.Suit;

import static com.cardgame.blackjack.constant.BlackJackConstants.FACE_CARD_VALUE;
import static com.cardgame.blackjack.constant.BlackJackConstants.OF;

/**
 * Representation of a Face card in a BlackJack game
 */

public class BlackJackFaceCard extends BlackJackCard {
    /**
     * The Face type of the current card
     */
    protected FaceType faceType;

    /**
     * @param faceValue
     * @param suit
     */
    public BlackJackFaceCard(int faceValue, Suit suit, FaceType faceType) {
        super(faceValue, suit);
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
