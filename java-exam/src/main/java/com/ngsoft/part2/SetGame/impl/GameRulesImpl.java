package com.ngsoft.part2.SetGame.impl;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import com.ngsoft.part2.SetGame.Card;
import com.ngsoft.part2.SetGame.GameRules;

public class GameRulesImpl implements GameRules {

	private static final int NUMBER_CARD_SET = 3;
	

	@Override
	public boolean isSet(Collection<Card> cards) {
		
		if (cards.size() != NUMBER_CARD_SET) {
			return false;
		}

		List<Card> cardsArr =  cards.stream().collect(Collectors.toList());
		
		Card card1 = cardsArr.get(0);
		Card card2 = cardsArr.get(1);
		Card card3 = cardsArr.get(2);

		if (isFeaturesSame(card1.getColor(), card2.getColor(), card3.getColor())
				|| isFeaturesSame(card1.getItemCount(), card2.getItemCount(), card3.getItemCount())
				|| isFeaturesSame(card1.getShape(), card2.getShape(), card3.getShape())
				|| isFeaturesSame(card1.getTexture(), card2.getTexture(), card3.getTexture())) {
			return true;
		}

		return false;
	}

	private <T> boolean isFeaturesSame(T feature1, T feature2, T featurer3) {

		if (feature1.equals(feature2) && feature2.equals(featurer3)) {
			return true;
		}
		if (!feature1.equals(feature2) && !feature2.equals(featurer3) && !feature1.equals(featurer3)) {
			return true;
		}

		return false;
	}
}
