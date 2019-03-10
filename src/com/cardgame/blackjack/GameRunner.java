package com.cardgame.blackjack;

import com.cardgame.blackjack.engine.GameFactory;
import com.cardgame.blackjack.interfaces.ICardGameEngine;

import java.util.Scanner;

public class GameRunner {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("******Welcome to the Game Factory !******");
        System.out.println("Please select the game you want to play :");
        System.out.println("   BlackJack ..");
        System.out.println("   Poker ..");
        System.out.print("What is your choice ? ");
        String answer = sc.next();
        while (!"BlackJack".equals(answer)){
            if ("Poker".equals(answer)){
                System.out.println("Sorry Poker is not supported yet ! Please make another choice..");
            } else {
                System.out.println("Your choice is not valid ! Please make a valid choice");
            }
            System.out.print("What is your choice ? ");
            answer = sc.next();
        }

        GameFactory gameFactory = new GameFactory();
        ICardGameEngine gameEngine = gameFactory.getCardGameEngine(answer);
        gameEngine.playGame();
    }
}
