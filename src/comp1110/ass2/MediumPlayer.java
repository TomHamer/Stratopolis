package comp1110.ass2;

import java.util.ArrayList;

/**
 * Created by Tom on 1/10/2016.
 */
public class MediumPlayer{
    private boolean redIsPlaying;

    public MediumPlayer(boolean redIsPlaying) {
        this.redIsPlaying = redIsPlaying;
    }


    public String getBestMove(BoardState board, char deckPiece, char opponentDeckPiece) {
        //generates all possible boards that could come from moves that are available to the AI
        ArrayList<BoardState> possibleBoards = generateNextBoards(board,redIsPlaying,deckPiece);
        BoardState bestBoard = possibleBoards.get(0);
        int moveNumber = 0;
        //looks through the boards, evaluating each with a static evaluation function
        for(int i = 0; i<possibleBoards.size();i++) {
            //finds the board that maximises "evaluate tree"
            if(evaluateTree(bestBoard, redIsPlaying, deckPiece, opponentDeckPiece) < evaluateTree(possibleBoards.get(i), redIsPlaying, deckPiece, opponentDeckPiece)) {
                bestBoard = possibleBoards.get(i);
                moveNumber = i;
            }
        }
        //finally, takes the index of the best move that is found
        return board.generateAllPossibleMoves(redIsPlaying,deckPiece).get(moveNumber);
    }
    //generate the next boards through using the list of moves generated in StratoGame.java
    private ArrayList<BoardState> generateNextBoards(BoardState board, boolean isRedsTurn, char deckPiece) {
        ArrayList<BoardState> toReturn = new ArrayList<>();
        ArrayList<String> movesList = board.generateAllPossibleMoves(isRedsTurn, deckPiece);

        for (int i = 0; i < movesList.size(); i++) {
            if (board.IsValidMove(movesList.get(i))) {
                BoardState tBoard = new BoardState(board.GetBoard()); // initialise a new board
                tBoard.PlaceTile(movesList.get(i));
                toReturn.add(tBoard);
            }
        }

        return toReturn;
    }

    //finds the board that minimises the other player's ability to play a good move
    public int evaluateTree(BoardState board, boolean isRedsTurn, char playersDeckPiece, char opponentsDeckPiece) {
        int maxi = 0;

        ArrayList secondTierBoards = generateNextBoards(board,!isRedsTurn,opponentsDeckPiece);
        //here update second tier boards by iterating through all the possibilities
        for(int j = 0;j<secondTierBoards.size();j++) {
            //find the maximum value that occurs here
            int boardValue = evaluateBoard((BoardState) secondTierBoards.get(j),!isRedsTurn);
            if(boardValue<maxi) {
                maxi = boardValue;
            }
        }

        return -maxi; //the maximum value that was found, but return negative, as this is the best move that was found for the opposite player


    }
    private int evaluateBoard(BoardState board, boolean isRedsTurn) {
        return board.BoardScore(!isRedsTurn)-board.BoardScore(isRedsTurn);
    }
}