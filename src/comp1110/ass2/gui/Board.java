package comp1110.ass2.gui;

import comp1110.ass2.AI.*;
import comp1110.ass2.AI.NeuralNetworkFiles.IntelligentPlayer;
import comp1110.ass2.AI.NeuralNetworkFiles.NN1HL;
import comp1110.ass2.BoardState;
import comp1110.ass2.Colour;
import comp1110.ass2.Pieces;
import comp1110.ass2.Tile;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.concurrent.Task;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.*;
import javafx.animation.FadeTransition;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.FutureTask;

import static comp1110.ass2.gui.Board.PlayerMode.Human;
import static comp1110.ass2.gui.Board.PlayerMode.Easy;
import static comp1110.ass2.gui.Board.PlayerMode.Medium;
import static comp1110.ass2.gui.Board.PlayerMode.Hard;

/*
    Developed jointly by Tom Hamer and Calum Snowdon.
    Calum created the UI and handled the logical flow of the game (making it work)
    Tom created the AI and the draggable icons
 */

public class Board extends Application {

    private static final int BOARD_WIDTH = 933;
    private static final int BOARD_HEIGHT = 700;
    private static final int SQUARE_SIZE = 23;
    private static final int DECK_COORD_G_X = 55;
    private static final int DECK_COORD_G_Y = 50;
    private static final int DECK_COORD_R_X = BOARD_WIDTH - 101;
    private static final int DECK_COORD_R_Y = 50;
    private BoardState boardState = new BoardState("MMUA");
    private Group root = new Group();
    private Group displayBoard; // Graphical representation of the board
    private Group titleScreen = new Group(), overScreen = new Group(); // Title screen visible upon opening the game
    private Text overText = new Text(), overBody = new Text(); // Title revealed on game over
    private Text greenScore, redScore; // Text objects to display each player's score
    private boolean greensTurn = true; // Used to keep track of whose turn it is
    private boolean soundOn = false; // Used to keep track of whether or not music is playing
    private Deck RDeck, GDeck; // Used to represent each player's deck
    private Text remainingG, remainingR; // Text showing how many pieces each player has left
    private Group hint = null;
    private ToggleGroup greenOptions = new ToggleGroup(); // Contains radio buttons to represent settings
    private ToggleGroup redOptions   = new ToggleGroup();
    private int boardIndex; // Used to keep track of position of displayBoard within root
    private Player greenAI, redAI; // Used to make moves for AI players when necessary

    private BooleanProperty gameStarted = new SimpleBooleanProperty(false); // Used to toggle access to settings for when game is not running

    private PlayerMode redMode = Human, greenMode = Human; // Used to keep track of what type of Player each colour is

    enum PlayerMode {
        Human, Easy, Medium, Hard // Used to allow for different types of AIs and players
    }

    public void start(Stage primaryStage) {

        Scene scene = new Scene(root, BOARD_WIDTH, BOARD_HEIGHT, Color.BLACK);

        primaryStage.setTitle("Stratopolis");
        primaryStage.setX(250);
        primaryStage.setY(100);

        primaryStage.sizeToScene();
        primaryStage.setScene(scene);
        primaryStage.show();

        Platform.setImplicitExit(true);

        // ----- Large section initializing UI ----- //

        // Creates the rectangles in the background
        for (double x = -100; x < BOARD_WIDTH; x += 300) {
            for (double y = 0; y < BOARD_HEIGHT; y += 140) {
                Rectangle toAdd = new Rectangle(x - 150 * (((int) (y / 140)) % 2) + 3, y + 3, 294, 134);
                toAdd.setFill(Color.DIMGRAY);
                root.getChildren().add(toAdd);
            }
        }

        greenScore = new Text();
        redScore = new Text();

        Rectangle greenBox = new Rectangle(DECK_COORD_G_X - SQUARE_SIZE * 1.5, DECK_COORD_G_Y - SQUARE_SIZE, SQUARE_SIZE * 5, 220);
        greenBox.setArcHeight(15);
        greenBox.setArcWidth(15);
        greenBox.setStroke(Color.BLACK);
        greenBox.setFill(Color.DARKGREEN);
        greenBox.setStrokeWidth(3);
        root.getChildren().add(greenBox);

        Rectangle redBox = new Rectangle(DECK_COORD_R_X - SQUARE_SIZE * 1.5, DECK_COORD_R_Y - SQUARE_SIZE, SQUARE_SIZE * 5, 220);
        redBox.setArcHeight(15);
        redBox.setArcWidth(15);
        redBox.setStroke(Color.BLACK);
        redBox.setFill(Color.DARKRED);
        redBox.setStrokeWidth(3);
        root.getChildren().add(redBox);

        root.getChildren().add(greenScore);
        root.getChildren().add(redScore);

        greenScore.setFont(new Font(18));
        redScore.setFont(new Font(18));

        greenScore.relocate(DECK_COORD_G_X - SQUARE_SIZE / 2, DECK_COORD_G_Y + SQUARE_SIZE * 2.5);
        redScore.relocate(DECK_COORD_R_X - SQUARE_SIZE / 2, DECK_COORD_R_Y + SQUARE_SIZE * 2.5);

        displayBoard = boardState.GetBoardGroup(SQUARE_SIZE);
        root.getChildren().add(displayBoard);
        displayBoard.setVisible(false);

        // This section creates the radio buttons which allow player options to be selected
        // Referenced http://docs.oracle.com/javafx/2/ui_controls/radio-button.htm while creating
        RadioButton humanG = new RadioButton();
        humanG.setToggleGroup(greenOptions);
        humanG.setText("Human");
        humanG.setUserData(Human);
        humanG.disableProperty().bind(gameStarted);
        humanG.relocate(DECK_COORD_G_X - SQUARE_SIZE, DECK_COORD_G_Y + SQUARE_SIZE * 4);
        humanG.setSelected(true);

        RadioButton humanR = new RadioButton();
        humanR.setToggleGroup(redOptions);
        humanR.setText("Human");
        humanR.setUserData(Human);
        humanR.disableProperty().bind(gameStarted);
        humanR.relocate(DECK_COORD_R_X - SQUARE_SIZE, DECK_COORD_R_Y + SQUARE_SIZE * 4);
        humanR.setSelected(true);

        RadioButton easyG = new RadioButton();
        easyG.setToggleGroup(greenOptions);
        easyG.setText("Easy AI");
        easyG.disableProperty().bind(gameStarted);
        easyG.relocate(DECK_COORD_G_X - SQUARE_SIZE, DECK_COORD_G_Y + SQUARE_SIZE * 5);
        easyG.setUserData(Easy);

        RadioButton easyR = new RadioButton();
        easyR.setToggleGroup(redOptions);
        easyR.setText("Easy AI");
        easyR.disableProperty().bind(gameStarted);
        easyR.relocate(DECK_COORD_R_X - SQUARE_SIZE, DECK_COORD_R_Y + SQUARE_SIZE * 5);
        easyR.setUserData(Easy);

        RadioButton mediumG = new RadioButton();
        mediumG.setToggleGroup(greenOptions);
        mediumG.setText("Medium AI");
        mediumG.disableProperty().bind(gameStarted);
        mediumG.relocate(DECK_COORD_G_X - SQUARE_SIZE, DECK_COORD_G_Y + SQUARE_SIZE * 6);
        mediumG.setUserData(Medium);

        RadioButton mediumR = new RadioButton();
        mediumR.setToggleGroup(redOptions);
        mediumR.setText("Medium AI");
        mediumR.disableProperty().bind(gameStarted);
        mediumR.relocate(DECK_COORD_R_X - SQUARE_SIZE, DECK_COORD_R_Y + SQUARE_SIZE * 6);
        mediumR.setUserData(Medium);

        RadioButton hardG = new RadioButton();
        hardG.setToggleGroup(greenOptions);
        hardG.setText("Hard AI");
        hardG.disableProperty().bind(gameStarted);
        hardG.relocate(DECK_COORD_G_X - SQUARE_SIZE, DECK_COORD_G_Y + SQUARE_SIZE * 7);
        hardG.setUserData(Hard);

        RadioButton hardR = new RadioButton();
        hardR.setToggleGroup(redOptions);
        hardR.setText("Hard AI");
        hardR.disableProperty().bind(gameStarted);
        hardR.relocate(DECK_COORD_R_X - SQUARE_SIZE, DECK_COORD_R_Y + SQUARE_SIZE * 7);
        hardR.setUserData(Hard);

        Button startGame = new Button();
        startGame.setText("Begin Game");
        startGame.disableProperty().bind(gameStarted);
        startGame.relocate(DECK_COORD_R_X - SQUARE_SIZE, BOARD_HEIGHT - 50);

        root.getChildren().add(startGame);
        root.getChildren().addAll(humanG, easyG, mediumG, hardG, humanR, easyR, mediumR, hardR);

        Rectangle titleBack = new Rectangle((BOARD_WIDTH - 400) / 2, BOARD_HEIGHT / 4, 400, 300);
        titleBack.setArcWidth(15);
        titleBack.setArcHeight(15);
        titleBack.setFill(Color.GRAY);
        titleBack.setStrokeWidth(3);
        titleBack.setStroke(Color.BLACK);
        titleScreen.getChildren().add(titleBack);

        Text titleText = new Text("Welcome to Stratopolis!");
        titleText.setFont(new Font(26));
        titleText.relocate((BOARD_WIDTH - titleText.getLayoutBounds().getWidth()) / 2, BOARD_HEIGHT / 3);
        titleScreen.getChildren().add(titleText);

        Text titleBody = new Text("- Use the radio buttons at the side to set up the game\n" +
                "- Press M to play music\n" +
                "- Press \"Start Game\" to begin\n");
        titleBody.setWrappingWidth(360);
        titleBody.relocate((BOARD_WIDTH - 360) / 2, BOARD_HEIGHT / 3 + 50);
        titleBody.setFont(new Font(16));
        titleScreen.getChildren().add(titleBody);

        Rectangle overBack = new Rectangle((BOARD_WIDTH - 400) / 2, SQUARE_SIZE * 26 - 120, 400, 200);
        overBack.setArcHeight(15);
        overBack.setArcWidth(15);
        overBack.setFill(Color.LIGHTGRAY);
        overBack.setStrokeWidth(3);
        overBack.setStroke(Color.BLACK);
        overScreen.getChildren().add(overBack);

        overText.setFont(new Font(36));
        overBody.setFont(new Font(16));
        overScreen.getChildren().addAll(overText, overBody);

        root.getChildren().add(titleScreen);
        root.getChildren().add(overScreen);
        overScreen.setVisible(false);

        remainingG = new Text();
        remainingR = new Text();
        root.getChildren().add(remainingG);
        root.getChildren().add(remainingR);

        // Sets up board when user requests game to start
        startGame.setOnMouseReleased(event -> {
            boardState = new BoardState("MMUA");
            root.getChildren().remove(displayBoard);

            overScreen.setVisible(false);

            displayBoard = boardState.GetBoardGroup(SQUARE_SIZE);
            root.getChildren().add(displayBoard);

            titleScreen.setVisible(false);

            gameStarted.setValue(true);
            greenMode = (PlayerMode) greenOptions.getSelectedToggle().getUserData();
            redMode   = (PlayerMode) redOptions.getSelectedToggle().getUserData();

            boardIndex = root.getChildren().indexOf(displayBoard);
            displayBoard.relocate((BOARD_WIDTH - SQUARE_SIZE * 26) / 2 - 10, (BOARD_HEIGHT - SQUARE_SIZE * 26 - 50) / 2 - 10);

            greenScore.setText("Score: 1");
            redScore.setText("Score: 1");

            remainingG.setText("Pieces Remaining: 20");
            remainingG.setFont(new Font(11));
            remainingG.relocate(DECK_COORD_G_X - SQUARE_SIZE * 1.2, DECK_COORD_G_Y - SQUARE_SIZE / 3 - 10);

            remainingR.setText("Pieces Remaining: 20");
            remainingR.setFont(new Font(11));
            remainingR.relocate(DECK_COORD_R_X - SQUARE_SIZE * 1.2, DECK_COORD_R_Y - SQUARE_SIZE / 3 - 10);

            switch (redMode) {
                case Human:
                    RDeck = new Deck(Colour.R,DECK_COORD_R_X, DECK_COORD_R_Y,false);
                    break;

                case Easy:
                    redAI = new EasyPlayer(true);
                    RDeck = new Deck(Colour.R,DECK_COORD_R_X, DECK_COORD_R_Y,true);
                    break;

                case Medium:
                    redAI = new MediumPlayer(true);
                    RDeck = new Deck(Colour.R,DECK_COORD_R_X, DECK_COORD_R_Y,true);
                    break;

                case Hard:
                    redAI = new HardPlayer(true);
                    RDeck = new Deck(Colour.R,DECK_COORD_R_X, DECK_COORD_R_Y,true);
                    break;
            }

            switch (greenMode) {
                case Human:
                    GDeck = new Deck(Colour.G,DECK_COORD_G_X, DECK_COORD_G_Y,false);
                    break;

                case Easy:
                    greenAI = new EasyPlayer(false);
                    GDeck = new Deck(Colour.G,DECK_COORD_G_X, DECK_COORD_G_Y,true);
                    GDeck.AIPlace(greenAI.getBestMove(boardState, GDeck.getCurrentPiece(), RDeck.getCurrentPiece()));
                    break;

                case Medium:
                    greenAI = new MediumPlayer(false);
                    GDeck = new Deck(Colour.G,DECK_COORD_G_X, DECK_COORD_G_Y,true);
                    GDeck.AIPlace(greenAI.getBestMove(boardState, GDeck.getCurrentPiece(), RDeck.getCurrentPiece()));
                    break;

                case Hard:
                    greenAI = new HardPlayer(false);
                    GDeck = new Deck(Colour.G,DECK_COORD_G_X, DECK_COORD_G_Y,true);
                    GDeck.AIPlace(greenAI.getBestMove(boardState, GDeck.getCurrentPiece(), RDeck.getCurrentPiece()));
                    break;
            }
        });

        // Allow for music to be played when M is pressed
        final AudioClip in = new AudioClip(Board.class.getResource("assets/bensound-goinghigher.mp3").toString());

        //event handler for sound system
        scene.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.M) {
                if(soundOn) {
                    in.stop();
                    soundOn = false;
                } else {
                    in.play();
                    soundOn = true;
                }
            }
        });
    }

    private void hideHint() {
        hint = null; // clear the hint group
    }
    // shows a hint, given by an easy AI
    public void showHint(boolean forRedPlayer) {
        hideHint();
        if(hint ==null) {
            EasyPlayer ep = new EasyPlayer(forRedPlayer);
            String moveToShow;

            if (forRedPlayer) {
                moveToShow = ep.getBestMove(boardState, RDeck.getCurrentPiece(), GDeck.getCurrentPiece());
            } else {
                moveToShow = ep.getBestMove(boardState, GDeck.getCurrentPiece(), RDeck.getCurrentPiece());
            }

            //initialise a new hint
            hint = new Group();

            Pieces piece = new Pieces(moveToShow);

            for (int i = 0; i < 3; i++) {
                int x = piece.coords[i][0];
                int y = piece.coords[i][1];

                Colour colour = piece.colours[i];

                Tile newTile = new Tile();
                newTile.Stack(colour);

                Group toAdd = (newTile).TileFX(SQUARE_SIZE);
                toAdd.relocate(x * SQUARE_SIZE, y * SQUARE_SIZE);
                hint.getChildren().add(toAdd);
            }

            //make this object fade out
            root.getChildren().add(hint);
            FadeTransition fade = new FadeTransition(Duration.seconds(6), hint);
            fade.setFromValue(0.7);
            fade.setToValue(0);
            fade.play();
        }
    }

    public void addPlacement(String placement) {
        boardState.UpdateBoardGroup(displayBoard, SQUARE_SIZE, placement);
    }


    //A deck consists of a list of tiles. It has a subclass FXDraggablePiece which is essentially an imageview that
    //can be dragged around by clicking and dragging. If the image is placed in a valid position on the board,
    //it updates the boardstate to include the new piece, and makes it the opponents turn. Code for this
    // was inspired by the drag and drop code used in assignment 1

    public class Deck {

        private char currentPieceOrientation;
        private final int boardCoordX = (BOARD_WIDTH - SQUARE_SIZE * 26) / 2 - 10;
        private final int boardCoordY = (BOARD_HEIGHT - SQUARE_SIZE * 26 - 50) / 2 - 10;
        private char currentPieceType;
        private char[] pieceArray;
        private boolean green;
        private boolean isAI;
        private FXDraggablePiece icon;
        private int piecesMarker = 0;

        private static final String URI_BASE = "gui/assets/";

        private int homeX, homeY;
        private final int SIZE_OF_DECK = 46; // this is not the number of pieces in the deck, it is the physical size of the icon

        char[] getPieceArray() {return pieceArray;}

        int getPiecesMarker() {return piecesMarker;}

        // Called by AIs to place pieces with animation
        // http://docs.oracle.com/javafx/2/animations/basics.htm was referenced
        // in the creation of this method, in particular to figure out how to write animations
        void AIPlace(String newPiece) {
            // Used to offset the translation animation to account for rotation
            int xOff = 0, yOff = 0;

            if (newPiece.charAt(3) < 'D' && newPiece.charAt(3) > 'A') {
                xOff = -SQUARE_SIZE;
            }
            if (newPiece.charAt(3) > 'B') {
                yOff = -SQUARE_SIZE;
            }

            icon.setOpacity(0.5);
            TranslateTransition translation = new TranslateTransition(Duration.millis(1000), icon);
            translation.setToX((BOARD_WIDTH - SQUARE_SIZE * 26) / 2 + (newPiece.charAt(0) - 'A') * SQUARE_SIZE - homeX + xOff);
            translation.setToY((BOARD_HEIGHT - SQUARE_SIZE * 26 - 50) / 2 + (newPiece.charAt(1) - 'A') * SQUARE_SIZE - homeY + yOff);

            RotateTransition rotation = new RotateTransition(Duration.millis(400), icon);
            rotation.setByAngle((newPiece.charAt(3) - 'A') * 90);

            ParallelTransition move = new ParallelTransition();
            move.getChildren().addAll(translation, rotation);
            // Updates the board once animation is finished
            move.setOnFinished(event -> {icon.setOpacity(1); placePiece(newPiece); icon.setRotate(0); icon.setTranslateX(0); icon.setTranslateY(0);});

            move.play();
        }

        // Places a piece on the board and changes the active player
        void placePiece(String newPiece) {
            // Computations are performed in a separate thread so that UI may be updated
            // while computations are occurring. Referenced http://stackoverflow.com/questions/26554814/javafx-updating-gui
            // for this section.
            Task<Void> task = new Task<Void>() {
                @Override
                public Void call() throws Exception {
                    piecesMarker++;
                    if (piecesMarker < 20) {
                        currentPieceType = pieceArray[piecesMarker];

                        // Sections which update the UI are given to the main
                        // thread to be executed concurrently
                        FutureTask<Void> UpdateUI = new FutureTask<>(() -> {
                            addPlacement(newPiece);
                            icon.setImage(new Image(BoardState.class.getResource(URI_BASE + currentPieceType + ".png").toString()));
                        }, null);

                        Platform.runLater(UpdateUI);
                        UpdateUI.get();
                    } else {
                        FutureTask<Void> UpdateUI = new FutureTask<>(() -> {
                            addPlacement(newPiece);
                            icon.setImage(null);
                        }, null);

                        Platform.runLater(UpdateUI);
                        UpdateUI.get();

                        // When red plays last piece, end of game is triggered
                        // Section brings up statement of results and allows new game to be started
                        if (!green) {
                            FutureTask<Void> EndGame = new FutureTask<>(() -> {
                                overScreen.toFront();
                                overScreen.setVisible(true);

                                if (boardState.BoardScore(true) > boardState.BoardScore(false)) {
                                    overText.setText("Green Wins!");
                                    overText.setFill(Color.DARKGREEN);
                                    overText.setStrokeWidth(1);
                                    overText.setStroke(Color.BLACK);
                                    overText.relocate((BOARD_WIDTH - overText.getLayoutBounds().getWidth()) / 2,
                                            SQUARE_SIZE * 26 - 100);
                                } else if (boardState.BoardScore(false) > boardState.BoardScore(true)) {
                                    overText.setText("Red Wins!");
                                    overText.setFill(Color.DARKRED);
                                    overText.setStrokeWidth(1);
                                    overText.setStroke(Color.BLACK);
                                    overText.relocate((BOARD_WIDTH - overText.getLayoutBounds().getWidth()) / 2,
                                            SQUARE_SIZE * 26 - 100);
                                } else if (boardState.BreakTie()) {
                                    overText.setText("Green Wins!");
                                    overText.setFill(Color.DARKGREEN);
                                    overText.setStrokeWidth(1);
                                    overText.setStroke(Color.BLACK);
                                    overText.relocate((BOARD_WIDTH - overText.getLayoutBounds().getWidth()) / 2,
                                            SQUARE_SIZE * 26 - 100);
                                } else {
                                    overText.setText("Red Wins!");
                                    overText.setFill(Color.DARKRED);
                                    overText.setStrokeWidth(1);
                                    overText.setStroke(Color.BLACK);
                                    overText.relocate((BOARD_WIDTH - overText.getLayoutBounds().getWidth()) / 2,
                                            SQUARE_SIZE * 26 - 100);
                                }

                                overBody.setText("                       Final Scores:\n           " +
                                        "    Green: " + boardState.BoardScore(true) + "      Red: " + boardState.BoardScore(false) + "\n" +
                                        "Use radio buttons to set up next game \n" +
                                        "      Press \"Start Game\" to play again");
                                overBody.relocate((BOARD_WIDTH - overBody.getLayoutBounds().getWidth()) / 2, SQUARE_SIZE * 26 - 40);

                                gameStarted.set(false);
                            }, null);
                            Platform.runLater(EndGame);
                            EndGame.get();
                        }
                    }

                    FutureTask<Void> UpdateUI = new FutureTask<>(() -> {
                        greenScore.setText("Score: " + boardState.BoardScore(true));
                        redScore.setText("Score: " + boardState.BoardScore(false));

                        if (green) {
                            remainingG.setText("Pieces Remaining: " + (20 - piecesMarker));
                        } else {
                            remainingR.setText("Pieces Remaining: " + (20 - piecesMarker));
                        }
                    }, null);

                    Platform.runLater(UpdateUI);
                    UpdateUI.get();

                    greensTurn = !greensTurn;
                    // Automates the AI players
                    if (greensTurn && GDeck.getAI() && GDeck.piecesMarker < 20) {
                        GDeck.AIPlace(greenAI.getBestMove(boardState, GDeck.getCurrentPiece(), RDeck.getCurrentPiece()));
                    } else if (!greensTurn && RDeck.getAI() && RDeck.piecesMarker < 20) {
                        RDeck.AIPlace(redAI.getBestMove(boardState, RDeck.getCurrentPiece(), GDeck.getCurrentPiece()));
                    }

                    return null;
                }
            };
            new Thread(task).start();
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
                        setOpacity(0.5);
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
                    setOpacity(1.0);
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

        char getCurrentPiece() {
            if (piecesMarker < 20) {
                return pieceArray[piecesMarker];
            } else {
                // Invalid character, will cause operations which require
                // a piece to finish up
                return 'n';
            }
        }

        boolean getAI() { return isAI;}

        Deck(Colour alignment, int x, int y, boolean isAi) {
            char[] deck;
            homeX = x;
            homeY = y;
            isAI = isAi;

            green = Colour.G == alignment;

            //create an array for the deck
            if (!green) {
                deck = new char[] {'A','B','C','D','E','F','G','H','I','J','A','B','C','D','E','F','G','H','I','J'};
            } else  {
                deck = new char[] {'K','L','M','N','O','P','Q','R','S','T','K','L','M','N','O','P','Q','R','S','T'};
            }

            pieceArray = shuffle(deck);

            //make the piece at the front of the deck appear on screen by initialising a new FXDraggablePiece
            currentPieceOrientation = 'A';
            currentPieceType = pieceArray[0];
            icon = new FXDraggablePiece(currentPieceType,SIZE_OF_DECK,x,y);
        }
    }

    //algorithm that shuffles a list
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


    //HardPlayer is a 3 lookhead minimax algorithm with alpha beta pruning. It is an inner class of the board,
    //as it needs to access the next deck piece coming up, unlike the other bots.
    public class HardPlayer implements Player {
        boolean redIsPlaying;
        char opponentDeckPiece;
        private final int MAX_LOOKAHEAD = 2;

        public HardPlayer(boolean redIsPlaying) {
            this.redIsPlaying = redIsPlaying;
        }

        public String getBestMove(BoardState board, char currentDeckPiece, char opponentDeckPiece) {
            this.opponentDeckPiece = opponentDeckPiece;
            ArrayList<BoardState> possibleBoards = generateNextBoards(board, redIsPlaying, currentDeckPiece);
            BoardState bestBoard = possibleBoards.get(0);
            int bestBoardVal = alphaBeta(bestBoard, -1000, 1000, MAX_LOOKAHEAD, !redIsPlaying);
            int moveNumber = 0; //keeps track of the index of the best move, so it can be returned
            for (int i = 0; i < possibleBoards.size(); i++) {
                int testValue = alphaBeta(possibleBoards.get(i), -1000, 1000, MAX_LOOKAHEAD, !redIsPlaying);
                //since we are taking alpha-beta of "!redIsPlaying" we have to minimise on this node.
                //so bestboardval is the minimum
                if (bestBoardVal > testValue)  {
                    bestBoardVal = testValue;
                    moveNumber = i;
                }
            }
            bestBoard = possibleBoards.get(moveNumber);
            return bestBoard.GetBoard().substring(bestBoard.GetBoard().length() - 4);
        }

        //minimax alpha-beta algorithm
        private int alphaBeta(BoardState board, int alpha, int beta, int lookahead, boolean maximiseForRed) {
            int bestValue;
            ArrayList<String> movesList;

            if (lookahead == 0  || gameOverQuery(board)) { //if we run out of lookaheads or the game is over we simply evaluate the board
                bestValue = evaluateBoard(board, maximiseForRed);
            } else {
                if (lookahead == MAX_LOOKAHEAD) { //if we are on the first lookahead, use the opponent deck piece
                    movesList = board.generateAllPossibleMoves(maximiseForRed, opponentDeckPiece);
                } else {
                    if (maximiseForRed) {
                        //here is where the bot cheats: it looks ahead through the moves that it knows it will be able to make, given the deckpiece its opponent cant see
                        movesList = board.generateAllPossibleMoves(true, RDeck.getPieceArray()[RDeck.getPiecesMarker() + 1]);
                    } else {
                        movesList = board.generateAllPossibleMoves(false, GDeck.getPieceArray()[GDeck.getPiecesMarker() + 1]);
                    }
                }
                if (maximiseForRed) {
                    //set the bestvalue to our alpha (since it is the best value discovered)
                    bestValue = alpha;

                    //iterate through the moves list
                    for (String aMove : movesList) {
                        //generate a new boardstate to test the move (so we dont change the actual board)
                        BoardState tBoard = new BoardState(board.GetBoard());
                        //place a tile on the board
                        tBoard.PlaceTile(aMove);
                        //find the alpha beta of the child value
                        int childValue = alphaBeta(tBoard, bestValue, beta, lookahead - 1, false);
                        bestValue = Math.max(bestValue, childValue);
                        if (beta <= bestValue) {
                            break;
                        }
                    }
                } else {
                    bestValue = beta;

                    for (String aMove : movesList) {
                        BoardState tBoard = new BoardState(board.GetBoard());
                        tBoard.PlaceTile(aMove);
                        int childValue = alphaBeta(tBoard, alpha, bestValue, lookahead - 1, true); //call alphabeta again with the new parameters
                        bestValue = Math.min(bestValue, childValue);
                        if (bestValue <= alpha) {
                            break;
                        }
                    }
                }
            }
            return bestValue;

        }

        private ArrayList<BoardState> generateNextBoards(BoardState board, boolean isRedsTurn, char deckPiece) {
            ArrayList<BoardState> toReturn = new ArrayList<>();
            ArrayList<String> movesList = board.generateAllPossibleMoves(isRedsTurn, deckPiece);
            for (String s : movesList) {
                    BoardState tBoard = new BoardState(board.GetBoard()); // initialise a new board
                    tBoard.PlaceTile(s);
                    toReturn.add(tBoard);
            }

            return toReturn;
        }

        private int evaluateBoard(BoardState board, boolean isRedsTurn) {
            return board.BoardScore(!isRedsTurn)-board.BoardScore(isRedsTurn);
        }


    }
    //plays games over and over again and prints them to the log
    //used this for machine learning extension
    private void play_n_sample(int numberOfGames) {
            //sets up the AI based on what the player wants
            EasyPlayer ep1 = new EasyPlayer(true);
            //HardPlayer hp = new HardPlayer(false);
            EasyPlayer ep2 = new EasyPlayer(false);
            MediumPlayer mp = new MediumPlayer(true);
            MediumPlayer mp2 = new MediumPlayer(false);
            NN1HL n = new NN1HL(8,676,1,0.001);
            HardPlayer hp = new HardPlayer(false);
            IntelligentPlayer ip = new IntelligentPlayer(n);
            MonteCarloPlayer mcp = new MonteCarloPlayer(true);
            RandomPlayer rand = new RandomPlayer(true);

            // int NO_OF_GAMES = 1;
            for (int i = 0; i < numberOfGames; i++) {
                boardState = new BoardState("MMUA");
                root.getChildren().remove(boardIndex);
                displayBoard = boardState.GetBoardGroup(SQUARE_SIZE);
                displayBoard.relocate((BOARD_WIDTH - SQUARE_SIZE * 26) / 2 - 10, (BOARD_HEIGHT - SQUARE_SIZE * 26 - 50) / 2 - 10);
                root.getChildren().add(displayBoard);
                boardIndex = root.getChildren().indexOf(displayBoard);

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

    private boolean gameOverQuery(BoardState board) {
        return board.GetBoard().length()==164; //a boardstate after a complete game has length 168
    }
}