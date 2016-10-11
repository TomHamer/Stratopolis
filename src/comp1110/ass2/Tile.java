package comp1110.ass2;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Created by calum on 8/15/2016.
 */

// Represents a single board tile.

public class Tile {
    private int height;
    private Colour alignment;
    private static final String URI_BASE = "gui/assets/";

    public Tile () {
        height = 0;
        alignment = Colour.W;
    }

    public void Stack(Colour c) {
        height++;
        alignment = c;
    }

    public Colour Alignment() {
        return alignment;
    }

    public int Height() {
        return height;
    }

    // Returns a JavaFX Group which represents the tile
    public Group TileFX (double size) {
        if (alignment == Colour.W) {return null;}

        Group toReturn = new Group();
        toReturn.getChildren().add(new Square(alignment, size));
        if (height > 1) {
            Label l = new Label("" + height);

            l.setTranslateX(9 * size / 30);
            l.setFont(new Font(0.75*size));
            if (alignment == Colour.N) {
                l.setTextFill(Color.GRAY);
            } else {
                l.setTextFill(Color.BLACK);
            }
            toReturn.getChildren().add(l);
        }


        return toReturn;
    }

    // Essentially taken from Board.java from assignment 1. Used in TileFX.
    class Square extends ImageView {
        Square (Colour colour, double size) {
            String col = "";
            switch (colour) {
                case R:
                    col = "Red";
                    break;

                case G:
                    col = "Green";
                    break;

                case N:
                    col = "Black";
                    break;
            }
            setImage(new Image(BoardState.class.getResource(URI_BASE + col + ".png").toString()));
            setFitHeight(size);
            setFitWidth(size);
        }
    }
}
