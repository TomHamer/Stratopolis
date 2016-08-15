package comp1110.ass2;

/**
 * Created by calum on 8/15/2016.
 */

// Represents a single board tile.

public class Tile {
    private int height;
    private Colour alignment;

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
}
