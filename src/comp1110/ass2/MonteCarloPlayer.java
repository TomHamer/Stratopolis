package comp1110.ass2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

/**
 * Created by Tom on 11/10/2016.
 */


//reference to https://jeffbradberry.com/posts/2015/09/intro-to-monte-carlo-tree-search/ for guidence on construction and theory for this algorithm

public class MonteCarloPlayer {
    private final int MAX_MOVES = 164;
    private HashMap<PlayerStatePairing,Integer> plays;
    private HashMap<PlayerStatePairing,Integer> wins;
    private boolean redIsPlaying;
    public ArrayList<State> states;

    public MonteCarloPlayer(boolean redIsPlaying) {
        this.states = new ArrayList<>();
        this.redIsPlaying = redIsPlaying;
        this.plays = new HashMap<>();
        this.wins = new HashMap<>();
    }

    private class PlayerStatePairing {
        private State state;
        public boolean redPlaying;
        private PlayerStatePairing(State s,boolean redPlaying) {
            this.state = s;
            this.redPlaying = redPlaying;
        }
        @Override
        public String toString() {
            return "["+state.toString()+","+redPlaying+"]";
        }

    }

    private class State {
        BoardState boardState;
        boolean playingRed;
        private State(BoardState b, boolean redIsPlaying) {
            this.boardState = b;
            this.playingRed = redIsPlaying;
        }
        @Override
        public String toString() {
            return "["+boardState.GetBoard()+","+playingRed+"]";
        }

    }

    private double get_play(State state, char opponentDeckPiece) {
        long startTime = System.currentTimeMillis(); //fetch starting time
        int games =0;
        states.add(state);
        while((System.currentTimeMillis()-startTime)<1000)
        {
            run_simulation(state, opponentDeckPiece);
            games++;
            //the simulation runs, and updates the hashmap of plays and wins
        }


        double maxVal = -1000;
        ArrayList<String> moveList = state.boardState.generateAllPossibleMoves(!state.playingRed, opponentDeckPiece);
        for(String i:moveList) {
            BoardState newBS = new BoardState(state.boardState.GetBoard()+i);
            State newState = new State(newBS, !state.playingRed);

            PlayerStatePairing key = new PlayerStatePairing(newState,redIsPlaying);
            if(plays.containsKey(key)) {
                double newVal = wins.get(key)/plays.get(key);
                if(newVal>maxVal){
                    maxVal = newVal;
                }
        }
    }
        //for children of the state
            //if the child exists
                //get childRatio

        return -maxVal;
    }

    private void run_simulation(State s, char opponentDeckPiece) {
        HashSet<PlayerStatePairing> visitedStates = new HashSet<>();
        //ArrayList<State> states_copy = this.states;
        BoardState board = new BoardState(s.boardState.GetBoard());
        Boolean bool = s.playingRed;
        State state = new State(board,bool);
        Random rand = new Random();

        boolean redWon = false;
        boolean expand = true;

        for(int i = 0; i<MAX_MOVES;i++) { //plays a certain number of random moves into the future

            //run simulation. This needs to give a random move
            //generate a random deckpiece each timed
            char deckpiece;
            if (i == 0) {
                //in this case use the "opponent deckpiece"
                deckpiece = opponentDeckPiece;
            } else {
                //use a random deckpiece
                if (state.playingRed) {
                    deckpiece = (new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'})[rand.nextInt(10)];
                } else {
                    deckpiece = (new char[]{'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T'})[rand.nextInt(10)];
                }
            }
            ArrayList<String> moveList = state.boardState.generateAllPossibleMoves(state.playingRed, deckpiece);
            //choose a random move
            String play = moveList.get(rand.nextInt(moveList.size()));
            //place the move on the board
            state.boardState.PlaceTile(play);
            //initialise the new state
            BoardState b = new BoardState(state.boardState.GetBoard());
            boolean pr = !state.playingRed;
            state = new State(b, pr);
            //states_copy.add(state);

            PlayerStatePairing expandingKey = new PlayerStatePairing(state, redIsPlaying);

            //expand the tree
            if (expand && !plays.containsKey(expandingKey)) {
                expand = false;
                plays.put(expandingKey, 0);
                wins.put(expandingKey, 0);
            }
            visitedStates.add(expandingKey);


            //ask if the game has ended
            if (gameOverQuery(state.boardState)) {
                //either red wins, or we break the tie
                redWon = state.boardState.BoardScore(true) < state.boardState.BoardScore(false);
                break;
            }
        }

            //backprop
        for(PlayerStatePairing psp:visitedStates) {
            if (!(plays.containsKey(psp))) {
                continue;
            }
            int currentState = plays.get(psp);
            plays.put(psp, currentState++);
            if (psp.redPlaying && redWon) {
                int currentWins = wins.get(psp);
                wins.put(psp, currentWins++);
            }
        }


    }

    private boolean gameOverQuery(BoardState board) {
            return board.GetBoard().length() == 168; //a boardstate after a complete game has length 160
    }

    public String getBestMove(BoardState board, char deckPiece, char opponentDeckPiece) {
        //generates all possible boards that could come from moves that are available to the AI
        ArrayList<BoardState> possibleBoards = generateNextBoards(board,redIsPlaying,deckPiece);
        BoardState bestBoard = possibleBoards.get(0);
        int moveNumber = 0;
        //looks through the boards, evaluating each
        for(int i = 0; i<possibleBoards.size();i++) {
            if(get_play(new State(bestBoard,redIsPlaying),opponentDeckPiece)<get_play(new State(possibleBoards.get(i),redIsPlaying),opponentDeckPiece)) {
                bestBoard = possibleBoards.get(i);
                moveNumber = i;
            }
        }

        //finally, takes the index of the best move that is found
        return board.generateAllPossibleMoves(true,deckPiece).get(moveNumber);
    }

    private ArrayList<BoardState> generateNextBoards(BoardState board, boolean red, char deckPiece) {
        ArrayList<BoardState> toReturn = new ArrayList<>();
        //always reds turn
        ArrayList<String> movesList = board.generateAllPossibleMoves(red,deckPiece);
        //maps out all the possible moves
        for (String aMovesList : movesList) {
            BoardState tBoard = new BoardState(board.GetBoard()); // initialise a new board
            tBoard.PlaceTile(aMovesList);
            toReturn.add(tBoard);
        }
        return toReturn;
    }






}




