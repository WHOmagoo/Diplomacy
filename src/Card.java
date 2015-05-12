public class Card {
	public static final int CLUB = 1;
	public final int SPADE = 2;
	public final int DIAMOND = 3;
	public static final int HEART = 4;
	private int suit;
	private int rank;
    private static String[] suits = {"hearts", "spades", "diamonds", "clubs"};
    private static String[] ranks  = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};

	public Card(int suit, int rank) {
		this.suit = suit;
		this.rank = rank;
	}

    @Override
    public String toString() {
        return ranks[rank - 1] + " of " + suits[suit - 1];
    }
}