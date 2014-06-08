import com.sun.prism.*;

import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 * Created by mamaxropela on 5/6/14.
 */
public class DiagramDrawer extends JComponent {
    private ArrayList<Sector[]> sectors;
    private int[][] data;
    private final Color[] colors = {
            Color.green,Color.blue,
            Color.cyan,Color.orange,
            Color.red};
    private int colorCount;

    private CSVProcessor processor;
    private JTable table;
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLUE);
        g.fillArc(10,10,100,100,0,50);
        //draw(g);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.fillArc(10,10,100,100,0,50);
        draw(g);
    }
    pai

    public DiagramDrawer(CSVProcessor proc){
        this.processor = proc;
        table = new JTable(3,3);
        table.setRowHeight(30);
        table.setBounds(10,120,200,100);
        initialise();
    }
    public JTable getTable(){
        return this.table;
    }
    private void initialise(){
        colorCount = 0;
        sectors = new ArrayList<>();
        String[][] csvTable = processor.getData();
        data = new int[csvTable.length][csvTable[0].length];
        for(int i = 0; i < csvTable.length; i++){
            sectors.add(new Sector[csvTable[0].length]);
            for(int j = 0; j < csvTable[0].length; j++)
                sectors.get(i)[j] = new Sector(Integer.parseInt(csvTable[i][j]),getNewColor());
                //data[i][j] = Integer.parseInt(csvTable[i][j]);
        }
    }
    private void draw(Graphics g){
        int x = 10, y = 10, width = 300, height = 300,
                startAngle = 0, endAngle = 0, maxValue = 0;
        Sector currSector;
        for(int j = 0; j < sectors.get(0).length; j++)
            maxValue += sectors.get(0)[j].getValue();
        for(int i = 0; i < sectors.get(0).length; i++){
            currSector = sectors.get(0)[i];
            endAngle = (int)(currSector.getValue()*10/maxValue/36) + startAngle;
            g.setColor(currSector.getColor());
            g.fillArc(x,y,width,height,startAngle,endAngle);
            startAngle = endAngle;
        }
    }
    private Color getNewColor(){
        if (colorCount>4)
            colorCount = 0;
        return colors[colorCount++];
    }
}
