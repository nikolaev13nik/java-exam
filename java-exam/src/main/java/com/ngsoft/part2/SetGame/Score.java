package com.ngsoft.part2.SetGame;




public class Score {

	private int valueScore;

	public Score() {
		this.valueScore = 0;
	}

	public int getValueScore() {
		return valueScore;
	}

	public void decreaseScore() {
		this.valueScore -= 1;
	}

	public void increaseScore() {
		this.valueScore += 1;
	}

	public void resetScore() {
		this.valueScore = 0;
	}

}
