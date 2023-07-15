import java.util.*;

import enums.BlackjackRules;
import enums.CardValue;

/**
 * @author Jamal Kamareddine
 * 
 * Contains general data and methods for a deck of cards
 */
public class Deck {
    private List<Card> cardDeck;
    
    /**
     * Constructor for creating the deck
     * 
     * @param numDecks The number of decks to use for the game
     */
    public Deck(int numDecks) {
        cardDeck = new ArrayList<>();
        createDeck(numDecks);
    }

    /**
     * Creates the deck by adding cards to it and shuffling the deck
     * 
     * @param numDecks The number of decks to use for the game
     */
    public void createDeck(int numDecks) {
        for (int i = 0; i < numDecks; i++) {
            for (CardValue cardValue : CardValue.values()) {
                for (int j = 0; j < 4; j++) {
                    Card card = new Card(cardValue.getRank(), cardValue.getValue(true));
                    cardDeck.add(card);
                }
            }
        }
        
        shufflDeck();
    }

    /**
     * Shuffles the deck of cards
     */
    public void shufflDeck() {
        Collections.shuffle(cardDeck);
    }

    /**
     * @return A list of the cards in the deck
     */
    public List<Card> getCards() {
        return cardDeck;
    }

    /**
     * Deals a card from the deck. Creates a new deck of it's empty
     * 
     * @return The top card from the deck
     */
    public Card dealCard() {
        if (cardDeck.isEmpty()) {
            createDeck(BlackjackRules.NUM_MAX_DECKS);
        }

        return cardDeck.remove(0);
    }
}
