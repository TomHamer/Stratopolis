package comp1110.ass2;

import java.util.ArrayList;

/**
 * Created by Tom on 3/10/2016.
 */
// Entirely the work of Tom Hamer
public class EasyPlayer implements Player {
    //keeps track of whether this particular instance of AI is playing as red or as green
    private boolean redIsPlaying;


    public EasyPlayer(boolean redIsPlaying) {
        this.redIsPlaying = redIsPlaying;
    }

    //gets the best move through what is essentially a 1-recursion-depth minimax algorithm
    public String getBestMove(BoardState board, char deckPiece, char opponentDeckPiece) {
        //generates all possible boards that could come from moves that are available to the AI
        ArrayList<BoardState> possibleBoards = generateNextBoards(board,redIsPlaying,deckPiece);
        BoardState bestBoard = possibleBoards.get(0);
        int moveNumber = 0;
        //looks through the boards, evaluating each with a static evaluation function
        for(int i = 0; i<possibleBoards.size();i++) {
            if(evaluateBoard(bestBoard, redIsPlaying)<evaluateBoard(possibleBoards.get(i),redIsPlaying)) {
                bestBoard = possibleBoards.get(i);
            }
        }
        //finally, return the last part of the string for the best board, which is the move that the player did
        return bestBoard.GetBoard().substring(bestBoard.GetBoard().length() - 4);
    }
    //generates an arraylist of boards
    private ArrayList<BoardState> generateNextBoards(BoardState board, boolean isRedsTurn, char deckPiece) {
        ArrayList<BoardState> toReturn = new ArrayList<>();
        ArrayList<String> movesList = board.generateAllPossibleMoves(isRedsTurn,deckPiece);
        //maps out all the possible moves
        for(int i = 0; i<movesList.size();i++) {
            if (board.IsValidMove(movesList.get(i))) {
                BoardState tBoard = new BoardState(board.GetBoard()); // initialise a new board
                tBoard.PlaceTile(movesList.get(i));
                toReturn.add(tBoard);
            }
        }
        return toReturn;
    }

    private int evaluateBoard(BoardState board, boolean isRedsTurn) {
        return board.BoardScore(!isRedsTurn)-board.BoardScore(isRedsTurn); // opposite because the parameter is "green"
    }
}
