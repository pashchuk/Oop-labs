import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by mamaxropela on 5/6/14.
 */
public class DiagramDrawer extends JComponent {
    private ArrayList<Sector[]> sectors = null;
    private int[][] data;
    private final Color[] colors = {
            Color.green,Color.blue,
            Color.cyan,Color.orange,
            Color.red};
    private int colorCount;

    private CSVProcessor processor;
    private JTable table;
    boolean b = false;

    public DiagramDrawer(CSVProcessor proc){
        this.processor = proc;
        initialise();
        table = new JTable(sectors.size(),sectors.get(0).length);
        int height = 20*sectors.size();
        table.setRowHeight(20);
        table.setLocation(20,400);
        table.setSize(560,height>160?160:height);
        updateTable();
    }
    public void updateTable(){
        for(int i = 0; i < data.length; i++)
            for(int j = 0; j < data[0].length; j++)
                table.setValueAt(data[i][j],i,j);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
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
            Sector[] local = new Sector[csvTable[0].length];
            for(int j = 0; j < csvTable[0].length; j++){
                local[j] = new Sector(Integer.parseInt(csvTable[i][j]),getNewColor());
                data[i][j] = local[j].getValue();
            }
                //data[i][j] = Integer.parseInt(csvTable[i][j]);
            sectors.add(local);
        }
        b = true;
    }

    private void draw(Graphics g){
        int x = 10, y = 10, width = 200, height = 200,
                startAngle = 0, endAngle = 0, maxValue = 0;
        Sector currSector;
        for(int j = 0; j < sectors.get(0).length; j++)
            maxValue += sectors.get(0)[j].getValue();
        for(int i = 0; i < sectors.get(0).length; i++){
            currSector = sectors.get(0)[i];
            endAngle = (int) ((double) currSector.getValue()/maxValue*360);
            g.setColor(currSector.getColor());
            g.fillArc(x,y,width,height,startAngle,endAngle);
            startAngle += endAngle;
        }
    }

    private Color getNewColor(){
        if (colorCount>4)
            colorCount = 0;
        return colors[colorCount++];
    }
}
