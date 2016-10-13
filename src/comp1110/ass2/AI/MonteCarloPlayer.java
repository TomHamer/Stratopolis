package comp1110.ass2.AI;

import comp1110.ass2.BoardState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

/**
 * Created by Tom on 11/10/2016.
 */

//Entirely the work of Tom Hamer

//Yet another attempt to get good AI - this algorithm does a monte carlo tree search.
//To evaluate the strength of a board position, it selects a node randomly, and plays a game, updating
//the number of times the bot won or lost from that position. With each new game, another node is added
//which has its own statistics, also the results of each game are backpropagated up each node, giving a
//number of wins and a number of times games were played from that node. Dividing the wins and losses
//we get a value for how good the board position is.
//due to the high expense of generating random moves, this algorithm is ineffective
//however this implementation of monte carlo does work better than a bot choosing a random moves. Its really interesting
//to see how this bot starts off not so well, but really improves in the end game when exploring branches to the end of the game
//isn't as intensive. Against the random player it always seems at least to make the right decision at the very end!
//reference to https://jeffbradberry.com/posts/2015/09/intro-to-monte-carlo-tree-search/ for guidence on construction and theory for this algorithm

public class MonteCarloPlayer implements Player {

    private final int MAX_MOVES = 164; //the maximum number of moves in the game
    private HashMap<PlayerStatePairing,Integer> plays; //a hashmap of all the states linked to the number of times the state has been played
    private HashMap<PlayerStatePairing,Integer> wins; //a hashmap of all the states lined to the number of times the state has been won
    private boolean redIsPlaying;
    private final double WAITING_TIME_MILLISECONDS = 10; //the waiting time for each branch to be explored

    public MonteCarloPlayer(boolean redIsPlaying) {
        this.redIsPlaying = redIsPlaying;
        this.plays = new HashMap<>();
        this.wins = new HashMap<>();
    }

    //created this class because the player who is playing has to be made distinguished from the player making the move
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
    //this "state" class consists of a player and a board, which is then logged and looked up in a hashtable
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
        //we give each branch of the simulation a certain amount of time to run
        while((System.currentTimeMillis()-startTime)<WAITING_TIME_MILLISECONDS)
        {
            run_simulation(state, opponentDeckPiece);
            games++;
            //the simulation runs, and updates the hashmap of plays and wins
        }

        double toReturn = 0;
        int numGames = 0;
        ArrayList<String> moveList = state.boardState.generateAllPossibleMoves(!state.playingRed, opponentDeckPiece);
        for(String i:moveList) {
            BoardState newBS = new BoardState(state.boardState.GetBoard()+i);
            State newState = new State(newBS, !state.playingRed);
            //add all the ratios from all the games that were played together to get the average
            PlayerStatePairing key = new PlayerStatePairing(newState,redIsPlaying);
            if(plays.containsKey(key)) { //see if the dictionary of games that have been played contains a particular node
                numGames++;
                double newVal = wins.get(key) / plays.get(key); //get the ratio
                toReturn+=newVal;
            }
        }


        //finally, take the average ratio for all the games that were played,
        return toReturn/numGames;
    }

    private void run_simulation(State s, char opponentDeckPiece) {
        HashSet<PlayerStatePairing> visitedStates = new HashSet<>();
        //ArrayList<State> states_copy = this.states;
        BoardState board = new BoardState(s.boardState.GetBoard());
        Boolean bool = s.playingRed;
        State state = new State(board,bool);
        Random rand = new Random();

        boolean playerWon = false;
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

            PlayerStatePairing expandingKey = new PlayerStatePairing(state, redIsPlaying);

            //expand the tree if it hasnt been looked at and is at the top of the tree
            if (expand && !plays.containsKey(expandingKey)) {
                expand = false;
                plays.put(expandingKey, 0);
                wins.put(expandingKey, 0);
            }
            visitedStates.add(expandingKey);


            //ask if the game has ended
            if (gameOverQuery(state.boardState)) {
                //either red wins, or we break the tie
                playerWon = state.boardState.BoardScore(!expandingKey.redPlaying) < state.boardState.BoardScore(expandingKey.redPlaying);
                break;
            }
        }

            //backpropagate the results up through the defined parts of the tree
        for(PlayerStatePairing psp:visitedStates) {
            if (!(plays.containsKey(psp))) {
                continue;
            }
            int currentState = plays.get(psp);
            plays.put(psp, currentState++);
            if (psp.redPlaying && playerWon) {
                int currentWins = wins.get(psp);
                wins.put(psp, currentWins++);
            }
        }


    }

    private boolean gameOverQuery(BoardState board) {
            return board.GetBoard().length() == 164; //a boardstate after a complete game has length 160
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
        return board.generateAllPossibleMoves(redIsPlaying,deckPiece).get(moveNumber);
    }


    //generate the next set of boards required
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




