import javax.swing.*;
import java.awt.*;

/**
 * Created by root on 6/7/14.
 */
public class DiagramDrawer extends JComponent {
    @Override
    public void paint(Graphics g) {
        g.drawOval(30,30,100,100);
    }
}
