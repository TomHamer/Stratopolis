package comp1110.ass2;

import java.util.ArrayList;

/**
 * Created by Tom on 1/10/2016.
 */
public class MediumPlayer implements Player {

//entirely the work of Tom Hamer. Vanilla minimax with two lookaheads. Since alpha-beta only decreases the amount
//of time and does not increase performance it was not needed. This function always executes in less than a few seconds.
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
            if(evaluateTree(bestBoard, redIsPlaying, opponentDeckPiece) < evaluateTree(possibleBoards.get(i), redIsPlaying, opponentDeckPiece)) {
                bestBoard = possibleBoards.get(i);
                moveNumber = i;
            }
        }
        return bestBoard.GetBoard().substring(bestBoard.GetBoard().length() - 4);
    }
    //generate the next boards through using the list of moves generated in StratoGame.java
    private ArrayList<BoardState> generateNextBoards(BoardState board, boolean isRedsTurn, char deckPiece) {
        ArrayList<BoardState> toReturn = new ArrayList<>();
        ArrayList<String> movesList = board.generateAllPossibleMoves(isRedsTurn, deckPiece);

        for (String aMovesList : movesList) {
            BoardState tBoard = new BoardState(board.GetBoard()); // initialise a new board
            tBoard.PlaceTile(aMovesList);
            toReturn.add(tBoard);
        }

        return toReturn;
    }

    //finds the board that minimises the other player's ability to play a good move
    private int evaluateTree(BoardState board, boolean isRedsTurn, char opponentsDeckPiece) {
        int maxi = 0;
        if(!(gameOverQuery(board))) {
            ArrayList secondTierBoards = generateNextBoards(board, !isRedsTurn, opponentsDeckPiece);
            //here update second tier boards by iterating through all the possibilities
            for (Object secondTierBoard : secondTierBoards) {
                //find the maximum value that occurs here
                int boardValue = evaluateBoard((BoardState) secondTierBoard, !isRedsTurn);
                if (boardValue > maxi) {
                    maxi = boardValue;
                }
            }
        } else {
            maxi = evaluateBoard(board, !isRedsTurn); //just evaluates the board if the game is over
        }
        //return the negation of the maximum so that the next node can minimise.
        return -maxi;


    }

    //static evaluation function, takes one board minus the other
    private int evaluateBoard(BoardState board, boolean isRedsTurn) {
        return board.BoardScore(!isRedsTurn)-board.BoardScore(isRedsTurn);
    }
    //queries if the game is over
    private boolean gameOverQuery(BoardState board) {
        return board.GetBoard().length()==164; //a boardstate after a complete game has length 168
    }
}