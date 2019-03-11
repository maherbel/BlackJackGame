package blackjack.enums;

/**
 * Defines the Face type of a Card
 */

public enum FaceType {
    Jack ("Jack", 0),
    Queen ("Queen", 1),
    King ("King", 2);

    private String faceName;
    private int value;

    FaceType(String faceName, int value) {
        this.faceName = faceName;
        this.value = value;
    }

    public String getFaceName() {
        return faceName;
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
    public static FaceType getFaceTypebyValue(int value){
        switch(value){
            case 0:
                return FaceType.Jack;
            case 1:
                return FaceType.Queen;
            case 2:
                return FaceType.King;
            default:
                return null;
        }
    }
}
