package comp1110.ass2;

import comp1110.ass2.gui.Board;

import java.util.ArrayList;

/**
 * Created by Tom on 13/10/2016.
 */
public class PlayGames {
    /*public static void main(String[] args) {
        Board board = new Board();
        BoardState boardState = new BoardState("MMUA");
        //sets up the AI based on what the player wants
        EasyPlayer ep1 = new EasyPlayer(true);
        //HardPlayer hp = new HardPlayer(false);
        EasyPlayer ep2 = new EasyPlayer(false);
        MediumPlayer mp = new MediumPlayer(true);
        MediumPlayer mp2 = new MediumPlayer(false);
        NN1HL n = new NN1HL(8,676,1,0.001);
        //Board.HardPlayer hp = new Board.HardPlayer(false);
        IntelligentPlayer ip = new IntelligentPlayer(n);
        MonteCarloPlayer mcp = new MonteCarloPlayer(true);
        RandomPlayer rand = new RandomPlayer(true);

        // int NO_OF_GAMES = 1;
        for (int i = 0; i < 1; i++) {


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


            RDeck.pieceArray = RpieceArray;
            GDeck.pieceArray = GpieceArray;


            for (int piecesPlayed = 0; piecesPlayed < 20; piecesPlayed++) {
                //pair[0] for the green player
                //pair[1] for the red player

                RDeck.placePiece(mcp.getBestMove(boardState,RDeck.getCurrentPiece(),GDeck.getCurrentPiece()));
                boards.add(boardState.GetBoard());
                GDeck.placePiece(ep2.getBestMove(boardState, GDeck.getCurrentPiece(),RDeck.getCurrentPiece()));
                boards.add(boardState.GetBoard());

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


            }
            System.out.println(boards);
            System.out.println(outcomes);
            System.out.println(boardState.GetBoard());
        }

    }
    }*/
}
