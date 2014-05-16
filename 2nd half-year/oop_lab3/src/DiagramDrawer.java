import javax.swing.*;
import java.awt.*;

/**
 * Created by Eduard on 5/15/2014.
 */
public class DiagramDrawer extends JPanel {
    public void draw(Graphics g){
        g.setColor(new Color(120,110,80));
        g.fillArc(100,100,50,50,0,110);
    }

    @Override
    public void paintComponent(Graphics g){
        ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
        ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        draw(g);
    }
}
