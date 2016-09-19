package comp1110.ass2;
import comp1110.ass2.BoardState;
import java.util.ArrayList;
/**
 * Created by Tom on 8/09/2016.
 */
public class Minimax extends Brain {
    private boolean redIsPlaying;
    private final int LOOKAHEAD = 2;
    public Minimax(boolean redIsPlaying) {
        this.redIsPlaying = redIsPlaying;
    }
    public String getBestMove(BoardState board,int lookahead) {
        ArrayList<BoardState> possibleBoards = generateNextBoards(board,redIsPlaying);
        BoardState bestBoard = possibleBoards.get(0);
        int moveNumber = 0;
            for(int i = 0; i<possibleBoards.size();i++) {
                if(evaluateTree(bestBoard, lookahead, redIsPlaying)<evaluateTree(possibleBoards.get(i), lookahead, redIsPlaying)) {
                    bestBoard = possibleBoards.get(i);
                    moveNumber = i;
                }
            }
            return StratoGame.generatePossibleMoves(board,redIsPlaying).get(moveNumber);

    }

    private int evaluateTree(BoardState board, int lookahead,boolean isRedsTurn) {
        if(lookahead>0) {
            ArrayList<BoardState> boards = generateNextBoards(board, isRedsTurn);
            int[] nextEvals = new int[boards.size()];
            for(int i = 0; i<nextEvals.length; i++) {
                nextEvals[i] = evaluateTree(boards.get(i),lookahead-1,!isRedsTurn);
            }
                if(isRedsTurn && redIsPlaying || !isRedsTurn && !redIsPlaying) {
                    //maximise
                    return maximum(nextEvals);
                } else {
                    //minimise
                    return minimum(nextEvals);
                }

            } else {

                return evaluateBoard(board,isRedsTurn);

        }
    }
    private int evaluateBoard(BoardState board, boolean isRedsTurn) {
            return board.BoardScore(!isRedsTurn);
    }
    private ArrayList<BoardState> generateNextBoards(BoardState board, boolean isRedsTurn) {
        ArrayList<BoardState> toReturn = new ArrayList<>();
        ArrayList<String> movesList = StratoGame.generatePossibleMoves(board,isRedsTurn);
        for(String i:movesList) {
            if (board.IsValidMove(i)) {
                board.PlaceTile(i);
                toReturn.add(board);
            }
        }
        return toReturn;
    }
    private int maximum(int[] ints) {
        int max = ints[0];
        for (int anInt : ints) {
            if (max < anInt) {
                max = anInt;
            }
        }
        return max;
    }
    private int minimum(int[] ints) {
        int min = ints[0];
        for (int anInt : ints) {
            if (min > anInt) {
                min = anInt;
            }
        }
        return min;
    }
}
