package com.ngsoft.part2.SetGame.impl;

import java.util.ArrayList;
import java.util.List;
import com.ngsoft.part2.SetGame.Card;
import com.ngsoft.part2.SetGame.DealConstants;
import com.ngsoft.part2.SetGame.Deck;
import com.ngsoft.part2.SetGame.GamePlay;
import com.ngsoft.part2.SetGame.GameRules;
import com.ngsoft.part2.SetGame.Score;

public class GamePlayService implements GamePlay {

	Deck deck;
	Score score;
	List<Card> revealedCards;
	GameRules gameRules;
	PlayResult playResult;

	public GamePlayService(Deck deck, Score score, GameRules gameRules) {
		this.deck = deck;
		this.score = score;
		this.revealedCards = new ArrayList<Card>();
		this.gameRules = gameRules;
		this.playResult = new PlayResult(score, revealedCards);
	}

	@Override
	public void start() {
		this.score.resetScore();
		this.playResult.resertState();		
		deck.reset();
		deck.shuffle();
		this.revealedCards.clear();
		this.revealedCards.addAll(deck.deal(DealConstants.START_DEAL));
	}

	@Override
	public List<Card> revealedCards() {
		return revealedCards;
	}

	@Override
	public PlayResult play(int card1, int card2, int card3) {

		playResult.resetShosenCards();
		int currentSizeDeal = revealedCards.size();
		if (card1 >= currentSizeDeal || card2 >= currentSizeDeal || card3 >= currentSizeDeal) {
			playResult.setErrorMessage("indexes are not correct");
			playResult.setSet(false);
			return playResult;
		}

		playResult.addChosenCard(revealedCards.get(card1));
		playResult.addChosenCard(revealedCards.get(card2));
		playResult.addChosenCard(revealedCards.get(card3));

		boolean isSet = gameRules.isSet(playResult.getChosenCards());

		if (!isSet) {
			score.decreaseScore();
			playResult.setSet(false);

		} else {
			score.increaseScore();
			playResult.setSet(true);

			if (deck.size() >= DealConstants.NEXT_DEAL) {
				gettingNextDealAndPutToRevealedCards(card1, card2, card3);

			} else {
				if (revealedCards.size() != 0) {
					revealedCards = clearingRevealedCardsFromSet(card1, card2, card3);
					playResult.setRevealedCards(revealedCards);
				}
			}
		}

		if (revealedCards.size() == 0) {
			playResult.setGameIsFinish(true);
			return playResult;
		}
		return playResult;
	}

	private List<Card> clearingRevealedCardsFromSet(int card1, int card2, int card3) {

		List<Card> tempListWithoutSet = new ArrayList<Card>();
		for (int i = 0; i < revealedCards.size(); i++) {
			if (i != card1 && i != card2 && i != card3) {
				tempListWithoutSet.add(revealedCards.get(i));
			}
		}
		return tempListWithoutSet;
	}

	private void gettingNextDealAndPutToRevealedCards(int card1, int card2, int card3) {
		List<Card> newDeaList = deck.deal(DealConstants.NEXT_DEAL);
		revealedCards.set(card1, newDeaList.get(0));
		revealedCards.set(card2, newDeaList.get(1));
		revealedCards.set(card3, newDeaList.get(2));
	}

	@Override
	public int getScore() {
		return score.getValueScore();
	}
}
