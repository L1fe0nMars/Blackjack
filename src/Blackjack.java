/**
 * @author Jamal Kamareddine
 * 
 * This program plays the game of Blackjack
 */
public class Blackjack {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        Blackjackgame game = new Blackjackgame();
        
        game.playBlackjack();
        game.endGame();
    }
}
