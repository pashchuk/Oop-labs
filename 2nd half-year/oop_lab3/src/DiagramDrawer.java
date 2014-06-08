import javax.swing.*;
import java.awt.*;

/**
 * Created by mamaxropela on 5/6/14.
 */
public class DiagramDrawer extends JComponent {
    private Sector[] sectors;
    private CSVProcessor processor;
    private JTable table;
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillArc(10,10,100,100,0,50);
    }
    public DiagramDrawer(CSVProcessor proc){
        this.processor = proc;
        table = new JTable(3,3);
        table.setRowHeight(30);
        table.setBounds(10,120,200,100);
    }
    public JTable getTable(){
        return this.table;
    }
}
