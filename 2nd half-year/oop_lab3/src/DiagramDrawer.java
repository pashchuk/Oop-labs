import javax.swing.*;
import java.awt.*;

/**
 * Created by root on 6/7/14.
 */
public class DiagramDrawer extends JComponent {
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillArc(10,10,100,100,0,50);
    }
}
