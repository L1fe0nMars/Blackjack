package enums;

/**
 * @author Jamal Kamareddine
 * 
 * Constants and functions for the values of each card
 */
public enum CardValue {
    ACE("A", 1, 11),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    JACK("J", 10),
    QUEEN("Q", 10),
    KING("K", 10);

    private String rank;
    private final int[] values;

    /**
     * Constructor to set each parameter
     * 
     * @param rank The rank of the card
     * @param values The possible values of the card
     */
    private CardValue(String rank, int... values) {
        this.rank = rank;
        this.values = values;
    }

    /**
     * @return The rank of the card
     */
    public String getRank() {
        return rank;
    }

    /**
     * Gets the value of a card and checks whether the high value should be used in the case for aces
     * 
     * @param useHighValue Boolean that determines which card value to use if there are multiple
     * 
     * @return The value of the card
     */
    public int getValue(boolean useHighValue) {
        return useHighValue && values.length > 1 ? values[1]: values[0];
    }
}
