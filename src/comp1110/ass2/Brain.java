package comp1110.ass2;

/**
 * Created by Tom on 9/09/2016.
 */
public abstract class Brain {

    public boolean redIsPlaying;


    abstract public String getBestMove(BoardState board,int lookahead);

}
