import java.util.*;

import enums.BlackjackRules;
import enums.CardValue;

/**
 * @author Jamal Kamareddine
 * 
 * Contains general data and methods for a hand of cards
 */
public class Hand {
    private final List<Card> cards;
    
    /**
     * Constructor for creating the hand
     */
    public Hand() {
        cards = new ArrayList<>();
    }

    /**
     * @return The cards in the hand
     */
    public List<Card> getCards() {
        return cards;
    }

    /**
     * Adds a card to the hand
     * 
     * @param card The card to add
     */
    public void addCard(Card card) {
        cards.add(card);
    }

    /**
     * Clears all cards from the hand
     */
    public void clearHand() {
        cards.clear();
    }

    /**
     * Gets the score of the current hand
     * 
     * @return The score
     */
    public int getScore() {
        List<Card> aces = new ArrayList<>();
        int score = 0;

        for (Card card : cards) {
            if (card.getrank() == CardValue.ACE.getRank()) {
                aces.add(card);
            }

            if (!card.isHidden()) {
                score += card.getValue();
            }
        }
        
        // Reduce the value of aces to 1 if the total score is over 21
        for (Card ace : aces) {
            if (score > BlackjackRules.BLACKJACK_VALUE && ace.getValue() == CardValue.ACE.getValue(true)) {
                ace.setValue(CardValue.ACE.getValue(false));
                score -= 10;
            }
        }

        return score;
    }

    /**
     * @return The hand information
     */
    public String toString() {
        String str = "";

        for (Card card : cards) {
            str += card.isHidden() ? "(Hidden) ": card.getrank() + " ";
        }

        str += "Total: " + getScore();
        
        return str;
    }
}
