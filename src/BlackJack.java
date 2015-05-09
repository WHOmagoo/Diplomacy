import java.util.Arrays;

/**
 * Created by mphannon on 5/9/15.
 */
public class BlackJack {
	public static void main(String[] args) {
		Deck d = new Deck();
		Card[] shuffled = d.shuffle();
		System.out.println(Arrays.toString(d.deckOfCards));
	}
}
