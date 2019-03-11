package blackjack.engine;

import com.cardgame.blackjack.engine.blackjack.BlackJackEngine;
import com.cardgame.blackjack.engine.poker.PokerEngine;
import com.cardgame.blackjack.enums.CardGameType;
import com.cardgame.blackjack.interfaces.ICardGameEngine;

/**
 * Factory to retrieve an instance of the related Card Game Engine
 */

public class GameEngineFactory {

    /**
     * Return an instance of the Game Engine using its name
     * @param cardGameType
     * @return
     */
    public ICardGameEngine getCardGameEngine(int cardGameType){
        CardGameType cardGamebyValue = CardGameType.getCardGamebyValue(cardGameType);
        if (CardGameType.BlackJack.equals(cardGamebyValue)){
            return new BlackJackEngine();
        } else if (CardGameType.Poker.equals(cardGamebyValue)){
            return new PokerEngine();
        }
        return null;
    }
}
