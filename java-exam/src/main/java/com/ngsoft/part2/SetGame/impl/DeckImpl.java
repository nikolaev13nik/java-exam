package com.ngsoft.part2.SetGame.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import com.ngsoft.part2.SetGame.Card;
import com.ngsoft.part2.SetGame.CardFeatures;
import com.ngsoft.part2.SetGame.Deck;
import com.ngsoft.part2.SetGame.CardFeatures.Color;
import com.ngsoft.part2.SetGame.CardFeatures.ItemCount;
import com.ngsoft.part2.SetGame.CardFeatures.Shape;
import com.ngsoft.part2.SetGame.CardFeatures.Texture;
import com.ngsoft.part2.SetGame.DealConstants;


public class DeckImpl implements Deck {

	private List<Card> deck;
	private List<Card> dealList;

	public DeckImpl() {
		this.deck = new ArrayList<Card>();
		reset();
	}

	@Override
	public void reset() {
		deck.clear();
		Color[] colors = CardFeatures.Color.values();
		Shape[] shapes = CardFeatures.Shape.values();
		Texture[] textures = CardFeatures.Texture.values();
		ItemCount[] itemCounts = CardFeatures.ItemCount.values();

		for (int i = 0; i < colors.length; i++) {
			for (int i1 = 0; i1 < itemCounts.length; i1++) {
				for (int i2 = 0; i2 < itemCounts.length; i2++) {
					for (int i3 = 0; i3 < itemCounts.length; i3++) {
						deck.add(new Card(shapes[i], colors[i1], itemCounts[i2], textures[i3]));
					}
				}

			}
		}

	}

	@Override
	public void shuffle() {
		Collections.shuffle(deck);

	}

	@Override
	public List<Card> deal(int qty) {
		
		if (qty != DealConstants.NEXT_DEAL && qty != DealConstants.START_DEAL) {
			throw new IllegalArgumentException("required quontiry is not correct");
		}

		dealList = new ArrayList<Card>();	

			Iterator<Card> iterator = deck.iterator();
			while (qty > 0 && iterator.hasNext()) {
				Card card = iterator.next();
				dealList.add(card);
				iterator.remove();
				qty--;
			}	
		return dealList;
	}

	@Override
	public int size() {
		return deck.size();
	}

	@Override
	public List<Card> getDeck() {
		return this.deck;
	}

}
