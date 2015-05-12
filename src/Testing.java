import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Michael Hannon on 05/11/15.
 */

/*TODO Make so I don't need two decks to properly display the sorted deck. With one deck it...
TODO ... Clears the sorted deck when it creates the shuffled deck for some reason. 
 */
public class Testing {
    public static void main(String[] args) {
        Deck deck = new Deck();
        Deck deck2 = new Deck();
        ArrayList<Card> sortedDeck = deck.getDeckOfCards();
        ArrayList<Card> shuffledDeck = deck2.shuffle();
        printer(sortedDeck);
    }

    public static void printer(ArrayList<Card> testDeck) {
        for(Card card : testDeck) {
            System.out.println(card);
        }
    }
}