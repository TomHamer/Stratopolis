package comp1110.ass2.gui;

import comp1110.ass2.BoardState;
import comp1110.ass2.StratoGame;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import comp1110.ass2.Colour;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import  java.io.*;
import  sun.audio.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;

public class Board extends Application {

    private static final int BOARD_WIDTH = 933;
    private static final int BOARD_HEIGHT = 700;
    private static final int SQUARE_SIZE = 23;
    private static final int DECK_COORD_X = 50;
    private static final int DECK_COORD_Y = 50;
    private final BoardState boardState = new BoardState("MMUA");
    private Group root = new Group();
    private Group current = null;
    private Group displayBoard;
    private Text greenScore, redScore;
    private boolean greensTurn = true;
    private AudioStream AS;
    private boolean soundOn = false;







    // FIXME Task 8: Implement a basic playable Strato Game in JavaFX that only allows pieces to be placed in valid places

    // FIXME Task 9: Implement scoring

    // FIXME Task 11: Implement a game that can play valid moves (even if they are weak moves)

    // FIXME Task 12: Implement a game that can play good moves

    public void addPlacement(String placement) {
        boardState.UpdateBoardGroup(displayBoard, SQUARE_SIZE, placement);
    }


    public void start(Stage primaryStage) {


        Scene scene = new Scene(root, BOARD_WIDTH, BOARD_HEIGHT);

        primaryStage.setTitle("Stratopolis");
        primaryStage.setWidth(415);
        primaryStage.setHeight(200);

        primaryStage.sizeToScene();
        primaryStage.setScene(scene);
        primaryStage.show();

        greenScore = new Text("1");
        redScore = new Text("1");

        root.getChildren().add(greenScore);
        root.getChildren().add(redScore);

        greenScore.setFont(new Font(20));
        redScore.setFont(new Font(20));

        greenScore.relocate(DECK_COORD_X, DECK_COORD_Y + 50);
        redScore.relocate(DECK_COORD_X + 50, DECK_COORD_Y + 50);


        displayBoard = boardState.GetBoardGroup(SQUARE_SIZE);


        root.getChildren().add(displayBoard);
        displayBoard.relocate((BOARD_WIDTH - SQUARE_SIZE * 26) / 2 - 10, (BOARD_HEIGHT - SQUARE_SIZE * 26 - 50) / 2 - 10);

        InputStream in = null;
        try {
            in = new FileInputStream("src/comp1110.ass2/gui/assets/bensound-goinghigher.mp3");
            AS = new AudioStream(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //creates two new decks
        Deck RDeck = new Deck(Colour.R,DECK_COORD_X, DECK_COORD_Y);
        Deck LDeck = new Deck(Colour.G,DECK_COORD_X + 50, DECK_COORD_Y);


        scene.setOnKeyPressed(event -> {
            if(event.getCharacter().equals("m")) {
                if(soundOn) {
                    AudioPlayer.player.stop(AS);
                    soundOn = false;
                } else {
                    AudioPlayer.player.start(AS);
                    soundOn = true;
                }
            }
        });
    }

    public static int getBoardWidth() {
        return BOARD_WIDTH;
    }

    public static int getBoardHeight() {
        return BOARD_HEIGHT;
    }

    public static int getSquareSize() {
        return SQUARE_SIZE;
    }

    public BoardState getBoardState() { return boardState; }


    //A deck consists of a list of tiles. It has a subclass FXDraggablePiece which is essentially an imageview that
    //can be dragged around by clicking and dragging. If the image is placed in a valid position on the board,
    //it updates the boardstate to include the new piece, and makes it the opponents turn. Code for this
    // was inspired by the drag and drop code used in assignment 1

    public class Deck extends ImageView {

        private char currentPieceOrientation;
        private final int boardCoordX = (BOARD_WIDTH - SQUARE_SIZE * 26) / 2 - 10;
        private final int boardCoordY = (BOARD_HEIGHT - SQUARE_SIZE * 26 - 50) / 2 - 10;
        private char currentPieceType;
        private String toString;
        private char[] pieceArray;
        private boolean green;
        //private Board board;

        private static final String URI_BASE = "gui/assets/";

        private int homeX, homeY;
        private final int SIZE_OF_DECK = 46; // this is not the number of pieces in the deck, it is the physical size of the icon

        //the draggable part of the deck - this class was inspired by the draggable functionality implemented
        //in the source code for assignment one

        private class FXDraggablePiece extends ImageView {
            //the mouse coordinates X and Y
            double mouseX, mouseY;

            FXDraggablePiece (char pieceType, double size, int x, int y) {
                //retrieves the image for the piece and then sets the image for the deck, for the user to drag
                //to the board
                Image deckImage = new Image(BoardState.class.getResource(URI_BASE + pieceType + ".png").toString());
                setImage(deckImage);
                root.getChildren().add(this);

                setLayoutX(homeX);
                setLayoutY(homeY);

                setFitHeight(size);
                setFitWidth(size);

                // scroll to change orientation
                setOnScroll(event -> {
                    if (greensTurn == green) {
                        double rotation = this.getRotate();
                        //rotates the piece by adding 90 to the rotation and taking %360 to deal
                        //with rotations over 360 degrees
                        this.setRotate((rotation + 90) % 360);
                        if (getRotate() == 0) {
                            currentPieceOrientation = 'A';
                        } else if (getRotate() == 90) {
                            currentPieceOrientation = 'B';
                        } else if (getRotate() == 180) {
                            currentPieceOrientation = 'C';
                        } else if (getRotate() == 270) {
                            currentPieceOrientation = 'D';
                        }
                    }
                    event.consume();
                });
                // mouse press indicates begin of drag. Here we update the coordinates of mouseX and MouseY
                // so that they correspond to the piece
                setOnMousePressed(event -> {
                    if (green == greensTurn) {
                        mouseX = event.getSceneX();
                        mouseY = event.getSceneY();
                    }

                });
                // Here we update the coordinates of the FXDraggablePiece by tracking it
                // with the mouse coordinates so the user can drag it directly onto the board
                setOnMouseDragged(event -> {
                    //check
                    if (green == greensTurn) {
                        double movementX = event.getSceneX() - mouseX;
                        double movementY = event.getSceneY() - mouseY;
                        setLayoutX(getLayoutX() + movementX);
                        setLayoutY(getLayoutY() + movementY);

                        mouseX = event.getSceneX();
                        mouseY = event.getSceneY();
                    }
                    event.consume();
                });
                //code to place the piece - the mouse button has been released
                setOnMouseReleased(event -> {
                    //check whose turn it is
                    if (green == greensTurn) {
                        //these calculations were inspired by assignment 1
                        int xDrop = (int) this.getLayoutX();
                        int yDrop = (int) this.getLayoutY();
                        int squareX = (int) (xDrop - boardCoordX) / 23;
                        int squareY = (int) (yDrop - boardCoordY) / 23;


                        //coordinate characters A-Z that will form part of the placement
                        char yLetter;
                        char xLetter;
                        //quick check as to whether the user has placed the square on the board
                        if (squareX < 26 && 0 <= squareX && 0 <= squareY && squareY < 26) {
                            //this switch ensures that the pieces are placed correctly given their orientation,
                            //which may cause the player to drag them onto the board slightly differently
                            switch (currentPieceOrientation) {

                                case 'A':
                                    break; //nothing to be done

                                case 'B':
                                    squareX++;
                                    break;
                                case 'C':
                                    squareX++;
                                    squareY++;
                                    break;
                                case 'D':
                                    squareY++;
                                    break;
                            }

                            xLetter = (char) ('A' + squareX);
                            yLetter = (char) ('A' + squareY);

                            String newPiece = "" + xLetter + yLetter + currentPieceType + currentPieceOrientation;

                            if (boardState.IsValidMove(newPiece)) {
                                //update the placement on the board
                                addPlacement(newPiece);
                                if (pieceArray.length > 1) {

                                    //take the piece that has been placed out of the piece array that can
                                    pieceArray = Arrays.copyOfRange(pieceArray, 1, pieceArray.length);
                                    currentPieceType = pieceArray[0];

                                    //updates the image of the board, since the placement has been updated
                                    this.setImage(new Image(BoardState.class.getResource(URI_BASE + currentPieceType + ".png").toString()));


                                } else {
                                    this.setImage(null);

                                    if (!green) {
                                        // game over case
                                    }
                                }

                                //update score boxes
                                greenScore.setText("" + boardState.BoardScore(true));
                                redScore.setText("" + boardState.BoardScore(false));

                                greensTurn = !greensTurn;


                            }
                        }
                        //return the image to its usual orientation
                        this.setRotate(0);
                        currentPieceOrientation = 'A';
                        //return the draggable piece to its home
                        setLayoutX(homeX);
                        setLayoutY(homeY);
                    }
                    event.consume();
                });
            }
        }

        public char getCurrentPiece() {
            return pieceArray[0];
        }

       // public Deck(Colour alignment, int x, int y) {

       // }

        public Deck(Colour alignment, int x, int y) {
            char[] deck;
            homeX = x;
            homeY = y;

            green = Colour.G == alignment;

            //generate a new deck
            if (!green) {
                deck = new char[] {'A','B','C','D','E','F','G','H','I','J','A','B','C','D','E','F','G','H','I','J'};
            } else  {
                deck = new char[] {'K','L','M','N','O','P','Q','R','S','T','K','L','M','N','O','P','Q','R','S','T'};
            }

            //shuffle the deck
            pieceArray = shuffle(deck);

            currentPieceOrientation = 'A';
            currentPieceType = pieceArray[0];
            new FXDraggablePiece(currentPieceType,SIZE_OF_DECK,x,y);

            //return deckFX(SIZE_OF_DECK, homeX, homeY);

        }

        //algorithm that shuffles the deck
        private char[] shuffle(char[] list) {

            Random random = new Random();

            for(int i = list.length - 1; i >= 0; i--) {
                int rand = random.nextInt(i + 1);

                char temp = list[rand];
                list[rand] = list[i];
                list[i] = temp;

                //char elem1 =  list[i];
                //char elem2 =  list[rand];
                //list[rand] = elem1;
                //list[i] = elem2;
            }


            return list;
        }
    }


    //----------------------------------------------------------------------------------------------------
    //AI code begins here
    //----------------------------------------------------------------------------------------------------

    public class EasyPlayer {
        //keeps track of whether this particular instance of AI is playing as red or as green
        private boolean redIsPlaying;

        public EasyPlayer(boolean redIsPlaying) {
            redIsPlaying = redIsPlaying;
        }

        //gets the best move through what is essentially a 1-recursion-depth minimax algorithm
        public String getBestMove(BoardState board, char deckPiece) {
            //generates all possible boards that could come from moves that are available to the AI
            ArrayList<BoardState> possibleBoards = generateNextBoards(board,redIsPlaying,deckPiece);
            BoardState bestBoard = possibleBoards.get(0);
            int moveNumber = 0;
            //looks through the boards, evaluating each with a static evaluation function
            for(int i = 0; i<possibleBoards.size();i++) {
                if(evaluateBoard(bestBoard, redIsPlaying)<evaluateBoard(possibleBoards.get(i),redIsPlaying)) {
                    bestBoard = possibleBoards.get(i);
                    moveNumber = i;
                }
            }
            //finally, takes the index of the best move that is found
            return StratoGame.generateAllPossibleMoves(board,redIsPlaying,deckPiece).get(moveNumber);
        }
        //generates an arraylist of boards
        private ArrayList<BoardState> generateNextBoards(BoardState board, boolean isRedsTurn, char deckPiece) {
            ArrayList<BoardState> toReturn = new ArrayList<>();
            ArrayList<String> movesList = StratoGame.generateAllPossibleMoves(board,isRedsTurn,deckPiece);
            //maps out all the possible moves
            for(int i = 0; i<movesList.size();i++) {
                if (board.IsValidMove(movesList.get(i))) {
                    board.PlaceTile(movesList.get(i));
                    toReturn.add(board);
                }
            }
            return toReturn;
        }


    }
    public class MediumPlayer{

        private boolean redIsPlaying;

        public MediumPlayer(boolean redIsPlaying) {
            redIsPlaying = redIsPlaying;
        }


        public String getBestMove(BoardState board, char deckPiece, char opponentDeckPiece) {
            //generates all possible boards that could come from moves that are available to the AI
            ArrayList<BoardState> possibleBoards = generateNextBoards(board,redIsPlaying,deckPiece);
            BoardState bestBoard = possibleBoards.get(0);
            int moveNumber = 0;
            //looks through the boards, evaluating each with a static evaluation function
            for(int i = 0; i<possibleBoards.size();i++) {
                //test which baord is best, and then find the best one
                if(evaluateTree(bestBoard, redIsPlaying, deckPiece, opponentDeckPiece) < evaluateTree(possibleBoards.get(i), redIsPlaying, deckPiece, opponentDeckPiece)) {
                    bestBoard = possibleBoards.get(i);
                    moveNumber = i;
                }
            }
            //finally, takes the index of the best move that is found
            return StratoGame.generateAllPossibleMoves(board,redIsPlaying,deckPiece).get(moveNumber);
        }

        private ArrayList<BoardState> generateNextBoards(BoardState board, boolean isRedsTurn, char deckPiece) {
            ArrayList<BoardState> toReturn = new ArrayList<>();
            ArrayList<String> movesList = StratoGame.generateAllPossibleMoves(board,isRedsTurn,deckPiece);

            for(int i = 0; i<movesList.size();i++) {
                if (board.IsValidMove(movesList.get(i))) {
                    board.PlaceTile(movesList.get(i));
                    toReturn.add(board);
                }
            }
            return toReturn;
        }


        //simple minimax algorithm
        public int evaluateTree(BoardState board, boolean isRedsTurn, char playersDeckPiece, char opponentsDeckPiece) {
            //generate the first tier boards
            int maxi = 0;

                ArrayList secondTierBoards = generateNextBoards(board,isRedsTurn,playersDeckPiece);
                //here update second tier boards by itterating through all the possibilities
                for(int j = 0;j<secondTierBoards.size();j++) {
                    //find the maximum value that occurs here
                    int boardValue = evaluateBoard((BoardState) secondTierBoards.get(j),redIsPlaying);
                    if(boardValue>maxi) {
                        maxi = boardValue;
                    }
                }

            return maxi; //the maximum value that was found


        }


    }





    /*public class ImpossiblePlayer {
        private boolean redIsPlaying;
        private final int LOOKAHEAD = 2;
        public ImpossiblePlayer(boolean redIsPlaying) {
            this.redIsPlaying = redIsPlaying;
        }
        public double valueOfBestMove(BoardState board,int lookahead) {
            return evaluateTree(new BoardState(board.GetBoard() + getBestMove(board,lookahead)),lookahead,redIsPlaying);
        }
        public String getBestMove(BoardState board,int lookahead) {
            ArrayList<BoardState> possibleBoards = generateNextBoards(board,redIsPlaying);
            BoardState bestBoard = possibleBoards.get(0);
            int moveNumber = 0;
            for(int i = 0; i<possibleBoards.size();i++) {
                if(evaluateTree(bestBoard, lookahead, redIsPlaying)<evaluateTree(possibleBoards.get(i), lookahead, redIsPlaying)) {
                    bestBoard = possibleBoards.get(i);
                    moveNumber = i;
                }
            }
            return StratoGame.generateAllPossibleMoves(board,redIsPlaying).get(moveNumber);
        }

        //returns a 4 character piece which is the best piece next to be given to the computer
        public String findBestNextPiece(String ownPiece, String oppositionPiece, BoardState ) {

        }

        public int evaluateTree(BoardState board, int lookahead,boolean isRedsTurn) {
            if(lookahead>0) {
                ArrayList<BoardState> boards = generateNextBoards(board, isRedsTurn);
                int[] nextEvals = new int[boards.size()];
                for(int i = 0; i<nextEvals.length; i++) {
                    nextEvals[i] = evaluateTree(boards.get(i),lookahead-1,!isRedsTurn);
                }
                if(isRedsTurn && redIsPlaying || !isRedsTurn && !redIsPlaying) {
                    //maximise
                    return maximum(nextEvals);
                } else {
                    //minimise
                    return minimum(nextEvals);
                }

            } else {

                return evaluateBoard(board,isRedsTurn);

            }
        }

        private ArrayList<BoardState> generateNextBoards(BoardState board, boolean isRedsTurn) {
            ArrayList<BoardState> toReturn = new ArrayList<>();
            ArrayList<String> movesList = StratoGame.generateAllPossibleMoves(board,isRedsTurn);
            for(String i:movesList) {
                if (board.IsValidMove(i)) {
                    board.PlaceTile(i);
                    toReturn.add(board);
                }
            }
            return toReturn;
        }

    }*/
    private int getMaxIndex(int[] ints) {
        int maxIndex = 0;
        for (int i = 0; i<ints.length; i++) {
            if (ints[maxIndex] < ints[i]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    private int maximum(int[] ints) {
        int max = ints[0];
        for (int anInt : ints) {
            if (max < anInt) {
                max = anInt;
            }
        }
        return max;
    }
    private int minimum(int[] ints) {
        int min = ints[0];
        for (int anInt : ints) {
            if (min > anInt) {
                min = anInt;
            }
        }
        return min;
    }

    private int evaluateBoard(BoardState board, boolean isRedsTurn) {
        return board.BoardScore(!isRedsTurn);
    }

}
