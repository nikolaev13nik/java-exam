package com.ngsoft.part2.SetGame;

import java.util.List;

import com.ngsoft.part2.SetGame.impl.PlayResult;

public interface GamePlay {

    /**
     * resets the state of the game and the deck.  deals the initial revealed cards
     */
    void start();

    /**
     *
     * @return the collection of cards that are currently displayed to user
     */
    List<Card> revealedCards();

    /**
     * allows the player to play next move by selecting 3 cards from the revealed cards list and
     * claiming these are state
     * @param card1 index of first cards
     * @param card2 index of second card
     * @param card3 index of third card
     * @return whether the selected cards are a set.
     * NOTE:boolean is probably not enouugh here - we should generate a PlayResultClass....
     **/

    PlayResult play(int card1, int card2, int card3);

    /**
     * any play should either increase or decrease the score, depending on success
     * @return the current score which is the number of success minus failures.
     */
    int getScore();


}
