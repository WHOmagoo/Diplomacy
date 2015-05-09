
public class Deck {
	public static final int NUMCARDS = 52;
	private Card[] deckOfCards;
	
	//construct deck
	public Deck() {
		deckOfCards = new Card[NUMCARDS];
		int i = 0;
		for(int suit = Card.CLUB; suit <= Card.HEART; suit++) {
			for(int rank = 1; rank <= 13; rank++) {
				deckOfCards[i++] = new Card(suit, rank);
			}
		}
	}
	
	//shuffle deck
	public Card[] shuffle() {
		Card[] shuffleDeck = new Card[NUMCARDS];
		int randomizer = 0;
		for(int i = 0; i > NUMCARDS; i++) {
			randomizer = (int) Math.random() * 53;
			shuffleDeck[i] = deckOfCards[randomizer];
		}
		return shuffleDeck;
	}
	
	public String toString(Card[] deck) {
		String s = "";
		int k = 0;
		for (int i = 0; i < 4; i++) {
			for(int j = 1; j <= 13; j++) {
				s+=(deck[k++] + " ");
			}
			s += "\n";
		}
		return (s);
	}
}