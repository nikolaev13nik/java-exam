package com.ngsoft.part2.SetGame.impl;

import java.util.ArrayList;
import java.util.List;
import com.ngsoft.part2.SetGame.Card;
import com.ngsoft.part2.SetGame.Score;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayResult {

	private boolean isSet;
	private List<Card> revealedCards;	
	private List<Card> chosenCards;
	private boolean gameIsFinish;
	private Score score;
	private String errorMessage;
	
	
	
	public PlayResult( Score score,List<Card>revealCards) {
		
		this.revealedCards = revealCards;
		this.chosenCards = new ArrayList<>();
		this.score = score;
		this.gameIsFinish=false;
		this.errorMessage="";
		this.isSet=false;
		
	}
	
	public void addChosenCard(Card card) {
		chosenCards.add(card);
	}
	
	public void resetShosenCards() {
		chosenCards.clear();
	}
	
	public void resertState() {
		this.revealedCards.clear();
		this.chosenCards.clear();
		this.gameIsFinish=false;
		this.score.resetScore();
		this.errorMessage="";
		this.isSet=false;
	}
}
