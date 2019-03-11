package blackjack.engine;

import com.cardgame.blackjack.engine.blackjack.BlackJackScoringSystem;
import com.cardgame.blackjack.engine.poker.PokerScoringSystem;
import com.cardgame.blackjack.enums.CardGameType;
import com.cardgame.blackjack.interfaces.IScoringSystem;

/**
 * Factory to retrieve an instance of the related Scoring system
 */

public class ScoringSystemFactory {

    /**
     * Return an instance of the Game Engine using its name
     * @param cardGameType
     * @return
     */
    public IScoringSystem getScoringSystem(int cardGameType){
        CardGameType cardGamebyValue = CardGameType.getCardGamebyValue(cardGameType);
        if (CardGameType.BlackJack.equals(cardGamebyValue)){
            return new BlackJackScoringSystem();
        } else if (CardGameType.Poker.equals(cardGamebyValue)){
            return new PokerScoringSystem();
        }
        return null;
    }
}
