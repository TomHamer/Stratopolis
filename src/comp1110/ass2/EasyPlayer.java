package comp1110.ass2;

import java.util.ArrayList;

/**
 * Created by Tom on 3/10/2016.
 */
public class EasyPlayer {
    //keeps track of whether this particular instance of AI is playing as red or as green
    private boolean redIsPlaying;

    public EasyPlayer(boolean redIsPlaying) {
        this.redIsPlaying = redIsPlaying;
    }

    //gets the best move through what is essentially a 1-recursion-depth minimax algorithm
    public String getBestMove(BoardState board, char deckPiece) {
        //generates all possible boards that could come from moves that are available to the AI
        ArrayList<BoardState> possibleBoards = generateNextBoards(board,redIsPlaying,deckPiece);
        BoardState bestBoard = possibleBoards.get(0);
        int moveNumber = 0;
        //looks through the boards, evaluating each with a static evaluation function
        for(int i = 0; i<possibleBoards.size();i++) {
            if(evaluateBoard(bestBoard, !redIsPlaying)<evaluateBoard(possibleBoards.get(i),!redIsPlaying)) {
                bestBoard = possibleBoards.get(i);
                moveNumber = i;
            }
        }
        //finally, takes the index of the best move that is found
        return board.generateAllPossibleMoves(redIsPlaying,deckPiece).get(moveNumber);
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
