//7.1 Deck of Cards: Design the data structures for a generic deck of cards. 
//Explain how you would subclass the data structures to implement blackjack

/*
key learnings: 
1. make variables as private and then use appropriate public methods for outside to access
2. Basic class and then extends as needed
3. enum as a good way to safe constants
*/

public enum Suit {
	Club(0), Diamond(1), Heart(2), Spade(3);
	private int value;
	private Suit(int v) {value = v};
	private int getValue() {return value;}
	public static Suit getSuitFromValue(int value) {...};
}

public class Deck <T extends Card> {
	private ArrayList<T> cards;
	private int dealtIndex = 0;
	public void setDeckOfCards(ArrayList<T> deckOfCards) {...}
	public void shuffle() {...}
	public int remainingCards() {
		return cards.size() - dealtIndex;
	}
	public T[] dealHand(int number) {...}
	public T dealCard() {...}
}

public abstract class Card {
	private boolean available = true;
	protected int faceValue;
	protected Suit suit;
	public Card(int c, Suit s) {
		faceValue = c;
		suit = s;
	}

	public abstract int value();
	public Suit suit() {return suit;}
	public boolean IsAvailable() {return available;}
	public void markAvailable() { available = true;}
	public void markUnAvailable() { available = false;}
}

public class Hand<T extends Card> {
	protected ArrayList<T> cards = new ArrayList<T>();
	public int score() {
		int score = 0;
		for (T card : cards) {
			score += card.value();
		}
		return score;
	}

	public void addCard(T card) {
		cards.add(card);
	}
}

public class BlackJackHand extends Hand<BlackJackCard> {
	public int score() {
		ArrayList<Integer> scores = possibleScores();
		int maxUnder = Integer.MIN_VALUE;
		int minOver = Integer.MAX_VALUE;
		for (int score : scores) {
			if (score > 21 && score < minOver) {
				minOver = score;
			}
			if (score <= 21 && score > maxUnder) {
				maxUnder = score;
			}
			if (maxUnder != Integer.MIN_VALUE) return maxUnder;
			return minOver;
		}
	}

	private ArrayList<Integer> possibleScores() {.../* A could be 11 or 1*/}
	public boolean busted() {return score() > 21;}
	public boolean is21() {return score() == 21;}
	public boolean isBlackJack() {...}
}


public class BlackJackCard extends Card {
	public BlackJackCard(int c, Suit s) {super(c, s);}
	public int value() {
		if (isAce()) return 1;
		else if (faceValue >= 11 && faceValue <= 13) return 10;
		else return faceValue;
	}
	public int minValue() {
		if (isAce()) return 1;
		else return value();
	}

	public int maxValue() {
		if (isAce()) return 11;
		else return value();
	}

	public boolean isAce() {
		return faceValue == 1;
	}

	public boolean isFaceCard() {
		return faceValue >= 11 && faceValue <= 13;
	}
}


