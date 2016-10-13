package comp1110.ass2.AI;

import comp1110.ass2.BoardState;
import comp1110.ass2.StratoGame;

/**
 * Created by Tom on 13/10/2016.
 */
//turning the strato game function into a class that can extend the AI
public class RandomPlayer implements Player {

    public RandomPlayer(boolean isPlayingRed) {

    }
    public String getBestMove(BoardState board, char deckPiece, char opponentDeckPiece) {
        return StratoGame.generateMove(board.GetBoard(),deckPiece,opponentDeckPiece);
    }


}
