package comp1110.ass2.gui;

import comp1110.ass2.*;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableBooleanValue;
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
import  sun.audio.*;
import java.io.*;
import javafx.animation.FadeTransition;
import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;

import static comp1110.ass2.gui.Board.PlayerMode.Human;
import static comp1110.ass2.gui.Board.PlayerMode.Easy;
import static comp1110.ass2.gui.Board.PlayerMode.Medium;
import static comp1110.ass2.gui.Board.PlayerMode.Hard;


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
    private Group current = null;
    private Group displayBoard;
    private Text greenScore, redScore;
    private boolean greensTurn = true;
    private AudioStream AS;
    private boolean soundOn = false;
    private Deck RDeck;
    private Deck GDeck;
    private Text remainingG, remainingR;
    private Group hint = null;
    private ToggleGroup greenOptions = new ToggleGroup();
    private ToggleGroup redOptions   = new ToggleGroup();
    private int boardIndex;


    private BooleanProperty gameStarted = new SimpleBooleanProperty(false);

    private PlayerMode redMode = Human, greenMode = Human;



    enum PlayerMode {
        Human, Easy, Medium, Hard
    }

    public void addPlacement(String placement) {
        boardState.UpdateBoardGroup(displayBoard, SQUARE_SIZE, placement);
    }

    // FIXME For Jingyi: Implement a system that uses the following functions, writing to the "savedGame.txt" to save files
    //clears the text file
    public void newGame() {
    }
    //writes the text file
    public void saveGame() {
        //need to save

        //types of the players, out of EasyPlayer, MediumPlayer, HardPlayer, Human player

        //whos turn it is

        //placement on board

    }
    public void loadGame() {
        boolean redWasEasy;
        boolean greenWasEasy;
        boolean redWasMedium;
        boolean greenWasMedium;
        boolean redWasImpossible;
        boolean greenWasImpossible;
        boolean redWasHuman;
        boolean greenWasHuman;
        String placement = "";
        boolean redsTurn;

        //assign the above values through reading in the text file

        boardState = new BoardState(placement);
    }

    //allows the user to save the game by writing the gameState into a textfile
    public String boardToText() {
        //this will need - placement, piece arrays for both decks, whether the two are AI or human and their difficulty
        return null;
    }

    public void start(Stage primaryStage) {


        Scene scene = new Scene(root, BOARD_WIDTH, BOARD_HEIGHT, Color.BLACK);

        primaryStage.setTitle("Stratopolis");
        primaryStage.setWidth(415);
        primaryStage.setHeight(200);
        primaryStage.setX(250);
        primaryStage.setY(100);

        primaryStage.sizeToScene();
        primaryStage.setScene(scene);
        primaryStage.show();

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

        startGame.setOnMouseReleased(event -> {
            gameStarted.setValue(true);
            greenMode = (PlayerMode) greenOptions.getSelectedToggle().getUserData();
            redMode   = (PlayerMode) redOptions.getSelectedToggle().getUserData();

            root.getChildren().add(displayBoard);
            boardIndex = root.getChildren().indexOf(displayBoard);
            displayBoard.relocate((BOARD_WIDTH - SQUARE_SIZE * 26) / 2 - 10, (BOARD_HEIGHT - SQUARE_SIZE * 26 - 50) / 2 - 10);

            //creates two new decks based on what the player wants
            RDeck = new Deck(Colour.R,DECK_COORD_R_X, DECK_COORD_R_Y,false); // the red deck
            GDeck = new Deck(Colour.G,DECK_COORD_G_X, DECK_COORD_G_Y,false); // the green deck

            greenScore.setText("Score: 1");
            redScore.setText("Score: 1");

            remainingG = new Text(DECK_COORD_G_X - SQUARE_SIZE * 1.2, DECK_COORD_G_Y - SQUARE_SIZE / 3, "Pieces Remaining: 20");
            remainingG.setFont(new Font(11));
            root.getChildren().add(remainingG);

            remainingR = new Text(DECK_COORD_R_X - SQUARE_SIZE * 1.2, DECK_COORD_R_Y - SQUARE_SIZE / 3, "Pieces Remaining: 20");
            remainingR.setFont(new Font(11));
            root.getChildren().add(remainingR);

            play_n_sample(1);
        });

        root.getChildren().add(startGame);
        root.getChildren().addAll(new RadioButton[] {humanG, easyG, mediumG, hardG, humanR, easyR, mediumR, hardR});

        // FIXME For Calum: Implement this so it all works and the user selects it

        //player selects what kind of game they want

        //Do they want AI?
        boolean leftBotIsAI = false;
        boolean rightBotIsAI = false;

        //for now set both true

        //how hard should the AI be? Easy, medium or impossible






        // FIXME For Jingyi: this sound system now works - suggest another idea?

        //It needs to be such that when the user presses 'M' the music starts, and then when the
        //user presses 'M' again the music stops

        //creates a new input stream for sound system

        final AudioClip in = new AudioClip(Board.class.getResource("assets/bensound-goinghigher.mp3").toString());


        //event handler for sound system
        scene.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.M) {
                if(soundOn) {
                    in.stop();
                    soundOn = false;

                } else {
                    PrintWriter writer = null;
                    try {
                        writer = new PrintWriter(new FileWriter(String.valueOf(Board.class.getResource("assets/Samples.txt")), true));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    in.play();
                    soundOn = true;
                }
            }
        });
    }

    public void hideHint() {
        hint = null; // clear the hint group
    }
    // shows a hint, given by an easy AI
    public void showHint(boolean forRedPlayer) {
        hideHint();
        if(hint ==null) {
            EasyPlayer ep = new EasyPlayer(forRedPlayer);
            String moveToShow;

            if (forRedPlayer) {
                moveToShow = ep.getBestMove(boardState, RDeck.getCurrentPiece());
            } else {
                moveToShow = ep.getBestMove(boardState, GDeck.getCurrentPiece());
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
        private char[] pieceArray;
        private boolean green;
        private boolean isAI;
        private FXDraggablePiece icon;
        private int piecesMarker = 0;

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


            if (piecesMarker < 19) {

                //take the piece that has been placed out of the piece array that can

                addPlacement(newPiece);
                System.out.println("added "+ newPiece);
                piecesMarker++;
                currentPieceType = pieceArray[piecesMarker];
                if (green) {
                    remainingG.setText("Pieces Remaining: " + (20 - piecesMarker));
                } else {
                    remainingR.setText("Pieces Remaining: " + (20 - piecesMarker));
                }

                icon.setImage(new Image(BoardState.class.getResource(URI_BASE + currentPieceType + ".png").toString()));
                //this.setImage(new Image(BoardState.class.getResource(URI_BASE + currentPieceType + ".png").toString()));


            } else {

                addPlacement(newPiece);
                icon.setImage(null);

                if (!green) {
                    // game over case

                }
            }

            //update score boxes
            greenScore.setText("Score: " + boardState.BoardScore(true));
            redScore.setText("Score: " + boardState.BoardScore(false));

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
            return pieceArray[piecesMarker];
        }

        public boolean getAI() { return isAI;}


        Deck(Colour alignment, int x, int y, boolean isAi) {
            char[] deck;
            homeX = x;
            homeY = y;
            isAI = isAi;

            green = Colour.G == alignment;

            //generate a new deck
            if (!green) {
                deck = new char[] {'A','B','C','D','E','F','G','H','I','J','A','B','C','D','E','F','G','H','I','J'};
            } else  {
                deck = new char[] {'K','L','M','N','O','P','Q','R','S','T','K','L','M','N','O','P','Q','R','S','T'};
            }

            //shuffle the deck
            pieceArray = shuffle(deck);

            //make the piece at the front of the deck appear on screen by initialising a new FXDraggablePiece
            currentPieceOrientation = 'A';
            currentPieceType = pieceArray[0];
            icon = new FXDraggablePiece(currentPieceType,SIZE_OF_DECK,x,y);
        }
    }
    //algorithm that shuffles the deck'
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


    public class HardPlayer {
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
            int moveNumber = 0;
            for (int i = 0; i < possibleBoards.size(); i++) {
                int testValue = alphaBeta(possibleBoards.get(i), -1000, 1000, MAX_LOOKAHEAD, !redIsPlaying);
                if (bestBoardVal < testValue)  {
                    bestBoardVal = testValue;
                    moveNumber = i;
                }
            }
            return board.generateAllPossibleMoves(redIsPlaying, currentDeckPiece).get(moveNumber);
        }


        //minimax alpha-beta algorithm
        private int alphaBeta(BoardState board, int alpha, int beta, int lookahead, boolean maximiseForRed) {
            int bestValue;
            ArrayList<String> movesList;



            if(lookahead == MAX_LOOKAHEAD) {
                movesList = board.generateAllPossibleMoves(maximiseForRed, opponentDeckPiece);
            } else {
                if(maximiseForRed) {
                    movesList = board.generateAllPossibleMoves(true, RDeck.getPieceArray()[MAX_LOOKAHEAD-1]);
                } else {
                    movesList = board.generateAllPossibleMoves(false, GDeck.getPieceArray()[MAX_LOOKAHEAD-1]);
                }
            }
            if (lookahead == 0) {
                bestValue = evaluateBoard(board,maximiseForRed);
            }
            else if (maximiseForRed) {
                bestValue = alpha;

                for (int i=0; i<movesList.size(); i++) {
                    BoardState tBoard = new BoardState(board.GetBoard()); // initialise a new board
                    tBoard.PlaceTile(movesList.get(i));
                    int childValue = alphaBeta(tBoard, bestValue, beta, lookahead-1, false);
                    bestValue = Math.max(bestValue, childValue);
                    if (beta <= bestValue) {
                        break;
                    }
                }
            }
            else {
                bestValue = beta;

                for (int i=0; i<movesList.size(); i++) {
                    BoardState tBoard = new BoardState(board.GetBoard()); // initialise a new board
                    tBoard.PlaceTile(movesList.get(i));
                    int childValue = alphaBeta(tBoard, alpha, bestValue,lookahead-1, true);
                    bestValue = Math.min(bestValue, childValue);
                    if (bestValue <= alpha) {
                        break;
                    }
                }
            }
            return bestValue;
        }



        private ArrayList<BoardState> generateNextBoards(BoardState board, boolean isRedsTurn, char deckPiece) {
            ArrayList<BoardState> toReturn = new ArrayList<>();
            ArrayList<String> movesList = board.generateAllPossibleMoves(isRedsTurn, deckPiece);

            for (String s : movesList) {
                if (board.IsValidMove(s)) {
                    BoardState tBoard = new BoardState(board.GetBoard()); // initialise a new board
                    tBoard.PlaceTile(s);
                    toReturn.add(tBoard);
                }
            }

            return toReturn;
        }

        private int evaluateBoard(BoardState board, boolean isRedsTurn) {
            return board.BoardScore(!isRedsTurn)-board.BoardScore(isRedsTurn);
        }


    }
    //plays games over and over again and prints them to the log
    private void play_n_sample(int numberOfGames) {
            //sets up the AI based on what the player wants
            EasyPlayer ep1 = new EasyPlayer(true);
            //HardPlayer hp = new HardPlayer(false);
            EasyPlayer ep2 = new EasyPlayer(false);
            MediumPlayer mp = new MediumPlayer(true);
            MediumPlayer mp2 = new MediumPlayer(true);
            NN1HL n = new NN1HL(8,676,1,0.001);
            HardPlayer hp = new HardPlayer(false);
            IntelligentPlayer ip = new IntelligentPlayer(n);
            MonteCarloPlayer mcp = new MonteCarloPlayer(false);

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

                    GDeck.placePiece(hp.getBestMove(boardState,GDeck.getCurrentPiece(),RDeck.getCurrentPiece()));
                    boards.add(boardState.GetBoard());
                    RDeck.placePiece(mp.getBestMove(boardState, RDeck.getCurrentPiece(),GDeck.getCurrentPiece()));
                    boards.add(boardState.GetBoard());

                    System.out.println("Red's score is currently "+boardState.BoardScore(true));
                    System.out.println("Green's score is currently "+boardState.BoardScore(false));

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


}
