package comp1110.ass2;

import comp1110.ass2.AI.MonteCarloPlayer;
import comp1110.ass2.AI.NeuralNetworkFiles.IntelligentPlayer;
import comp1110.ass2.AI.NeuralNetworkFiles.NN1HL;
import comp1110.ass2.AI.Player;
import comp1110.ass2.AI.RandomPlayer;
import comp1110.ass2.gui.Board;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Tom on 13/10/2016.
 */
// Written entirely by Tom
public class PlayGames {
    public static void main(String[] args) {


        //some examples you could use!
        /*
        EasyPlayer ep1 = new EasyPlayer(true);

        EasyPlayer ep2 = new EasyPlayer(false);

        MediumPlayer mp1 = new MediumPlayer(true);
        MediumPlayer mp2 = new MediumPlayer(false);
        //intellegent player requires a neural network architecture

        //intelligent player can only play as red
        IntelligentPlayer ip = new IntelligentPlayer(n);
        */
        NN1HL n = new NN1HL(8,676,1,0.001);
        IntelligentPlayer ip = new IntelligentPlayer(n);
        RandomPlayer rand = new RandomPlayer(false);

        //create two players and run a game here
        //printRunningStats = true gives live updates of how the game is progressing
        //typically use ixt when running 1 game for testing
        //when sampling from many games, use false. This is the function used in the machine learning extension
        //as well as when debugging AI


        playGame(100,rand,ip,false);
    }


    //plays a number of games given by number of games and prints data at the end of the game in the form:
    //first line is a list of boards that existed during the game
    //second line is a list of +/-1 with +1 for all the boards that the player moved to who won, and -1 for all the boards
    //that the player moved to who lost
    //The printRunningStats = true gives stats midgame after each move for debugging to see which player is losing or winning
    private static void playGame(int numberOfGames, Player greenPlayer, Player redPlayer, boolean printRunningStats) {
        Board board = new Board();
        BoardState boardState = new BoardState("MMUA");


            for(int i = 0; i<numberOfGames; i++) {
                ArrayList<String> boards = new ArrayList<>();
                ArrayList<Integer> outcomes = new ArrayList<>();

                //generate a new deck
                char[] Rdeck;
                char[] Gdeck;

                Rdeck = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};

                Gdeck = new char[]{'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T'};


                //shuffle the deck
                char[] RpieceArray = shuffle(Rdeck);
                char[] GpieceArray = shuffle(Gdeck);


                int pieceMarker = 0;

                for (int piecesPlayed = 0; piecesPlayed < 20; piecesPlayed++) {
                    //pair[0] for the green player
                    //pair[1] for the red player
                    boardState = new BoardState(boardState.GetBoard() + greenPlayer.getBestMove(boardState, GpieceArray[pieceMarker], RpieceArray[pieceMarker]));
                    boards.add(boardState.GetBoard());
                    boardState = new BoardState(boardState.GetBoard() + redPlayer.getBestMove(boardState, RpieceArray[pieceMarker], GpieceArray[pieceMarker]));
                    boards.add(boardState.GetBoard());
                    //red and green moved so add 1 to red piece marker
                    pieceMarker++;

                    if(printRunningStats) {
                        System.out.println("Red's score is currently " + boardState.BoardScore(false));
                        System.out.println("Green's score is currently " + boardState.BoardScore(true));
                    }

                    if (piecesPlayed == 19) {
                        if (boardState.BoardScore(true) < boardState.BoardScore(false)) {
                            // System.out.println("green won");
                            //go back and assign 1 to all of greens moves and -1 to all of reds moves
                            for (int boardNumber = 0; boardNumber < boards.size(); boardNumber++) {
                                //since green went first assign every even number 1 and every odd -1
                                if (boardNumber % 2 == 0) {
                                    outcomes.add(-1);
                                } else {
                                    outcomes.add(1);
                                }
                            }
                        } else {
                            //System.out.println("red won");
                            for (int boardNumber = 0; boardNumber < boards.size(); boardNumber++) {
                                //since green went first assign every even number -1 and every odd 1
                                if (boardNumber % 2 == 0) {
                                    outcomes.add(1);
                                } else {
                                    outcomes.add(-1);
                                }
                            }
                        }
                    }
                }


                System.out.println(boards);
                System.out.println(outcomes);
            }


    }


    private static char[] shuffle(char[] list) {

        Random random = new Random();

        for(int i = list.length - 1; i >= 0; i--) {
            int rand = random.nextInt(i + 1);

            char temp = list[rand];
            list[rand] = list[i];
            list[i] = temp;

        }


        return list;
    }

}
