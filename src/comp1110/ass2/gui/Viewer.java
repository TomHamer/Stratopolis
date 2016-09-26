package comp1110.ass2.gui;

import comp1110.ass2.BoardState;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;



import static comp1110.ass2.StratoGame.isPlacementValid;

/**
 * A very simple viewer for piece placements in the link game.
 *
 * NOTE: This class is separate from your main game class.  This
 * class does not play a game, it just illustrates various piece
 * placements.
 */
public class Viewer extends Application {

    /* board layout */
    private static final int VIEWER_WIDTH = 750;
    private static final int VIEWER_HEIGHT = 700;
    private static final int SQUARE_SIZE = 23;

    private final Group root = new Group();
    private final Group controls = new Group();
    TextField textField;

    private Group current = null;


    /**
     * Draw a placement in the window, removing any previously drawn one
     *
     * @param placement  A valid placement string
     */
    void makePlacement(String placement) {
        root.getChildren().remove(current);
        BoardState board = new BoardState(placement);

        current = board.GetBoardGroup(SQUARE_SIZE);

        root.getChildren().add(current);
        current.relocate((VIEWER_WIDTH - SQUARE_SIZE * 26) / 2 - 10,(VIEWER_HEIGHT - SQUARE_SIZE * 26 - 50) / 2 - 10);

        System.out.println(board.BoardScore(true));
        // FIXME Task 5: implement the simple placement viewer
    }

    /**
     * Create a basic text field for input and a refresh button.
     */
    private void makeControls() {
        Label label1 = new Label("Placement:");
        textField = new TextField ();
        textField.setPrefWidth(300);
        Button button = new Button("Refresh");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                makePlacement(textField.getText());
                textField.clear();
            }
        });
        HBox hb = new HBox();
        hb.getChildren().addAll(label1, textField, button);
        hb.setSpacing(10);
        hb.setLayoutX(130);
        hb.setLayoutY(VIEWER_HEIGHT - 50);
        controls.getChildren().add(hb);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("StratoGame Viewer");
        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);

        root.getChildren().add(controls);
        makeControls();

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
