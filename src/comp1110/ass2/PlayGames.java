package comp1110.ass2;

import comp1110.ass2.gui.Board;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Tom on 13/10/2016.
 */
public class PlayGames {
    public static void main(String[] args) {


        //some examples you could use!

        EasyPlayer ep1 = new EasyPlayer(true);
        //HardPlayer hp = new HardPlayer(false);
        EasyPlayer ep2 = new EasyPlayer(false);
        MediumPlayer mp = new MediumPlayer(true);
        MediumPlayer mp2 = new MediumPlayer(false);
        NN1HL n = new NN1HL(8,676,1,0.001);
        IntelligentPlayer ip = new IntelligentPlayer(n);
        MonteCarloPlayer mcp = new MonteCarloPlayer(true);
        RandomPlayer rand = new RandomPlayer(true);

        //create two players and run a game here
        playGame(mcp,ip);
    }



    private static void playGame(Player greenPlayer,Player redPlayer) {
        Board board = new Board();
        BoardState boardState = new BoardState("MMUA");



            ArrayList<String> boards = new ArrayList<>();
            ArrayList<Integer> outcomes = new ArrayList<>();

            //generate a new deck
            char[] Rdeck;
            char[] Gdeck;

            Rdeck = new char[] {'A','B','C','D','E','F','G','H','I','J','A','B','C','D','E','F','G','H','I','J'};

            Gdeck = new char[] {'K','L','M','N','O','P','Q','R','S','T','K','L','M','N','O','P','Q','R','S','T'};


            //shuffle the deck
            char[] RpieceArray = shuffle(Rdeck);
            char[] GpieceArray = shuffle(Gdeck);


            int rpieceMarker = 0;
            int gpieceMarker = 0;
            for (int piecesPlayed = 0; piecesPlayed < 20; piecesPlayed++) {
                //pair[0] for the green player
                //pair[1] for the red player

                boardState = new BoardState(boardState.GetBoard() + redPlayer.getBestMove(boardState,RpieceArray[rpieceMarker],GpieceArray[gpieceMarker]));
                boards.add(boardState.GetBoard());
                //red moved so add 1 to red piece marker
                rpieceMarker++;
                boardState = new BoardState(boardState.GetBoard() + greenPlayer.getBestMove(boardState,GpieceArray[gpieceMarker],RpieceArray[rpieceMarker]));
                boards.add(boardState.GetBoard());
                gpieceMarker++;

                System.out.println("Red's score is currently "+boardState.BoardScore(false));
                System.out.println("Green's score is currently "+boardState.BoardScore(true));

                if (piecesPlayed == 19) {
                    if (boardState.BoardScore(true) < boardState.BoardScore(false)) {
                        // System.out.println("green won");
                        //go back and assign 1 to all of greens moves and -1 to all of reds moves
                        for(int boardNumber = 0; boardNumber<boards.size();boardNumber++) {
                            //since green went first assign every even number 1 and every odd -1
                            if(boardNumber%2 == 0) {
                                outcomes.add(-1);
                            } else {
                                outcomes.add(1);
                            }
                        }
                    } else {
                        //System.out.println("red won");
                        for(int boardNumber = 0; boardNumber<boards.size();boardNumber++) {
                            //since green went first assign every even number -1 and every odd 1
                            if(boardNumber%2 == 0) {
                                outcomes.add(1);
                            } else {
                                outcomes.add(-1);
                            }
                        }
                    }
                }



            System.out.println(boards);
            System.out.println(outcomes);
            System.out.println(boardState.GetBoard());
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
