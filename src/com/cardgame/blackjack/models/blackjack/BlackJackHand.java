package com.cardgame.blackjack.models.blackjack;

import com.cardgame.blackjack.models.generic.Hand;

import java.util.ArrayList;
import java.util.List;

public class BlackJackHand extends Hand<BlackJackCard> {

    public BlackJackHand(List<BlackJackCard> handCards) {
        super(handCards);
    }

    @Override
    public int computeHandScore() {
        List<Integer> possibleScores = computePossibleScores();
        int maxUnderLimit = Integer.MIN_VALUE;
        int minOverLimit = Integer.MAX_VALUE;
        for (int score : possibleScores){
            if (score < minOverLimit && minOverLimit > 21){
                minOverLimit = score;
            } else if (score > maxUnderLimit && score <= 21){
                maxUnderLimit = score;
            }
        }
        return maxUnderLimit == Integer.MIN_VALUE ? minOverLimit : maxUnderLimit;
    }

    private List<Integer> computePossibleScores() {
        List<Integer> possibleScores = new ArrayList<>();
        for (BlackJackCard card : handCards){
            if (card instanceof BlackJackAceCard){
                if (possibleScores.isEmpty()){
                    possibleScores.add(1);
                    possibleScores.add(11);
                } else {
                    List<Integer> tempScores = new ArrayList<>();
                    for (Integer existingScore : possibleScores) {
                        tempScores.add(existingScore + 1);
                        tempScores.add(existingScore + 11);
                    }
                    possibleScores = tempScores;
                }
            } else if (card instanceof BlackJackFaceCard){
                if (possibleScores.isEmpty()){
                    possibleScores.add(10);
                } else {
                    List<Integer> tempScores = new ArrayList<>();
                    for (Integer existingScore : possibleScores) {
                        tempScores.add(existingScore + 10);
                    }
                    possibleScores = tempScores;
                }
            } else {
                if (possibleScores.isEmpty()){
                    possibleScores.add(card.value());
                } else {
                    List<Integer> tempScores = new ArrayList<>();
                    for (Integer existingScore : possibleScores) {
                        tempScores.add(existingScore + card.value());
                    }
                    possibleScores = tempScores;
                }
            }
        }
        return possibleScores;
    }

    /**
     * If the score is more than 21 than the hand is busted
     * @return
     */
    public boolean isBusted(){
        return computeHandScore() > 21;
    }

    /**
     * A natural blackjack is the total of the hand is 21
     * @return
     */
    public boolean isNaturalBlackJack(){
        return computeHandScore() == 21;
    }

    /**
     * if we have a total of 21 and the hand is exactly 1 ace and 1 face card
     * @return
     */
    public boolean isBlackJack(){
        if ((handCards.get(0) instanceof BlackJackAceCard && handCards.get(0) instanceof BlackJackFaceCard) ||
                (handCards.get(0) instanceof BlackJackFaceCard && handCards.get(0) instanceof BlackJackAceCard)){
            return true;
        }

        return false;
    }
}
