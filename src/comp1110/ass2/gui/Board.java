package comp1110.ass2.gui;

import comp1110.ass2.*;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.animation.*;
import  java.io.*;

import javafx.util.Duration;
import  sun.audio.*;
import java.io.File;
import java.util.concurrent.TimeUnit;
import javafx.animation.FadeTransition;

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
    private Deck RDeck;
    private Deck GDeck;
    private Group hint = new Group();




    public void addPlacement(String placement) {
        boardState.UpdateBoardGroup(displayBoard, SQUARE_SIZE, placement);
    }


    // FIXME For Jingyi: Implement a system that uses the following functions, writing to the "savedGame.txt" to save files
    //clears the text file
    public void newGame() {}
    //writes the text file
    public void saveGame() {}

    //allows the user to save the game by writing the gameState into a textfile
    public String boardToText() {
        //this will need - placement, piece arrays for both decks, whether the two are AI or human and their difficulty
        return null;
    }


    public void start(Stage primaryStage) {

        //move to a different "game setup function"?
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


        // FIXME For Calum: Implement this so it all works and the user selects it

        //player selects what kind of game they want

        //Do they want AI?
        boolean leftBotIsAI;
        boolean rightBotIsAI;

        //for now set both true
        leftBotIsAI = true;
        rightBotIsAI = true;

        //how hard should the AI be? Easy, medium or impossible

        //creates two new decks based on what the player wants
        RDeck = new Deck(Colour.R,DECK_COORD_X, DECK_COORD_Y,leftBotIsAI); // the red deck
        GDeck = new Deck(Colour.G,DECK_COORD_X + 50, DECK_COORD_Y,rightBotIsAI); // the green deck


        if(leftBotIsAI && rightBotIsAI) {
            //sets up the AI based on what the player wants
            EasyPlayer ep1 = new EasyPlayer(true);
            MediumPlayer mp = new MediumPlayer(false);
            EasyPlayer ep2 = new EasyPlayer(false);


            for (int piecesPlayed = 0; piecesPlayed < 10; piecesPlayed++) {
                //pair[0] for the green player
                //pair[1] for the red player
                RDeck.placePiece(ep1.getBestMove(boardState, RDeck.getCurrentPiece()));
                GDeck.placePiece(ep2.getBestMove(boardState, GDeck.getCurrentPiece()));


            }
        }




        // FIXME For Jingyi: make this sound system work

        //It needs to be such that when the user presses 'M' the music starts, and then when the
        //user presses 'M' again the music stops

        //creates a new input stream for sound system
        InputStream in = null;
        try {
            in = new FileInputStream(Board.class.getResource("assets/bensound-goinghigher.mp3").toString());
            AS = new AudioStream(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //event handler for sound system
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

    public void hideHint() {

        hint = new Group(); // clear the hint group

    }
    // shows a hint, given by an easy AI
    public void showHint(boolean forRedPlayer) {

        EasyPlayer ep = new EasyPlayer(forRedPlayer);
        String moveToShow;

        if (forRedPlayer) {
            moveToShow = ep.getBestMove(boardState, RDeck.getCurrentPiece());
        } else {
            moveToShow = ep.getBestMove(boardState, GDeck.getCurrentPiece());
        }

        Pieces piece = new Pieces(moveToShow);

        for (int i = 0; i < 3; i++) {
            int x = piece.coords[i][0];
            int y = piece.coords[i][1];

            Group toAdd = (new Tile()).TileFX(SQUARE_SIZE);
            toAdd.relocate(x * SQUARE_SIZE, y * SQUARE_SIZE);
            root.getChildren().add(toAdd);
            hint.getChildren().add(toAdd);


            //make this hint object flash - this javafx code was inspired by
            // http://stackoverflow.com/questions/23190049/how-to-make-a-text-content-disappear-after-some-time-in-javafx


            Timeline blink = new Timeline(
                    new KeyFrame(
                            Duration.seconds(0),
                            new KeyValue(
                                    toAdd.opacityProperty(),
                                    1,
                                    Interpolator.DISCRETE
                            )
                    ),
                    new KeyFrame(
                            Duration.seconds(0.5),
                            new KeyValue(
                                    toAdd.opacityProperty(),
                                    0,
                                    Interpolator.DISCRETE
                            )
                    ),
                    new KeyFrame(
                            Duration.seconds(1),
                            new KeyValue(
                                    toAdd.opacityProperty(),
                                    1,
                                    Interpolator.DISCRETE
                            )
                    )
            );

                FadeTransition fade = new FadeTransition(Duration.seconds(2), toAdd);
                fade.setFromValue(1);
                fade.setToValue(0);

                SequentialTransition blinkThenFade = new SequentialTransition(
                        blink,
                        fade
                );


            blink.setCycleCount(3);

                blinkThenFade.play();


        }
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
        private boolean isAI;
        //private Board board;

        private static final String URI_BASE = "gui/assets/";

        private int homeX, homeY;
        private final int SIZE_OF_DECK = 46; // this is not the number of pieces in the deck, it is the physical size of the icon

        public char[] getPieceArray() {
            return pieceArray;
        }



        //UNSAFE, does not check if the piece is placed in a valid position
        private void placePiece(String newPiece) {

            if(isAI) {
                // FIXME For Calum: Implement animation when the AI places a piece
                //animation code here
            }

            hideHint(); // hide the hint

            //update the placement on the board

            addPlacement(newPiece);
            System.out.println("Added " + newPiece);
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
                    if (greensTurn == green && !isAI) { // if the deck is an AI deck then the dragging is not needed
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
                    if (green == greensTurn && !isAI) {
                        mouseX = event.getSceneX();
                        mouseY = event.getSceneY();
                    }

                });
                // Here we update the coordinates of the FXDraggablePiece by tracking it
                // with the mouse coordinates so the user can drag it directly onto the board
                setOnMouseDragged(event -> {
                    //check
                    if (green == greensTurn && !isAI) {

                        //make it so that if the mouse hovers over a valid position the position lights up

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
                    if (green == greensTurn && !isAI) {
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


                                placePiece(newPiece);

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

        public boolean getAI() { return isAI;}


        public Deck(Colour alignment, int x, int y, boolean isAi) {
            char[] deck;
            homeX = x;
            homeY = y;
            isAI = isAi;

            green = Colour.G == alignment;

            //generate a new deck
            if (!green) {
                deck = new char[] {'A','B','C','D','E','F','G','H','I','J'};
            } else  {
                deck = new char[] {'K','L','M','N','O','P','Q','R','S','T'};
            }

            //shuffle the deck
            pieceArray = shuffle(deck);

            //make the piece at the front of the deck appear on screen by initialising a new FXDraggablePiece
            currentPieceOrientation = 'A';
            currentPieceType = pieceArray[0];
            new FXDraggablePiece(currentPieceType,SIZE_OF_DECK,x,y);

        }

        //algorithm that shuffles the deck
        private char[] shuffle(char[] list) {

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


    //----------------------------------------------------------------------------------------------------
    //AI code begins here
    //----------------------------------------------------------------------------------------------------

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
                    BoardState tBoard = new BoardState(board.GetBoard()); // initialise a new board
                    tBoard.PlaceTile(movesList.get(i));
                    toReturn.add(tBoard);
                }
            }
            return toReturn;
        }


    }
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
            return StratoGame.generateAllPossibleMoves(board,redIsPlaying,deckPiece).get(moveNumber);
        }
        //generate the next boards through using the list of moves generated in StratoGame.java
        private ArrayList<BoardState> generateNextBoards(BoardState board, boolean isRedsTurn, char deckPiece) {
            ArrayList<BoardState> toReturn = new ArrayList<>();
            ArrayList<String> movesList = StratoGame.generateAllPossibleMoves(board, isRedsTurn, deckPiece);

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

            ArrayList secondTierBoards = generateNextBoards(board,isRedsTurn,opponentsDeckPiece);
            //here update second tier boards by iterating through all the possibilities
            for(int j = 0;j<secondTierBoards.size();j++) {
                //find the maximum value that occurs here
                int boardValue = evaluateBoard((BoardState) secondTierBoards.get(j),!isRedsTurn);
                if(boardValue<maxi) {
                    maxi = boardValue;
                }
            }

            return maxi; //the maximum value that was found


        }
        private int evaluateBoard(BoardState board, boolean isRedsTurn) {
            return board.BoardScore(!isRedsTurn)-board.BoardScore(isRedsTurn);
        }
    }




    //impossible player does not work yet
    public class ImpossiblePlayer {
        private boolean redIsPlaying;
        private final int LOOKAHEAD = 2;
        private char bestNextPiece;

        public ImpossiblePlayer(boolean redIsPlaying) {
            this.redIsPlaying = redIsPlaying;
        }

        public char getNextDeckPiece(Deck deck) { //works out the next deck piece that would be optimal
            return bestNextPiece;
        }

        //gets the best move
        public String getBestMove(BoardState board,int lookahead, char currentDeckPiece, char opponentDeckPiece) {
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
        public String findBestNextPiece(String ownPiece, String oppositionPiece, BoardState board) {

            //set best next piece
            return "AAAA";
        }
        //lookahead will be 3
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

    }

    //finds the index of the maximum value of a list of ints
    private int getMaxIndex(int[] ints) {
        int maxIndex = 0;
        for (int i = 0; i<ints.length; i++) {
            if (ints[maxIndex] < ints[i]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    //finds the maximum of a list of ints
    private int maximum(int[] ints) {
        int max = ints[0];
        for (int anInt : ints) {
            if (max < anInt) {
                max = anInt;
            }
        }
        return max;
    }
    //finds the minimum of a list of ints
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
        return board.BoardScore(!isRedsTurn)-board.BoardScore(isRedsTurn); // opposite because the parameter is "green"
    }

}
