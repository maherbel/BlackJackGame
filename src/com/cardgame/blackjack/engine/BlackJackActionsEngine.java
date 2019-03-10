package com.cardgame.blackjack.engine;

import com.cardgame.blackjack.models.blackjack.*;
import com.cardgame.blackjack.models.generic.Deck;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BlackJackActionsEngine {

    private final Scanner sc;
    private final Deck<BlackJackCard> gameDeck;
    private final BlackJackDealer dealer;

    public BlackJackActionsEngine(Scanner sc, Deck<BlackJackCard> gameDeck, BlackJackDealer dealer) {
        this.sc = sc;
        this.gameDeck = gameDeck;
        this.dealer = dealer;
    }
    // TODO ADD SCORE MANAGMENT ON EACH ACTION !!!!
    public void processAction(BlackJackActions action, BlackJackPlayer player) {
        BlackJackHand playerHand = player.getHand();
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
                // TODO
            case Split:
                // TODO
        }
    }

    private void processDouble(BlackJackPlayer player, BlackJackHand playerHand, Integer currentBet) {
        player.setCurrentBet(currentBet *2);
        BlackJackCard doubleCard = gameDeck.dealCard();
        playerHand.addNewCard(doubleCard);
        System.out.println("Player ("+player.getName()+") hand is now : ");
        playerHand.printHand();
        if (playerHand.isBusted()){
            System.out.println("Player ("+player.getName()+") hand is busted. He is out of the game");
            System.out.println("Loosing bets are : "+ player.getCurrentBet());
            player.setInGame(false);
        } else {
            System.out.println("Player ("+player.getName()+") cannot receive any more cards.");
            System.out.println("His best have been doubled "+ player.getCurrentBet());
            player.setPlayerStanding(true);
        }
    }

    private void processSurrender(BlackJackPlayer player, int currBet) {
        System.out.println("Player (" + player.getName() + ") has surrendered. He is out of the game");
        System.out.println("Loosing bets are : " + currBet);
        player.setInGame(false);
    }

    private void processHit(BlackJackPlayer player, BlackJackHand playerHand, Integer currentBet) {
        BlackJackCard newCard = gameDeck.dealCard();
        playerHand.addNewCard(newCard);
        System.out.println("Player ("+player.getName()+") hand is now : ");
        playerHand.printHand();
        if (playerHand.isBusted()){
            System.out.println("Player ("+player.getName()+") hand is busted. He is out of the game");
            System.out.println("Loosing bets are : "+ currentBet);
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
        System.out.println("Player ("+player.getName()+") hand is now : ");
        player.getHand().printHand();
        System.out.println("Player ("+player.getName()+") is ready to play his hand.");
    }

    public BlackJackActions computePlayerAction() {
        System.out.println("Please choose an number from the list below :");
        System.out.println("1 (Stand)");
        System.out.println("2 (Hit)");
        System.out.println("3 (Split)");
        System.out.println("4 (Double)");
        System.out.println("5 (Insurance)");
        System.out.println("6 (Surrender)");
        System.out.print("What is your choice ? ");
        int action = sc.nextInt();
        while (action == 0 || action > 6){
            System.out.print("You must choose between 1 and 6, please do it again : ");
            action = sc.nextInt();
        }
        System.out.println();
        return BlackJackActions.getActionbyValue(action);
    }

    public void computeWinningPlayers(List<BlackJackPlayer> gamePlayers) {
        List<BlackJackPlayer> winningPlayers = new ArrayList<>();
        if (dealer.getHand().isBusted()){
            winningPlayers = gamePlayers;
        } else {
            int winningScore = dealer.getHand().computeHandScore();
            for (BlackJackPlayer player : gamePlayers){
                if (player.getHand().computeHandScore() > winningScore){
                    winningPlayers.add(player);
                } else if (player.getHand().computeHandScore() == winningScore){
                    System.out.print("Player (" + player.getName() + ") has tied. He does not loose his bet : ");
                    player.getCurrentBet();
                    player.setInGame(false);
                } else {
                    System.out.print("Player (" + player.getName() + ") has lost. He lost his bet : ");
                    player.getCurrentBet();
                    player.setInGame(false);
                }
            }
        }

        for (BlackJackPlayer player : winningPlayers){
            System.out.println("Player (" + player.getName() + ") has won. He win "+player.getCurrentBet()+"$");
        }
    }

}
