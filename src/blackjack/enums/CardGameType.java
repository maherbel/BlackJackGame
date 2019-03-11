package blackjack.enums;

/**
 * Represents the Games available
 */

public enum CardGameType {
    BlackJack(1),
    Poker(2);

    /**
     * Value of the current CardGameType
     */
    private int value;
    CardGameType(int value){
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
     * Computes the correct CardGameType using a given value
     * If not found return null
     * @param value
     * @return
     */
    public static CardGameType getCardGamebyValue(int value){
        switch(value){
            case 1:
                return CardGameType.BlackJack;
            case 2:
                return CardGameType.Poker;
            default:
                return null;
        }
    }
}
