package comp1110.ass2;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.Random;
import java.util.Arrays;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;



/**
 * Created by Tom on 20/08/2016.
 */

//A deck consists of a list of tiles,

public class Deck {

    private char currentPieceOrientation;
    private char currentPieceType;
    private String toString;
    private char[] pieceArray;

    private static final String URI_BASE = "gui/assets/";

    private int homeX, homeY;


    class FXDraggablePiece extends ImageView {

        double mouseX, mouseY;


        FXDraggablePiece (char pieceType, double size, int x, int y) {
            System.out.println(URI_BASE + pieceType + ".png");
            setImage(new Image(BoardState.class.getResource(URI_BASE + pieceType + ".png").toString()));
            setFitHeight(size);
            setFitWidth(size);
            setOnScroll(event -> {// scroll to change orientation
                rotate();
                event.consume();
            });
            setOnMousePressed(event -> {      // mouse press indicates begin of drag
                mouseX = event.getSceneX();
                mouseY = event.getSceneY();
            });
            setOnMouseDragged(event -> {      // mouse is being dragged
                toFront();
                double movementX = event.getSceneX() - mouseX;
                double movementY = event.getSceneY() - mouseY;
                setLayoutX(getLayoutX() + movementX);
                setLayoutY(getLayoutY() + movementY);
                mouseX = event.getSceneX();
                mouseY = event.getSceneY();
                event.consume();
            });
            setOnMouseReleased(event -> {     // drag is complete
               // snapToGrid
                setLayoutX(homeX);
                setLayoutY(homeY);


            });
        }
    }









    //create a javafx object

    public Deck(Colour alignment, int x, int y) {
        char[] deck;

        //generate a new deck
        if (alignment == Colour.R) {
            deck = new char[] {'A','B','C','D','E','F','G','H','I','J'};
        } {
            deck = new char[] {'K','L','M','N','O','P','Q','R','S','T'};
        }
        //shuffle the deck

        pieceArray = shuffle(deck);

        currentPieceOrientation = 'A';
        currentPieceType = pieceArray[0];

        



    }

    public void getNextPiece() {

       //occurs when the next piece needs to be shown
        // set currentPiece


    }

    public void drop() {

        pieceArray = Arrays.copyOfRange(pieceArray, 1, pieceArray.length);
        currentPieceType = pieceArray[0];

        //place piece and get next piece

    }

    public void rotate() {
        switch(currentPieceOrientation){
            case 'A':
                currentPieceOrientation ='B';
                break;
            case 'B':
                currentPieceOrientation ='C';
                break;
            case 'C':
                currentPieceOrientation ='D';
                break;
            case 'D':
                currentPieceOrientation ='A';
                break;
        }


    }

    private char[] shuffle(char[] list) {

        Random random = new Random();

        for(int i = 0; i<list.length; i++) {
            int rand = random.nextInt(list.length);

            char elem1 =  list[i];
            char elem2 =  list[rand];
            list[rand] = elem1;
            list[i] = elem2;


        }


        return list;
    }



}
