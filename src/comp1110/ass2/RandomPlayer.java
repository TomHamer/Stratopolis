package comp1110.ass2;

/**
 * Created by Tom on 13/10/2016.
 */
//turning the strato game function into a class that can extend the AI
public class RandomPlayer {

    public RandomPlayer(boolean isPlayingRed) {

    }
    public String getBestMove(BoardState board, char deckPiece,char opponentDeckPiece) {
        return StratoGame.generateMove(board.GetBoard(),deckPiece,opponentDeckPiece);
    }


}
