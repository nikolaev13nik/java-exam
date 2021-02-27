package com.ngsoft.part2.SetGameTests;

import static org.junit.Assert.*;
import java.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import com.ngsoft.part2.SetGame.Card;
import com.ngsoft.part2.SetGame.CardFeatures.Color;
import com.ngsoft.part2.SetGame.CardFeatures.ItemCount;
import com.ngsoft.part2.SetGame.CardFeatures.Shape;
import com.ngsoft.part2.SetGame.CardFeatures.Texture;
import com.ngsoft.part2.SetGame.GameRules;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:spring-config.xml")
public class GameRulesTest {

	@Autowired
	GameRules gameRules;

	@Test
	public void WHEN_FEATURES_ARE_DIFFERENT() {

		Card card1 = new Card(Shape.DIAMOND, Color.BLUE, ItemCount.ONE, Texture.FULL);
		Card card2 = new Card(Shape.OVAL, Color.GREEN, ItemCount.THREE, Texture.HOLLOW);
		Card card3 = new Card(Shape.SQUIGGLE, Color.PURPLE, ItemCount.TWO, Texture.STRIPED);

		assertTrue(gameRules.isSet(Arrays.asList(card1, card2, card3)));

	}

	@Test
	public void WHEN_FEATURES_ARE_SAME() {
		Card card1 = new Card(Shape.DIAMOND, Color.BLUE, ItemCount.ONE, Texture.FULL);
		Card card2 = new Card(Shape.DIAMOND, Color.GREEN, ItemCount.THREE, Texture.HOLLOW);
		Card card3 = new Card(Shape.DIAMOND, Color.PURPLE, ItemCount.TWO, Texture.STRIPED);

		assertTrue(gameRules.isSet(Arrays.asList(card1, card2, card3)));

		Card card4 = new Card(Shape.OVAL, Color.PURPLE, ItemCount.TWO, Texture.HOLLOW);
		Card card5 = new Card(Shape.DIAMOND, Color.PURPLE, ItemCount.THREE, Texture.STRIPED);
		assertTrue(gameRules.isSet(Arrays.asList(card3, card4, card5)));

	}

	@Test
	public void WHEN_COLLECTION_IS_NOT_SET() {
		Card card1 = new Card(Shape.DIAMOND, Color.BLUE, ItemCount.ONE, Texture.FULL);
		Card card2 = new Card(Shape.DIAMOND, Color.BLUE, ItemCount.THREE, Texture.HOLLOW);
		Card card3 = new Card(Shape.SQUIGGLE, Color.PURPLE, ItemCount.THREE, Texture.HOLLOW);

		assertFalse(gameRules.isSet(Arrays.asList(card1, card2, card3)));

	}

	@Test
	public void WHEN_NUMBER_CARDS_IS_WRONG() {

		Card card1 = new Card(Shape.DIAMOND, Color.BLUE, ItemCount.ONE, Texture.FULL);
		Card card2 = new Card(Shape.OVAL, Color.GREEN, ItemCount.THREE, Texture.HOLLOW);
		Card card3 = new Card(Shape.SQUIGGLE, Color.PURPLE, ItemCount.TWO, Texture.STRIPED);
		Card card4 = new Card(Shape.DIAMOND, Color.BLUE, ItemCount.ONE, Texture.FULL);

		assertFalse(gameRules.isSet(Arrays.asList(card1, card2)));
		assertFalse(gameRules.isSet(Arrays.asList(card1, card2, card3, card4)));

	}

}
