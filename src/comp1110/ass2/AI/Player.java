package comp1110.ass2.AI;

import comp1110.ass2.BoardState;

/**
 * Created by calum on 10/12/2016.
 */
public interface Player {
    String getBestMove(BoardState board, char deckPiece, char opponentDeckPiece);
}
