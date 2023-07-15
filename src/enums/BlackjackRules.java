package enums;

import java.util.Scanner;

/**
 * @author Jamal Kamareddine
 * 
 * Constants used for various aspects of the blackjack game
 */
public class BlackjackRules {
    public static final int BLACKJACK_VALUE = 21;
    public static final int DEALER_MIN_SCORE = 17;
    public static final int NUM_MAX_DECKS = 6;
    public static final int NUM_CARDS_IN_DECK = 52;
    public static final int MIN_BET_AMOUNT = 5;

    /**
     * Explains how to play blackjack
     */
    public static void showRules() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println(
                            "The goal of Blackjack is to have your cards total to a value of, or as close as possible, to 21 without going over." + "\n" +
                            "You must first place a bet (minimum $5) and then you and the dealer are each dealt 2 cards." + "\n" +
                            "Your cards are face up but the dealer has 1 card face up and 1 card face down until it's their turn." + "\n" +
                            "Each card with a number counts as that number, while Jacks, Queens, and Kings count as 10. An Ace counts as either 1 or 11." + "\n" +
                            "You can either \"hit\" or \"stand\"." + "\n" +
                            "To \"hit\" means to take another card. You can continue hitting until you either \"stand\", meaning you want to stop taking cards, or bust, meaning you go over 21." + "\n" +
                            "You automatically lose if you bust." + "\n" +
                            "You also lose if your total is less than the dealer's total, if the dealer didn't bust." + "\n" +
                            "You win by having a higher total less than or equal to 21 or if the dealer busts." + "\n" +
                            "Payouts are 2:1 for normal wins and 3:1 if you win with 21." + "\n" +
                            "For example: If you bet $5, then you'll get those $5 back plus another $5 for winning, plus an additional $5 if you win with a total of 21." + "\n" +
                            "If there's a tie, the money you bet is returned to you." + "\n" +
                            "Press ENTER when you are ready to continue."
                        );

        scanner.nextLine();
    }
}
