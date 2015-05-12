import java.util.Arrays;

/**
 * Created by mphannon on 5/9/15.
 */

//This should be under a gamemode class or interface
public class BlackJack {
    private Deck deck;
	public BlackJack(Deck deck) {
		this.deck = deck;
		//Card[] shuffled = deck.shuffle();
		//System.out.println(Arrays.toString(deck.deckOfCards));
	}
}
