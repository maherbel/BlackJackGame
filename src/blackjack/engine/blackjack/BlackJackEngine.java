package blackjack.engine.blackjack;

import com.cardgame.blackjack.enums.FaceType;
import com.cardgame.blackjack.enums.Suit;
import com.cardgame.blackjack.interfaces.ICardGameEngine;
import com.cardgame.blackjack.interfaces.IScoringSystem;
import com.cardgame.blackjack.models.Deck;
import com.cardgame.blackjack.models.Player;
import com.cardgame.blackjack.models.blackjack.*;
import com.cardgame.blackjack.utils.ScannerUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the Engine for the BlackJack
 */

public class BlackJackEngine implements ICardGameEngine {
    /**
     * Limit of the Dealer on which he cannot draw anymore cards
     * If the Hand total is 16 or less he has to draw a new card
     */
    private static final int HIT_UNTIL = 16;

    /**
     * Minimum Bet allowed
     */
    private static final int MINIMUM_BET = 10;
    /**
     * Dealer of the Game
     */
    private BlackJackDealer dealer;
    /**
     * Hands played at the game
     */
    private List<BlackJackPlayer> gamePlayers;
    /**
     * Deck used for the game
     */
    private Deck<BlackJackCard> gameDeck;
    /**
     * Engine for the different actions that can be done ingame
     */
    private BlackJackActionsEngine actionsEngine;
    /**
     * Scoring engine used by this game
     */
    private IScoringSystem scoringSystem;
    /**
     * Scanner Utility
     */
    private ScannerUtil scannerUtil;


    public BlackJackEngine() {
    }

    @Override
    public void playGame() {
        // Display Rules
        displayRules();
        // Init the Deck for the game
        initializeDeck();
        // Request the number of players
        int playersNbr = getNumberOfPlayers();
        // Init Players and the Dealer
        initPlayers(playersNbr);
        // Place first Bets
        placeFirstBets();
        // First distribution of cards
        firstDistribution();
        // Start the game
        startGame();
        // Check if the players want to play again
        playAgain();
    }

    @Override
    public void displayRules() {
        System.out.println();
        System.out.println("******************Rules of BlackJack******************");
        System.out.println("  Number of Players: Up to 4 players.\n" +
                "\n" +
                "  Rank of Cards: Cards have no relative rank but the counting value is as follows: K’s, Q’s, and J’s, 10 each; Aces, 11 or 1, balance are counted at pip value, 9’s 9, 8’s 8, etc.\n" +
                "\n" +
                "  Betting Before the Deal: Before cards are dealt each player, except the dealer, makes a bet placing the counters or chips before him. \n" +
                "  Dealer makes no bet, but is the banker, who takes and pays all player’s bets.\n" +
                "\n" +
                "  Object of the Game: To hold cards, the collective pip value of which most nearly approaches 21, without passing that number.\n" +
                "\n" +
                "  Dealing: Dealer gives each player two cards one at a time.\n" +
                "\n" +
                "  Drawing and Settling of Bets: Each player examines cards dealt him. If dealer’s cards consist of an Ace and 10 (or Court card) it is called a Natural, \n" +
                "  and each player (unless he also has a Natural) loses twice the amount he has staked.\n" +
                "  Should a player have a Natural and dealer none, dealer must pay player double.\n" +
                "\n" +
                "  If no player receives a Natural (or after players [other than dealer] have been paid for Naturals held), \n" +
                "  each player in turn may ask for a card so as to bring the pip value of his hand nearer to 21. \n" +
                "  Drawing begins with eldest hand, and he may draw one card at a time until he is satisfied, \n" +
                "  or until the pip value of his hand exceeds 21. In latter case he must abandon his hand and pay his stake to dealer.\n" +
                "\n" +
                "  Next player draws in same manner, and so on until each player is either satisfied or overdrawn. \n" +
                "  The dealer then draws. If dealer overdraws, he pays each player who has not overdrawn the amount of that player’s stake. \n" +
                "  If dealer has 21 or less, players having same amount are tied and neither win nor lose, those holding less lose their stake, \n" +
                "  while those holding more than dealer but not more than 21 win the amount of their stakes.");
        System.out.println("******************************************************");
        System.out.println();
    }

    @Override
    public void initializeDeck() {
        List<BlackJackCard> cards = new ArrayList<>();
        // Initialize Aces
        for (int j = 0; j <= 3; j++) {
            Suit suit = Suit.getSuitbyValue(j);
            BlackJackCard card = new BlackJackAceCard(1, suit);
            cards.add(card);
        }
        // Initialize Normal cards
        for (int i = 2; i <= 10; i++) {
            for (int j = 0; j <= 3; j++) {
                Suit suit = Suit.getSuitbyValue(j);
                BlackJackCard card = new BlackJackCard(i, suit);
                cards.add(card);
            }
        }
        // Initialize Face cards
        for (int j = 0; j <= 3; j++) {
            for (int p = 0; p <= 2; p++) {
                Suit suit = Suit.getSuitbyValue(j);
                FaceType faceType = FaceType.getFaceTypebyValue(p);
                BlackJackCard card = new BlackJackFaceCard(10, suit, faceType);
                cards.add(card);
            }
        }
        // Init the Deck with the card and with 0 cards already Dealt
        gameDeck = new Deck<>(cards, 0);
        gameDeck.shuffleCards();
    }

    @Override
    public int getNumberOfPlayers() {
        System.out.print("Please input the number of players (1-8) for this game : ");
        int ans = scannerUtil.nextInt();
        while (ans > 8){
            System.out.println("Sorry, the game cannot be played with more than 8 players !");
            System.out.print("Please input again : ");
            ans = scannerUtil.nextInt();
        }
        return ans;
    }

    @Override
    public void initPlayers(int playersNbr) {
        gamePlayers = new ArrayList<>();
        for (int i=1; i <= playersNbr; i++){
            System.out.print("Please input the player "+i+" name : ");
            String playerName = scannerUtil.nextString();
            BlackJackPlayer newPlayer = new BlackJackPlayer(playerName, true);
            gamePlayers.add(newPlayer);
        }
        dealer = new BlackJackDealer("Dealer", true);
        System.out.println();
    }

    private void placeFirstBets() {
        System.out.println("Please place your bets !");
        for (BlackJackPlayer player : gamePlayers){
            System.out.print("Player ("+player.getName()+"), what is your bet (Minimum is "+MINIMUM_BET+"$) ? ");
            int playerBet = scannerUtil.nextInt();
            while (playerBet < MINIMUM_BET){
                System.out.print("Minimum Bet is " + MINIMUM_BET + "$, Choose again : ");
                playerBet = scannerUtil.nextInt();
            }
            player.setCurrentBet(playerBet);
        }
        System.out.println();
    }

    @Override
    public void firstDistribution() {
        for (BlackJackPlayer player : gamePlayers){
            System.out.println("Drawing a hand for player ("+player.getName()+")");
            List<BlackJackCard> handCards = gameDeck.dealHand(2);
            player.setHand(new BlackJackHand(handCards));
            System.out.println();
        }
        firstDistributionDealer();
    }

    @Override
    public void startGame() {
        // Check for BlackJack Hands
        checkBlackJackHands();

        // Init booleans for game state
        actionsEngine = new BlackJackActionsEngine(scannerUtil, gameDeck, dealer, scoringSystem);

        for (BlackJackPlayer player : gamePlayers){
            processPlayerHand(player);
        }

        List<BlackJackPlayer> playersInGame = gamePlayers
                .stream()
                .filter(blackJackPlayer -> blackJackPlayer.isInGame())
                .collect(Collectors.toList());

        if (playersInGame.isEmpty()){
            System.out.println("There is no more players in the Game.");
            System.out.println("The game has ended ! We hope you enjoyed your time playing !");
            return;
        } else {
            System.out.println("Players that are still playing : ");
            for (BlackJackPlayer player : playersInGame){
                actionsEngine.displayHand(player, player.getHand(), player.getCurrentBet());
            }
            System.out.println();
        }

        processDealerHand();
        System.out.println();
        actionsEngine.computeWinningPlayers(playersInGame);

        System.out.println("The game has ended ! We hope you enjoyed your time playing !");
    }

    private void checkBlackJackHands() {
        for (BlackJackPlayer player : gamePlayers){
            BlackJackHand playerHand = (BlackJackHand)player.getHand();
            if (playerHand.isBlackJack()){
                System.out.println("Player ("+player.getName()+") has a BlackJack !");
                System.out.println("His current bet is ("+player.getCurrentBet()+"), payout is 3:2");
                System.out.println("Gains are : "+(player.getCurrentBet()*1.5));
                scoringSystem.logGain(player, player.getCurrentBet()*1.5);
                player.setInGame(false);
                System.out.println("Player ("+player.getName()+") has left the game.");
            }
        }
    }

    @Override
    public void processPlayerHand(Player player) {
        BlackJackPlayer blackJackPlayer = (BlackJackPlayer)player;
        System.out.println("It is Player ("+player.getName()+") turn.");
        System.out.println("Your current bet is : "+blackJackPlayer.getCurrentBet()+"$");
        System.out.print("Your hand is : ");
        blackJackPlayer.getHand().printHand();
        BlackJackActions action = actionsEngine.computePlayerAction();
        actionsEngine.processAction(action, blackJackPlayer);
        System.out.println();
        System.out.println();
    }

    private void firstDistributionDealer() {
        List<BlackJackCard> dealerCards = new ArrayList<>();
        System.out.println("Drawing the dealer's first card");
        BlackJackCard card = gameDeck.dealCard();
        dealerCards.add(card);
        dealer.setHand(new BlackJackHand(dealerCards));
        System.out.println();
    }

    private void processDealerHand() {
        System.out.println("Please input any character and press on enter so that Dealer starts drawing cards..");
        scannerUtil.nextString();
        System.out.println("Dealer current Hand is : ");
        BlackJackHand dealerHand = (BlackJackHand)dealer.getHand();
        dealerHand.printHand();
        while (dealerHand.computeHandScore() < HIT_UNTIL){
            BlackJackCard card = gameDeck.dealCard();
            dealerHand.addNewCard(card);
        }
        System.out.print("Dealer's hand is : ");
        dealerHand.printHand();
    }

    @Override
    public void playAgain() {
        System.out.println();
        System.out.println("Do you want to play again ? (y/n)");
        String ans = scannerUtil.nextString();
        while (!"y".equals(ans) && !"n".equals(ans)){
            System.out.println("Invalid input, Do you want to play again ? (y/n)");
            ans = scannerUtil.nextString();
        }

        if ("y".equals(ans)){
            playGame();
        }
    }

    @Override
    public void setPrinterUtil(ScannerUtil scannerUtil) {
        this.scannerUtil = scannerUtil;
    }

    @Override
    public void setScoringSystem(IScoringSystem scoringSystem) {
        this.scoringSystem = scoringSystem;
    }
}
