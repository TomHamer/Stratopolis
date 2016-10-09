package comp1110.ass2;
/*
import java.util.ArrayList;
import org.la4j.Matrix;

/**
 * Created by Tom on 25/09/2016.
 */
public class Trainer {


/*

    //lets just make red the AI, so we will train the network just to play red
    //for this we will need to generate random board states, and then put them through the minmax function
    //to assign a value

    //we can generate boardStates using trees

    public static ArrayList<BoardState> generateBoardStates(ArrayList<BoardState> boards,int lookahead, boolean currentRedsTurn) {
        ArrayList<BoardState> toReturn = new ArrayList<>();
        toReturn.addAll(boards);
        if(lookahead >0) {
            for (int j = 0; j < boards.size(); j++) {
                ArrayList<String> nextMoves = StratoGame.generatePossibleMoves(boards.get(j), currentRedsTurn);
                for (int i = 0; i < nextMoves.size(); i++) {
                    BoardState newState = new BoardState(boards.get(j).GetBoard() + nextMoves.get(i));
                    toReturn.add(newState);
                }
            }

            return generateBoardStates(toReturn, lookahead - 1, !(currentRedsTurn));
        } else {
            return boards;
        }
    }

    public static void main(String[] args) {
        ArrayList<BoardState> initList = new ArrayList<>();
        initList.add(new BoardState("MMUA"));
        System.out.println(generateBoardStates(initList,1,true));

        //create a new brain
        Brain brain = new Minimax(true);
        brain.getBestMove(new BoardState("MMUA"),2);
    }
    private Matrix vectoriseBoard(BoardState board) {
        //this will be a 676 length binary vector. A red square will be replaced with a 1, and green replaced with -1. 0 will take the place of black squares and empty squares.
        //
        //The output value will be determined by the minimax function, and then normalised
        return null;
    }
    //once a list of moves has been created, initialise a matrix, put the values in and train the network




*/





}
