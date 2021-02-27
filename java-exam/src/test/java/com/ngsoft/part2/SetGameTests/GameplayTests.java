package com.ngsoft.part2.SetGameTests;

import com.ngsoft.part2.SetGame.Card;
import com.ngsoft.part2.SetGame.DealConstants;
import com.ngsoft.part2.SetGame.Deck;
import com.ngsoft.part2.SetGame.GameRules;
import com.ngsoft.part2.SetGame.impl.GamePlayService;
import com.ngsoft.part2.SetGame.impl.PlayResult;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:spring-config.xml")
public class GameplayTests {

	/**
	 * Gameplay should be the main class to interact with the UX. After starting new
	 * deck will be reset and shuffled. 12 cards will be displayed on revealed cards
	 * list Player may select a set of 3 cards by stating their index (0-11) within
	 * the revealed cards list so there should probably be a method called play(int
	 * card1,int card2, int card3) this method will return some sort of response
	 * object, stating whether success or not, what is the score, the next 3 cards
	 * being dealt (that will be added to the revealed cards list), and whether the
	 * game is over) How do I make the gameplay testable?
	 */

	@Autowired
	private GamePlayService gamePlayService;

	@Autowired
	private Deck deck;

	@Autowired
	GameRules gameRules;


	@Before
	public void setup() {
		gamePlayService.start();
	}

	@Test
	public void WHEN_STARTING_12_CARDS_ARE_DEALT_FROM_DECK() {
		gamePlayService.start();
		assertEquals(69, deck.size());
		assertEquals(DealConstants.START_DEAL, gamePlayService.revealedCards().size());
		deck.deal(3);
		assertEquals(DealConstants.START_DEAL, gamePlayService.revealedCards().size());
	}

	@Test
	public void CAN_SELECT_CARDS_AND_VERIFY() {

	}

	@Test
	public void IF_SELECTED_PROPER_SET_SCORE_IS_ENHANCED_BY_1() {
		int scoreBefore = gamePlayService.getScore();

		for (int i = 0; i < 20; i++) {

			PlayResult playResult = gamePlayService.play(0, 3, 6);
			if (playResult.isSet()) {

				assertEquals(++scoreBefore, playResult.getScore().getValueScore());
			} else {
				assertEquals(--scoreBefore, playResult.getScore().getValueScore());
			}
			assertEquals(gamePlayService.revealedCards().size(), 12);
			assertNotEquals(gamePlayService.revealedCards().size(), 11);
		}
	}

	@Test
	public void IF_PROPER_SET_THEN_CARDS_ARE_REMOVED_FROM_REVEALED_LIST() {

		List<Card> clonedRevealedCards = new ArrayList<Card>();
		gamePlayService.revealedCards().stream().forEach((c) -> clonedRevealedCards.add(c.clone()));

		List<Integer> inexesOfSet = getListIndexesForSet(clonedRevealedCards);

		gamePlayService.play(inexesOfSet.get(0), inexesOfSet.get(1), inexesOfSet.get(2));
		List<Card> revealedCardsAfterDeal = gamePlayService.revealedCards();

		assertFalse(revealedCardsAfterDeal.contains(clonedRevealedCards.get(inexesOfSet.get(0))));
		assertFalse(revealedCardsAfterDeal.contains(clonedRevealedCards.get(inexesOfSet.get(1))));
		assertFalse(revealedCardsAfterDeal.contains(clonedRevealedCards.get(inexesOfSet.get(2))));

	}

	private List<Integer> getListIndexesForSet(List<Card> revealedCardsAfterDeal) {
		List<Integer> inexesOfSet = new ArrayList<Integer>();

		for (int i = 0; i < revealedCardsAfterDeal.size(); i++) {
			for (int j = 0; j < revealedCardsAfterDeal.size(); j++) {
				if (revealedCardsAfterDeal.get(i).getColor().equals(revealedCardsAfterDeal.get(j).getColor())) {
					inexesOfSet.add(j);
				}
			}
			if (inexesOfSet.size() >= 3) {
				return inexesOfSet;
			} else {
				inexesOfSet.clear();
			}
		}
		return inexesOfSet;
	}

	@Test
	public void IF_PROPER_SET_THEN_NEW_CARDS_ARE_DEALT_UNLESS_DECK_IS_EMPTY() {

		List<Integer> inexesOfSet;
		PlayResult playResult = null;

		while (gamePlayService.revealedCards().size() > 0) {
			inexesOfSet = getListIndexesForSet(gamePlayService.revealedCards());
			playResult = gamePlayService.play(inexesOfSet.get(0), inexesOfSet.get(1), inexesOfSet.get(2));

		}
		assertEquals(deck.size(), 0);
		assertEquals(gamePlayService.revealedCards().size(), 0);
		assertEquals(playResult.getScore().getValueScore(), 27);

	}

	@Test
	public void FAILED_SET_ATTEMPT_REDUCES_SCORE_BY_1() {

		List<Integer> inexesAreNotSet = getWrongIndexesForSet(gamePlayService.revealedCards());
		for (int expectedScore = -1; expectedScore > -5; expectedScore--) {
			assertEquals(expectedScore,
					gamePlayService.play(inexesAreNotSet.get(0), inexesAreNotSet.get(1), inexesAreNotSet.get(2))
							.getScore().getValueScore());
		}
	}

	private List<Integer> getWrongIndexesForSet(List<Card> revealedCards) {
		List<Integer> indexesAreNotSet = new ArrayList<Integer>();
		Random random = new Random();
		do {
			indexesAreNotSet.clear();
			indexesAreNotSet.add(random.nextInt(12 - 0) + 0);
			indexesAreNotSet.add(random.nextInt(12 - 0) + 0);
			indexesAreNotSet.add(random.nextInt(12 - 0) + 0);
		} while (gameRules.isSet(Arrays.asList(revealedCards.get(indexesAreNotSet.get(0)),
				revealedCards.get(indexesAreNotSet.get(1)), revealedCards.get(indexesAreNotSet.get(2)))));
		return indexesAreNotSet;
	}
}
