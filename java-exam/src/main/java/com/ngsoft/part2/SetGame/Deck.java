package com.ngsoft.part2.SetGame;


import java.util.List;

public interface Deck {

	void reset();

	void shuffle();

	List<Card> deal(int qty);

	int size();

	List<Card> getDeck();
}
