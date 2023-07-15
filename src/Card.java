/**
 * @author Jamal Kamareddine
 * 
 * Contains general data about a card
 */
public class Card {
    private final String rank;
    private int value;
    private boolean hidden = false;

    /**
     * Constructor to set each parameter
     * 
     * @param rank The rank of the card
     * @param value The value of the card
     */
    public Card(String rank, int value) {
        this.rank = rank;
        this.value = value;
    }

    /**
     * @return The rank of the card
     */
    public String getrank() {
        return rank;
    }

    /**
     * @return The value of the card
     */
    public int getValue() {
        return value;
    }

    /**
     * Sets the card value
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Checks if the card is hidden
     * 
     * @param card The card to check
     * 
     * @return Boolean of whether the card is hidden or not
     */
    public void setHidden(boolean hideCard) {
        hidden = hideCard;
    }

    /**
     * Checks if the card is hidden
     * 
     * @return Boolean of whether the card is hidden or not
     */
    public boolean isHidden() {
        return hidden;
    }

    /**
     * @return The card information
     */
    public String toString() {
        String str = "Card Rank: " + rank + " Card Value: " + value;
        
        return str;
    }
}
