import java.util.Scanner;

import enums.BlackjackRules;

/**
 * @author Jamal Kamareddine
 * 
 * The main blackjack game loop
 */
public class Blackjackgame {
    private Player player;
    private Dealer dealer;
    private Deck deck;
    private Scanner scanner;

    /**
     * Constructor for creating the necessary elements of the game
     */
    public Blackjackgame() {
        player = new Player();
        dealer = new Dealer();
        deck = new Deck(BlackjackRules.NUM_MAX_DECKS);
        scanner = new Scanner(System.in);
    }

    /**
     * The main game loop
     */
    public void playBlackjack() {
        System.out.println(
                            "*********************" + "\n" +
                            "Welcome to Blackjack!" + "\n" +
                            "*********************"
                        );
        System.out.println("Would you like to go over the rules first? They can be accessed again at any time." + "\n" + "Y - Yes N - No");
        String userInput = scanner.nextLine().toLowerCase();

        if (userInput.equalsIgnoreCase("y")) {
            BlackjackRules.showRules();
        }

        userInput = "";

        System.out.println("You are starting out with $100.");

        do {
            Hand playerHand = player.getHand();
            Hand dealerHand = dealer.getHand();
            
            playerHand.clearHand();
            dealerHand.clearHand();
            player.resetBetAmount();
            
            placeBet();
            dealInitialCards(playerHand, false);
            dealInitialCards(dealerHand, true);
            
            playerTurn();

            if (playerHand.getScore() <= BlackjackRules.BLACKJACK_VALUE) {
                dealerTurn();
            }

            determinePayout();

            System.out.println("You now have $" + player.getMoney());

            if (player.getMoney() < BlackjackRules.MIN_BET_AMOUNT) {
                System.out.println("Looks like you don't have enough money to keep playing. Better luck next time!");
            }
            else {
                System.out.println("Would you like to play again? Type Q to quit, otherwise press ENTER.");
                userInput = scanner.nextLine();
            }

        } while (!userInput.equalsIgnoreCase("q") && player.getMoney() > 4);
    }

    /**
     * Places the user's bet before each game
     */
    public void placeBet() {
        int betAmount = 0;

        while (betAmount < BlackjackRules.MIN_BET_AMOUNT || betAmount > player.getMoney()) {
            System.out.println("How much would you like to bet? (Enter a whole number)");
            betAmount = scanner.nextInt();
            scanner.nextLine();
        
            if (betAmount < BlackjackRules.MIN_BET_AMOUNT) {
                System.out.println("The minimum bet is $5.");
            }
            else if (betAmount > player.getMoney()) {
                System.out.println("You cannot bet more money than you have.");
            }
        }

        player.setBetAmount(betAmount);
    }

    /**
     * Deals 2 cards at the start of each game
     * 
     * @param hand The hand the cards are added to
     * @param isDealer Determines if the hand belongs to the dealer so that the 2nd card dealt becomes hidden
     */
    public void dealInitialCards(Hand hand, boolean isDealer) {
        for (int i = 0; i < 2; i++) {
            Card card = deck.dealCard();
            hand.addCard(card);

            if (isDealer && i == 1) {
                card.setHidden(true);
            }
        }
    }

    /**
     * Displays the game 
     */
    public void displayGame() {
        Hand playerHand = player.getHand();
        Hand dealerHand = dealer.getHand();
        
        System.out.println(
                            "\n" +
                            "Dealer's Hand: " + dealerHand + "\n" +
                            "----------------------------------------" + "\n" +
                            "Your Hand:     " + playerHand + "\n" +
                            "0 - Hit 1 - Stand 2 - Rules"
                        );
    }

    /**
     * Lets the user play the game during their turn
     */
    public void playerTurn() {
        int menuOption;
        Scanner option = new Scanner(System.in);

        displayGame();

        if (player.getHand().getScore() < BlackjackRules.BLACKJACK_VALUE) {
            do { 
                menuOption = option.nextInt();

                displayGame();
                
                switch (menuOption) {
                    case 0:
                        player.hit(deck);
                        System.out.println("You hit.");
                        displayGame();
                        break;
                    case 1:
                        System.out.println("You stand.");
                        break;
                    case 2:
                        BlackjackRules.showRules();
                        displayGame();
                        break;
                    default:
                        System.out.println("You entered an invalid option. Please try again.");
                        displayGame();
                }
            } while (menuOption != 1 && player.getHand().getScore() < BlackjackRules.BLACKJACK_VALUE);
        }
        
        if (player.bust()) {
            System.out.println("Bust! You lose!");
        }
        else if (player.hasBlackjack()) {
            System.out.println("You got 21!");
        }
    }

    /**
     * Simulates the dealer
     */
    public void dealerTurn() {
        int dealerSelection;

        for (Card card : dealer.getHand().getCards()) {
            if (card.isHidden()) {
                card.setHidden(false);
            }
        }

        System.out.println("Now it's the dealer's turn");

        displayGame();
        
        if (dealer.getHand().getScore() < BlackjackRules.BLACKJACK_VALUE) {
            do { 
                dealerSelection = dealerLogic();
                
                switch (dealerSelection) {
                    case 0:
                        dealer.hit(deck);
                        System.out.println("The dealer hits.");
                        displayGame();
                        break;
                    case 1:
                        System.out.println("The dealer stands.");
                        break;
                    default:
                        System.out.println("This should never display.");
                }
            } while (dealerSelection != 1 && dealer.getHand().getScore() < BlackjackRules.BLACKJACK_VALUE);
        }
        
        if (dealer.bust()) {
            System.out.println("The dealer busts! You win!");
        }
        else if (dealer.getHand().getScore() == player.getHand().getScore()) {
            System.out.println("It's a tie. You don't earn or lose any money.");
        }
        else if (dealer.getHand().getScore() < player.getHand().getScore()) {
            System.out.println("You win!");
        }
        else if (dealer.getHand().getScore() > player.getHand().getScore()) {
            System.out.println("You lose.");
        }
    }

    /**
     * Determines whether the dealer hits or stands
     * 
     * @return The dealer's selection
     */
    public int dealerLogic() {
        int dealerSelection = 1;
        int playerScore = player.getHand().getScore();
        int dealerScore = dealer.getHand().getScore();

        if ((player.hasBlackjack() && dealerScore < BlackjackRules.BLACKJACK_VALUE)
        || (dealerScore < BlackjackRules.DEALER_MIN_SCORE && dealerScore < playerScore))
        {
            dealerSelection = 0;
        }

        return dealerSelection;
    }

    /**
     * Updates the user's money based on the game result
     */
    public void determinePayout() {
        int playerScore = player.getHand().getScore();
        int dealerScore = dealer.getHand().getScore();
        int betAmount = player.getBetAmount();

        if (player.hasBlackjack() && (dealerScore < playerScore || dealer.bust())) {
            player.payout(2 * betAmount);
        }
        else if (playerScore < BlackjackRules.BLACKJACK_VALUE && (dealerScore < playerScore || dealer.bust())) {
            player.payout(betAmount);
        }
        else if (playerScore != dealerScore) {
            player.payout(-betAmount);
        }
    }

    /**
     * Ends the game
     */
    public void endGame() {
        System.out.println("You leave with $" + player.getMoney());
        System.out.println("*****************************");
        System.out.println("Thanks for playing Blackjack!");
        System.out.println("*****************************");
    }
}
