package com.cardgame.blackjack.models.blackjack;

public enum BlackJackActions {
    Stand(1),
    Hit(2),
    Split(3),
    Double(4),
    Insurance(5),
    Surrender(6);

    /**
     * Value of the current Action
     */
    private int value;
    BlackJackActions(int value){
        this.value = value;
    }

    /**
     * Return the associated Value
     * @return int
     */
    public int getValue() {
        return value;
    }

    /**
     * Computes the correct Suit using a given value
     * If not found return null
     * @param value
     * @return
     */
    public static BlackJackActions getActionbyValue(int value){
        switch(value){
            case 1:
                return BlackJackActions.Stand;
            case 2:
                return BlackJackActions.Hit;
            case 3:
                return BlackJackActions.Split;
            case 4:
                return BlackJackActions.Double;
            case 5:
                return BlackJackActions.Insurance;
            case 6:
                return BlackJackActions.Surrender;
            default:
                return null;
        }
    }
}
