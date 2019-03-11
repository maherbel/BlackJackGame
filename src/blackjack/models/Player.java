package blackjack.models;

/**
 * Representation of one player in the game
 */

public class Player <T extends Card> {

    /**
     * Player's Name
     */
    private String name;

    /**
     * Player's Hand
     */
    private Hand<T> hand;

    /**
     * Indicates if the Player is still playing the game
     */
    private boolean inGame;

    public Player(String name, boolean inGame) {
        this.name = name;
        this.inGame = inGame;
    }

    public String getName() {
        return name;
    }

    public boolean isInGame() {
        return inGame;
    }

    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }

    public Hand<T> getHand() {
        return hand;
    }

    public void setHand(Hand<T> hand) {
        this.hand = hand;
    }
}
