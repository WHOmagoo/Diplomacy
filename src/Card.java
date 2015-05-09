public class Card {
	public static final int CLUB = 1;
	public final int SPADE = 2;
	public final int DIAMOND = 3;
	public static final int HEART = 4;
	private int suit;
	private int rank;
	
	public Card(int suit, int rank) {
		this.suit = suit;
		this.rank = rank;
	}
}