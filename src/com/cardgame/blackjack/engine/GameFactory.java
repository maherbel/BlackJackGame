package com.cardgame.blackjack.engine;

import com.cardgame.blackjack.enums.CardGameType;
import com.cardgame.blackjack.interfaces.ICardGameEngine;

/**
 * Factory to retrieve an instance of the related Card Game Engine
 */

public class GameFactory {

    /**
     * Return an instance of the Game Engine using its name
     * @param cardGameType
     * @return
     */
    public ICardGameEngine getCardGameEngine(String cardGameType){
        if (CardGameType.BlackJack.name().equals(cardGameType)){
            return new BlackJackEngine();
        } else if (CardGameType.Poker.name().equals(cardGameType)){
            return new PokerEngine();
        }
        return null;
    }
}
