package blackjack;

import com.cardgame.blackjack.engine.GameEngineFactory;
import com.cardgame.blackjack.engine.ScoringSystemFactory;
import com.cardgame.blackjack.interfaces.ICardGameEngine;
import com.cardgame.blackjack.interfaces.IScoringSystem;
import com.cardgame.blackjack.utils.ScannerUtil;

import java.util.Scanner;

/**
 * Runner class for the all the games in the program
 */

public class GameRunner {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ScannerUtil scannerUtil = new ScannerUtil(sc);

        System.out.println("******Welcome to the Game Factory !******");
        System.out.println("Please select the game you want to play :");
        System.out.println("   1 - BlackJack");
        System.out.println("   2 - Poker");
        System.out.print("What is your choice ? ");
        int answer = scannerUtil.nextInt();
        while (answer != 1){
            if (answer == 2){
                System.out.println("Sorry Poker is not supported yet ! Please make another choice..");
            } else {
                System.out.println("Your choice is not valid ! Please make a valid choice");
            }
            System.out.print("What is your choice ? ");
            answer = sc.nextInt();
        }

        // Get the Game implementation from the Factory
        GameEngineFactory gameEngineFactory = new GameEngineFactory();
        ICardGameEngine gameEngine = gameEngineFactory.getCardGameEngine(answer);

        // Get the Scoring Systel implementation from the Factory
        ScoringSystemFactory scoringSystemFactory = new ScoringSystemFactory();
        IScoringSystem scoringSystem = scoringSystemFactory.getScoringSystem(answer);

        // Set the scanner utility and the scoring system to the game engine
        gameEngine.setPrinterUtil(scannerUtil);
        gameEngine.setScoringSystem(scoringSystem);

        // Play the game
        gameEngine.playGame();
    }
}
