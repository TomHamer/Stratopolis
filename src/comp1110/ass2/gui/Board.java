package comp1110.ass2.gui;

import comp1110.ass2.BoardState;
import comp1110.ass2.StratoGame;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import comp1110.ass2.Deck;
import comp1110.ass2.Colour;

public class Board extends Application {


    private static final int BOARD_WIDTH = 933;
    private static final int BOARD_HEIGHT = 700;
    private static final int STAGE_HEIGHT = 700;
    private static final int STAGE_WIDTH = 700;
    private static final int SQUARE_SIZE = 23;
    private static final int DECK_COORD_X = 50;
    private static final int DECK_COORD_Y = 50;
    private BoardState boardState = new BoardState();
    Group root = new Group();
    private Group current = null;
    private Group displayBoard;




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

        primaryStage.setTitle("ImageView");
        primaryStage.setWidth(415);
        primaryStage.setHeight(200);

        primaryStage.sizeToScene();
        primaryStage.setScene(scene);
        primaryStage.show();

        boardState = new BoardState("MMUA");

        displayBoard = boardState.GetBoardGroup(SQUARE_SIZE);


        root.getChildren().add(displayBoard);
        displayBoard.relocate((BOARD_WIDTH - SQUARE_SIZE * 26) / 2 - 10,(BOARD_HEIGHT - SQUARE_SIZE * 26 - 50) / 2 - 10);


        root.getChildren().add(RDeck.makeDeck(this,Colour.R,DECK_COORD_X,DECK_COORD_Y));
        root.getChildren().add(LDeck.makeDeck(this,Colour.G,100,DECK_COORD_Y));


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

}
