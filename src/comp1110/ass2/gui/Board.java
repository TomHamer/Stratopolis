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
    private BoardState board = new BoardState();



    // FIXME Task 8: Implement a basic playable Strato Game in JavaFX that only allows pieces to be placed in valid places

    // FIXME Task 9: Implement scoring

    // FIXME Task 11: Implement a game that can play valid moves (even if they are weak moves)

    // FIXME Task 12: Implement a game that can play good moves

    @Override
    public void start(Stage primaryStage) throws Exception {

        Group root = new Group();

        Deck RDeck = new Deck();

        root.getChildren().add(RDeck.makeDeck(Colour.R,DECK_COORD_X,DECK_COORD_Y));


        Scene scene = new Scene(root,BOARD_WIDTH,BOARD_HEIGHT);




        primaryStage.setTitle("ImageView");
        primaryStage.setWidth(415);
        primaryStage.setHeight(200);

        primaryStage.sizeToScene();
        primaryStage.setScene(scene);
        primaryStage.show();



    }
}
