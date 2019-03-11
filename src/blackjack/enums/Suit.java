package blackjack.enums;

/**
 * Represents the four possible suits of a card
 */

public enum Suit {
    Spade (0),
    Heart (1),
    Diamond (2),
    Club (3);

    /**
     * Value of the current Suit
     */
    private int value;
    Suit(int value){
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
    public static Suit getSuitbyValue(int value){
        switch(value){
            case 0:
                return Suit.Spade;
            case 1:
                return Suit.Heart;
            case 2:
                return Suit.Diamond;
            case 3:
                return Suit.Club;
            default:
                return null;
        }
    }
}
