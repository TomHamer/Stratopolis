package comp1110.ass2.gui;

import comp1110.ass2.BoardState;
import comp1110.ass2.StratoGame;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
//import comp1110.ass2.Deck;
import comp1110.ass2.Colour;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Collections;
import java.util.Random;
import java.util.Arrays;

public class Board extends Application {


    private static final int BOARD_WIDTH = 933;
    private static final int BOARD_HEIGHT = 700;
    private static final int STAGE_HEIGHT = 700;
    private static final int STAGE_WIDTH = 700;
    private static final int SQUARE_SIZE = 23;
    private static final int DECK_COORD_X = 50;
    private static final int DECK_COORD_Y = 50;
    private final BoardState boardState = new BoardState("MMUA");
    private Group root = new Group();
    private Group current = null;
    private Group displayBoard;
    private Text greenScore, redScore;
    private boolean greensTurn = true;




    // FIXME Task 8: Implement a basic playable Strato Game in JavaFX that only allows pieces to be placed in valid places

    // FIXME Task 9: Implement scoring

    // FIXME Task 11: Implement a game that can play valid moves (even if they are weak moves)

    // FIXME Task 12: Implement a game that can play good moves

    public void addPlacement(String placement) {
        boardState.UpdateBoardGroup(displayBoard, SQUARE_SIZE, placement);

        //root.getChildren().remove(displayBoard);
        //boardState.PlaceTile(placement);

        //displayBoard = boardState.GetBoardGroup(SQUARE_SIZE);

        //displayBoard.relocate((BOARD_WIDTH - SQUARE_SIZE * 26) / 2 - 10,(BOARD_HEIGHT - SQUARE_SIZE * 26 - 50) / 2 - 10);

        //root.getChildren().add(displayBoard);
        //displayBoard.toBack();
    }



    @Override
    public void start(Stage primaryStage) throws Exception {

        Deck RDeck = new Deck();
        Deck LDeck = new Deck();

        Scene scene = new Scene(root,BOARD_WIDTH,BOARD_HEIGHT);

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

        //boardState = new BoardState("MMUA");

        displayBoard = boardState.GetBoardGroup(SQUARE_SIZE);


        root.getChildren().add(displayBoard);
        displayBoard.relocate((BOARD_WIDTH - SQUARE_SIZE * 26) / 2 - 10,(BOARD_HEIGHT - SQUARE_SIZE * 26 - 50) / 2 - 10);

        root.getChildren().add(RDeck.makeDeck(Colour.G,DECK_COORD_X,DECK_COORD_Y));
        root.getChildren().add(LDeck.makeDeck(Colour.R,DECK_COORD_X + 50,DECK_COORD_Y));
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

    public BoardState getBoardState() {return boardState;}


    /**
     * Created by Tom on 20/08/2016.
     */

//A deck consists of a list of tiles,

    public class Deck {

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


        private ImageView deckFX(double size, int x, int y) { return new FXDraggablePiece(currentPieceType,size,x,y); }

        private class FXDraggablePiece extends ImageView {

            double mouseX, mouseY;

            //doesnt deal with orientation yet


            FXDraggablePiece (char pieceType, double size, int x, int y) {
                System.out.println(URI_BASE + pieceType + ".png");
                setImage(new Image(BoardState.class.getResource(URI_BASE + pieceType + ".png").toString()));
                setLayoutX(homeX);
                setLayoutY(homeY);
                setFitHeight(size);
                setFitWidth(size);
                setOnScroll(event -> {// scroll to change orientation
                    if (greensTurn == green) {
                        double rotation = this.getRotate();
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
                setOnMousePressed(event -> {

                    // mouse press indicates begin of drag
                    if (green == greensTurn) {
                        mouseX = event.getSceneX();
                        mouseY = event.getSceneY();
                    }

                });
                setOnMouseDragged(event -> {      // mouse is being dragged
                    //toFront();
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
                setOnMouseReleased(event -> {     // drag is complete
                    if (green == greensTurn) {
                        int xDrop = (int) this.getLayoutX();
                        int yDrop = (int) this.getLayoutY();
                        int squareX = (int) (xDrop - boardCoordX) / 23;
                        int squareY = (int) (yDrop - boardCoordY) / 23;


                        // snapToGrid
                        char yLetter = 'A';
                        char xLetter = 'A';


                        System.out.println("Attempted to place " + currentPieceType + " in " + currentPieceOrientation
                                + " orientation " + " at " + "(" + xDrop + "," + yDrop + ")");


                        if (squareX < 26 && 0 <= squareX && 0 <= squareY && squareY < 26) {
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

                                addPlacement(newPiece);
                                if (pieceArray.length > 1) {
                                    pieceArray = Arrays.copyOfRange(pieceArray, 1, pieceArray.length);
                                    currentPieceType = pieceArray[0];

                                    this.setImage(new Image(BoardState.class.getResource(URI_BASE + currentPieceType + ".png").toString()));
                                    System.out.println("successfully placed a piece!");
                                } else {
                                    this.setImage(null);

                                    if (!green) {
                                        // game over case
                                    }
                                }

                                greenScore.setText("" + boardState.BoardScore(true));
                                redScore.setText("" + boardState.BoardScore(false));

                                greensTurn = !greensTurn;


                            }
                        }

                        this.setRotate(0);
                        currentPieceOrientation = 'A';

                        setLayoutX(homeX);
                        setLayoutY(homeY);
                    }
                    event.consume();
                });
            }
        }

        //create a javafx object

        public ImageView makeDeck(Colour alignment, int x, int y) {
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

            return deckFX(SIZE_OF_DECK, homeX, homeY);

        }

        public void getNextPiece() {

            //occurs when the next piece needs to be shown
            // set currentPiece


        }

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

}
