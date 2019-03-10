package com.cardgame.blackjack.engine;

import com.cardgame.blackjack.enums.FaceType;
import com.cardgame.blackjack.enums.Suit;
import com.cardgame.blackjack.enums.blackjack.BlackJackCardState;
import com.cardgame.blackjack.interfaces.ICardGameEngine;
import com.cardgame.blackjack.models.blackjack.*;
import com.cardgame.blackjack.models.generic.Deck;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BlackJackEngine implements ICardGameEngine {
    /**
     * Limit of the Dealer on which he cannot draw anymore cards
     * If the Hand total is 16 or less he has to draw a new card
     */
    private static final int HIT_UNTIL = 16;
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
     * Scanner used for interaction with players
     */
    private Scanner sc;

    public BlackJackEngine() {
    }

    @Override
    public void playGame() {
        sc = new Scanner(System.in);
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
        startBlackJackGame();
    }

    private void startBlackJackGame() {
        // Check for BlackJack Hands
        checkBlackJackHands();
        // Init booleans for game state
        BlackJackActionsEngine actionsEngine = new BlackJackActionsEngine(sc, gameDeck, dealer);

        for (BlackJackPlayer player : gamePlayers){
            System.out.println("It is Player ("+player.getName()+") turn.");
            System.out.println("Your current bet is : "+player.getCurrentBet());
            System.out.println("Your hand is ");
            player.getHand().printHand();
            BlackJackActions action = actionsEngine.computePlayerAction();
            actionsEngine.processAction(action, player);
        }

        List<BlackJackPlayer> playersInGame = gamePlayers
                .stream()
                .filter(blackJackPlayer -> blackJackPlayer.isInGame())
                .collect(Collectors.toList());

        if (playersInGame.isEmpty()){
            System.out.println("There is no more players in the Game.");
            System.out.println("The game has ended ! We hope you enjoyed your time playing !");
            return;
        }

        System.out.println("Dealer is now drawing cards !");
        System.out.println("Dealer current Hand is : ");
        BlackJackHand dealerHand = dealer.getHand();
        dealerHand.printHand();
        while (dealerHand.computeHandScore() < HIT_UNTIL){
            BlackJackCard card = gameDeck.dealCard();
            System.out.println("Dealer did draw the card : ");
            card.printCard();
            dealerHand.addNewCard(card);
        }

        actionsEngine.computeWinningPlayers(playersInGame);

        System.out.println("The game has ended ! We hope you enjoyed your time playing !");
    }

    private void checkBlackJackHands() {
        for (BlackJackPlayer player : gamePlayers){
            if (player.getHand().isBlackJack()){
                System.out.println("Player ("+player.getName()+") has a BlackJack !");
                System.out.println("His current bet is ("+player.getCurrentBet()+"), payout is 3:2");
                System.out.println("Gains are : "+(player.getCurrentBet()*1.5));
                // TODO Manage score here
                player.setInGame(false);
                System.out.println("Player ("+player.getName()+") has left the game.");
            }
        }
    }

    @Override
    public int getNumberOfPlayers() {
        System.out.print("Please input the number of players for this game : ");
        int ans = sc.nextInt();
        while (ans > 4){
            System.out.println("Sorry, the game cannot be played with more than 4 players !");
            System.out.println("Please input again : ");
            ans = sc.nextInt();
        }
        return ans;
    }

    @Override
    public String displayRules() {
        //TODO Rules to display
        return "";
    }

    @Override
    public void initPlayers(int playersNbr) {
        gamePlayers = new ArrayList<>();
        for (int i=1; i <= playersNbr; i++){
            System.out.print("Please input the player "+i+" name : ");
            String playerName = sc.next();
            BlackJackPlayer newPlayer = new BlackJackPlayer(playerName, true);
            gamePlayers.add(newPlayer);
        }
        dealer = new BlackJackDealer("Dealer", true);
    }

    private void placeFirstBets() {
        System.out.println("Please place your bets !");
        for (BlackJackPlayer player : gamePlayers){
            System.out.print("Player ("+player.getName()+"), what is your bet (Minimum is 10$) ? ");
            int playerBet = sc.nextInt();
            player.setCurrentBet(playerBet);
        }
    }

    @Override
    public void firstDistribution() {
        for (BlackJackPlayer player : gamePlayers){
            List<BlackJackCard> handCards = gameDeck.dealHand(2);
            player.setHand(new BlackJackHand(handCards));
        }
        firstDistributionDealer();
    }

    private void firstDistributionDealer() {
        List<BlackJackCard> dealerCards = new ArrayList<>();
        BlackJackCard card = gameDeck.dealCard();
        dealerCards.add(card);
        dealer.setHand(new BlackJackHand(dealerCards));
    }

    @Override
    public boolean playSpecificHand(int handNumber) {
        return false;
    }

    @Override
    public boolean playAllAvailableHands() {
        return false;
    }

    @Override
    public void initializeDeck() {
        List<BlackJackCard> cards = new ArrayList<>();
        // Initialize Aces
        for (int j = 0; j <= 3; j++) {
            Suit suit = Suit.getSuitbyValue(j);
            BlackJackCard card = new BlackJackAceCard(1, suit, BlackJackCardState.Available);
            cards.add(card);
        }
        // Initialize Normal cards
        for (int i = 2; i <= 10; i++) {
            for (int j = 0; j <= 3; j++) {
                Suit suit = Suit.getSuitbyValue(j);
                BlackJackCard card = new BlackJackCard(i, suit, BlackJackCardState.Available);
                cards.add(card);
            }
        }
        // Initialize Face cards
        for (int j = 0; j <= 3; j++) {
            for (int p = 0; p <= 2; p++) {
                Suit suit = Suit.getSuitbyValue(j);
                FaceType faceType = FaceType.getFaceTypebyValue(p);
                BlackJackCard card = new BlackJackFaceCard(10, suit, BlackJackCardState.Available, faceType);
                cards.add(card);
            }
        }
        // Init the Deck with the card and with 0 cards already Dealt
        gameDeck = new Deck<>(cards, 0);
        gameDeck.shuffleCards();
    }

    @Override
    public List<Integer> getWinners() {
        return null;
    }
}
