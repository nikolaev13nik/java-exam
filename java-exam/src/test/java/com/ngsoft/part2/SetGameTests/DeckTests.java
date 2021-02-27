package com.ngsoft.part2.SetGameTests;

import com.ngsoft.part2.SetGame.Card;
import com.ngsoft.part2.SetGame.DealConstants;
import com.ngsoft.part2.SetGame.Deck;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:spring-config.xml")
public class DeckTests {

	private static final int STAR_STATE_DECK = 81;

	@Autowired
	private Deck deck;

	@Before
	public void setUpDeck() {
		deck.reset();
	}

	@Test
	public void A_DECK_HAS_81_CARDS() {
		assertEquals(STAR_STATE_DECK, deck.size());
	}

	@Test
	public void ALL_CARDS_ARE_DIFFERENT_IN_DECK() {
		int expectedSize = deck.getDeck().size();
		Set<Card> set = new HashSet<Card>(deck.getDeck());
		int actualSize = set.size();
		assertEquals(actualSize, expectedSize);

	}

	@Test
	public void DEAL_SELECT_RETURNS_REQUIRED_AMOUT_OF_CARDS_FROM_DECK() {
		assertEquals(12, deck.deal(12).size());
		int i = 23;
		while (i > 0) {
			assertEquals(3, deck.deal(3).size());
			i--;
		}
		assertEquals(0, deck.size());
		assertEquals(0, deck.deal(3).size());
	}

	@Test(expected = IllegalArgumentException.class)
	public void WHEN_QUONTITY_DEAL_NOT_VALID() {
		deck.deal(1);
	}

	@Test
	public void DEAL_REMOVES_DEALT_CARD_FROM_DECK() {
		int expectedSize = deck.size();

		deck.deal(DealConstants.NEXT_DEAL);
		expectedSize -= DealConstants.NEXT_DEAL;
		assertEquals(expectedSize, deck.size());

		deck.deal(DealConstants.START_DEAL);
		expectedSize -= DealConstants.START_DEAL;
		assertEquals(expectedSize, deck.size());

	}

	@Test
	public void SHUFFLING_WORKS() {
		List<Card> listFromDeck = deck.getDeck();
		List<Card> clonedListForShuffle = listFromDeck.stream().map((c) -> c.clone()).collect(Collectors.toList());

		assertEquals(listFromDeck, clonedListForShuffle);
		Collections.shuffle(deck.getDeck());
		assertNotEquals(listFromDeck, clonedListForShuffle);
		assertEquals(listFromDeck.size(), clonedListForShuffle.size());

	}

}
