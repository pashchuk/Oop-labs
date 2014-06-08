import java.awt.*;

/**
 * Created by mamaxropela on 5/7/14.
 */
public class Sector {
    private Color color;
    private int value;
    public Sector(int value, Color color){
        this.value = value;
        this.color = color;
    }
    public Color getColor(){
        return this.color;
    }
    public int getValue(){
        return this.value;
    }
}
