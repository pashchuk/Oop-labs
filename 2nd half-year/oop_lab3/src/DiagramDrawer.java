import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

/**
 * Created by mamaxropela on 5/6/14.
 */
public class DiagramDrawer extends JComponent {
    private int selectedRow;
    private Point[] points;
    private ArrayList<Sector[]> sectors = null;
    private String[][] data;
    private final Color[] colors = {
            Color.green,Color.blue,
            Color.cyan,Color.orange,
            Color.red};
    private int colorCount;
    private CSVProcessor processor;
    private JTable table;

    public DiagramDrawer(CSVProcessor proc){
        this.processor = proc;
        initialise();
        table = new JTable(sectors.size(),sectors.get(0).length);
        int height = 20*sectors.size();
        table.setRowHeight(20);
        table.setLocation(20, 400);
        table.setSize(560, height > 160 ? 160 : height);
        table.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if ("tableCellEditor".equals(evt.getPropertyName()))
                    if (!table.isEditing()) {
                        int row = table.convertRowIndexToModel(table.getEditingRow());
                        int column = table.convertColumnIndexToModel(table.getEditingColumn());
                        data[row][column] = (String) table.getModel().getValueAt(row, column);
                    }
            }
        });
        points = new Point[data[0].length];
        updateTable();
    }

    public void updateTable(){
        for(int i = 0; i < data.length; i++)
            for(int j = 0; j < data[0].length; j++)
                table.setValueAt(data[i][j],i,j);
    }

    public void ChangeDiagram(){
        
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
        data = processor.getData();
        for(int i = 0; i < data.length; i++){
            Sector[] local = new Sector[data[0].length];
            for(int j = 0; j < data[0].length; j++){
                local[j] = new Sector(Integer.parseInt(data[i][j]),getNewColor());
            }
            sectors.add(local);
        }
    }

    public void draw(Graphics g){
        Graphics2D gr = (Graphics2D)g;
        int x = 10, y = 10, width = 200, height = 200, maxValue = 0;
        double startAngle = 0, endAngle = 0;
        Sector currSector;
        for(int j = 0; j < sectors.get(0).length; j++)
            maxValue += sectors.get(0)[j].getValue();
        for(int i = 0; i < sectors.get(0).length; i++){
            currSector = sectors.get(0)[i];
            endAngle = Math.round(((double) currSector.getValue()/maxValue*360));
            gr.setColor(currSector.getColor());
            gr.fillArc(x, y, width, height, (int) Math.round(startAngle), (int) Math.round(endAngle));
            startAngle += endAngle;
            gr.setColor(Color.gray);
            gr.setStroke(new BasicStroke(3));
            int pointx = (int)Math.round(Math.cos(-1 * startAngle * Math.PI / 180)*100),
                    pointy = (int)Math.round(Math.sin(-1 * startAngle * Math.PI / 180)*100);
            points[i] = new Point(pointx,pointy);
            gr.drawLine(x+height/2,y+width/2,x +height/2 +(int)Math.round(Math.cos(-1 * startAngle * Math.PI / 180)*100),y +width/2+ (int)Math.round(Math.sin(-1 * startAngle * Math.PI / 180)*100));
        }
        gr.drawOval(x,y,width,height);
    }

    private Color getNewColor(){
        if (colorCount>4)
            colorCount = 0;
        return colors[colorCount++];
    }
}
