package blackjack.engine.blackjack;

import com.cardgame.blackjack.interfaces.IScoringSystem;
import com.cardgame.blackjack.models.Deck;
import com.cardgame.blackjack.models.Hand;
import com.cardgame.blackjack.models.blackjack.*;
import com.cardgame.blackjack.utils.ScannerUtil;

import java.util.ArrayList;
import java.util.List;

public class BlackJackActionsEngine {

    private final ScannerUtil sc;
    private final Deck<BlackJackCard> gameDeck;
    private final BlackJackDealer dealer;
    private final IScoringSystem scoringSystem;

    public BlackJackActionsEngine(ScannerUtil sc, Deck<BlackJackCard> gameDeck, BlackJackDealer dealer,
                                  IScoringSystem scoringSystem) {
        this.sc = sc;
        this.gameDeck = gameDeck;
        this.dealer = dealer;
        this.scoringSystem = scoringSystem;
    }

    public void processAction(BlackJackActions action, BlackJackPlayer player) {
        BlackJackHand playerHand = (BlackJackHand)player.getHand();
        Integer currentBet = player.getCurrentBet();
        switch(action){
            case Stand:
                processStand(player);
                break;
            case Hit:
                processHit(player, playerHand, currentBet);
                break;
            case Surrender:
                processSurrender(player,currentBet / 2);
                break;
            case Double:
                processDouble(player, playerHand, currentBet);
                break;
            case Insurance:
                processInsurance(player, playerHand, currentBet);
                break;
            case Split:
                processSplit(player, playerHand, currentBet);
            case DisplayHand:
                displayHand(player, playerHand, currentBet);
                chooseActionAgain(player, "");
                break;
        }
    }

    public void displayHand(BlackJackPlayer player, Hand<BlackJackCard> playerHand, Integer currentBet) {
        System.out.print("Player (" + player.getName() + ") has the Hand : ");
        playerHand.printHand();
        System.out.println("His bet is : "+currentBet+"$ !");
    }

    private void processSplit(BlackJackPlayer player, BlackJackHand playerHand, Integer currentBet) {
        // TODO
        chooseActionAgain(player, "The Split action is not supported yet, please choose another action.");
    }

    private void processInsurance(BlackJackPlayer player, BlackJackHand playerHand, Integer currentBet) {
        // TODO
        chooseActionAgain(player, "The Insurance action is not supported yet, please choose another action.");
    }

    private void chooseActionAgain(BlackJackPlayer player, String message) {
        System.out.println(message);
        BlackJackActions newAction = computePlayerAction();
        processAction(newAction, player);
    }

    private void processDouble(BlackJackPlayer player, BlackJackHand playerHand, Integer currentBet) {
        player.setCurrentBet(currentBet *2);
        System.out.print("Bets have been doubled to : " + player.getCurrentBet());
        System.out.println();
        BlackJackCard doubleCard = gameDeck.dealCard();
        playerHand.addNewCard(doubleCard);
        System.out.println("Player ("+player.getName()+") hand is now : ");
        playerHand.printHand();
        if (playerHand.isBusted()){
            System.out.println("Player ("+player.getName()+") hand is busted. He is out of the game.");
            System.out.println("Loosing bets are : "+ player.getCurrentBet());
            scoringSystem.logLost(player, player.getCurrentBet());
            player.setInGame(false);
        } else {
            System.out.println("Player ("+player.getName()+") cannot receive any more cards.");
            player.setPlayerStanding(true);
        }
    }

    private void processSurrender(BlackJackPlayer player, int currBet) {
        System.out.println("Player (" + player.getName() + ") has surrendered. He is out of the game.");
        System.out.println("Loosing bets are : " + currBet);
        scoringSystem.logLost(player, currBet);
        player.setInGame(false);
    }

    private void processHit(BlackJackPlayer player, BlackJackHand playerHand, Integer currentBet) {
        BlackJackCard newCard = gameDeck.dealCard();
        playerHand.addNewCard(newCard);
        System.out.println("Player ("+player.getName()+") hand is now : ");
        playerHand.printHand();
        if (playerHand.isBusted()){
            System.out.println("Player ("+player.getName()+") hand is busted. He is out of the game.");
            System.out.println("Loosing bets are : "+ currentBet);
            scoringSystem.logLost(player, currentBet);
            player.setInGame(false);
        } else {
            while (player.isInGame() && !player.isPlayerStanding()){
                BlackJackActions newAction = computePlayerAction();
                processAction(newAction, player);
            }
        }
    }

    private void processStand(BlackJackPlayer player) {
        player.setPlayerStanding(true);
        System.out.println("Player ("+player.getName()+") Stands, He is ready to play his hand.");
    }

    public BlackJackActions computePlayerAction() {
        System.out.println("Please choose an number from the list below :");
        System.out.println("   1 - (Stand)");
        System.out.println("   2 - (Hit)");
        System.out.println("   3 - (Split) (Not supported yet)");
        System.out.println("   4 - (Double)");
        System.out.println("   5 - (Insurance) (Not supported yet)");
        System.out.println("   6 - (Surrender)");
        System.out.println("   7 - (Display the Hand/Bet)");
        System.out.print("What is your choice ? ");
        int action = sc.nextInt();
        while (action == 0 || action > 7){
            System.out.print("You must choose between 1 and 7, please do it again : ");
            action = sc.nextInt();
        }
        System.out.println();
        return BlackJackActions.getActionbyValue(action);
    }

    public void computeWinningPlayers(List<BlackJackPlayer> gamePlayers) {
        List<BlackJackPlayer> winningPlayers = new ArrayList<>();
        BlackJackHand dealerHand = (BlackJackHand)dealer.getHand();
        if (dealerHand.isBusted()){
            System.out.println("Dealer's hand is busted ! All the players still in the game have won !");
            System.out.println();
            winningPlayers = gamePlayers;
        } else {
            int winningScore = dealerHand.computeHandScore();
            for (BlackJackPlayer player : gamePlayers){
                if (player.getHand().computeHandScore() > winningScore){
                    winningPlayers.add(player);
                } else if (player.getHand().computeHandScore() == winningScore){
                    System.out.print("Player (" + player.getName() + ") has tied with the hand : ");
                    player.getHand().printHand();
                    System.out.println("He gets back his bet : "+player.getCurrentBet()+"$ !");
                    player.setInGame(false);
                } else {
                    System.out.print("Player (" + player.getName() + ") has lost with the hand : ");
                    player.getHand().printHand();
                    System.out.println("He lost "+player.getCurrentBet()+"$ !");
                    player.setInGame(false);
                }
            }
        }

        for (BlackJackPlayer player : winningPlayers){
            System.out.print("Player (" + player.getName() + ") has won with the hand : ");
            player.getHand().printHand();
            System.out.println("He win "+player.getCurrentBet()+"$ !");
            scoringSystem.logGain(player, player.getCurrentBet());
        }
    }

}
