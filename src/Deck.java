import java.util.ArrayList;
public class Deck {
	public static final int NUMCARDS = 52;
	private ArrayList<Card> deckOfCards;
    private ArrayList<Card> shuffledDeck;
	//TODO make a deck of cards into a HashMap that gets assigned random keys and then gets sorted into numerical order NO

	//construct deck
	public Deck() {
		deckOfCards = new ArrayList<Card>();
		int i = 0;
        String suitName = "";
		for(int suit = 1; suit <= 4; suit++) {
			for(int rank = 1; rank <= 13; rank++) {
				deckOfCards.add(new Card(suit, rank));
			}
		}
	}

    public ArrayList<Card> getDeckOfCards() {
        return deckOfCards;
    }

    //shuffle deck
	public ArrayList<Card> shuffle() {
        ArrayList<Card> workingDeck = deckOfCards;
		shuffledDeck = new ArrayList<Card>();
		int random;
        int deckSize = workingDeck.size();
        for(int i = 0; i < deckSize; i++) {
			random = (int) (Math.random() * (52 - i));
			shuffledDeck.add(workingDeck.get(random));
            workingDeck.remove(random);
		}
        return shuffledDeck;
	}
}