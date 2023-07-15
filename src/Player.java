import enums.BlackjackRules;

/**
 * @author Jamal Kamareddine
 * 
 * Contains general data and methods for the player
 */
public class Player {
    private final Hand hand;
    private int money = 100;
    private int betAmount = 0;

    /**
     * Constructor for creating the player
     */
    public Player() {
        hand = new Hand();
    }

    /**
     * @return The amount of money
     */
    public int getMoney() {
        return money;
    }

    /**
     * Sets the player's money
     * 
     * @param amount The amount of money to add
     */
    public void setMoney(int money) {
        this.money = money;
    }

    /**
     * Adds to the player's money
     * 
     * @param amount The amount of money to add
     */
    public void payout(int amount) {
        money += amount;

        if (money < 0) {
            money = 0;
        }
    }

    /**
     * @return The amount of money the player bets
     */
    public int getBetAmount() {
        return betAmount;
    }

    /**
     * Sets the bet amount
     * 
     * @param amount The amount of money the player bets
     */
    public void setBetAmount(int amount) {
        betAmount = amount;
    }

    /**
     * Resets the bet amount
     */
    public void resetBetAmount() {
        betAmount = 0;
    }

    /**
     * @return The player's hand
     */
    public Hand getHand() {
        return hand;
    }
    
    /**
     * @return Check whether the player busts or not
     */
    public boolean bust() {
        return hand.getScore() > BlackjackRules.BLACKJACK_VALUE;
    }

    /**
     * @return Check if the hand is equal to the blackjack value
     */
    public boolean hasBlackjack() {
        return hand.getScore() == BlackjackRules.BLACKJACK_VALUE;
    }

    /**
     * Adds a card to the player's hand when they hit
     * 
     * @param deck The deck object to pull a card from
     */
    public void hit(Deck deck) {
        hand.addCard(deck.dealCard());
    }
}
