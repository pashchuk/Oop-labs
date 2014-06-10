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
    private int selectedRow = 0;
    private int selectedCol;
    public boolean onChange = false;
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
                        initialiseSectors();
                    }
            }
        });
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                selectedRow = table.rowAtPoint(e.getPoint());
                repaint();
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

    public void changeDiagram(Point newPoint){
        for(int i = 0; i < points.length; i++){
            if((newPoint.x>=points[i].x-4)&&(newPoint.x<=points[i].x+4)&&(newPoint.y>=points[i].y+30-4)&&(newPoint.y<=points[i].y+30+4)){
                selectedCol = i;
                onChange = true;
                break;
            }
        }
            if(onChange){
                Sector first, second ;
                Point prevpoint, point, nextpoint ;
                if(selectedCol==0){
                    first = sectors.get(selectedRow)[sectors.get(0).length-1];
                    second = sectors.get(selectedRow)[0];
                    prevpoint = points[sectors.get(0).length-1];
                    point = points[0];
                    nextpoint = points[1];
                }
                else if (selectedCol == sectors.get(selectedRow).length-1){
                    first = sectors.get(selectedRow)[sectors.get(0).length-2];
                    second = sectors.get(selectedRow)[sectors.get(0).length-1];
                    prevpoint = points[sectors.get(0).length-2];
                    point = points[sectors.get(0).length-1];
                    nextpoint = points[0];
                }
                else{
                    first = sectors.get(selectedRow)[selectedCol];
                    second = sectors.get(selectedRow)[selectedCol+1];
                    prevpoint = points[selectedCol-1];
                    point = points[selectedCol];
                    nextpoint = points[selectedCol+1];
                }
                int maxValue = first.getValue() + second.getValue();
                double startangle = calcVectorAngle(point,nextpoint);
                double endangle = calcVectorAngle(point,newPoint);
                double diff = endangle-startangle;
                double diffvalue = Math.toDegrees(diff)*maxValue/calcVectorAngle(prevpoint,nextpoint);
                double deggre = Math.toDegrees(diff);
                double asd = calcVectorAngle(prevpoint,nextpoint);
                data[selectedRow][selectedCol] +=(int)diffvalue;
                initialiseSectors();
                updateTable();
                this.repaint();
            }
    }
    private double calcVectorAngle(Point point1, Point point2){
        double scalar = point1.getX()*point2.getX() + point1.getY()*point2.getY();
        double module = Math.sqrt(Math.pow(point1.getX(),2)+Math.pow(point1.getY(),2))*
                Math.sqrt(Math.pow(point2.getX(),2)+Math.pow(point2.getY(),2));
        return Math.acos(scalar/module);
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
        initialiseSectors();
    }

    private void initialiseSectors(){
        sectors.clear();
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
        gr.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        int x = 0, y = 0, width = 200, height = 200, maxValue = 0;
        double startAngle = 0, endAngle = 0;
        Sector currSector;
        for(int j = 0; j < sectors.get(selectedRow).length; j++)
            maxValue += sectors.get(selectedRow)[j].getValue();
        for(int i = 0; i < sectors.get(selectedRow).length; i++){
            currSector = sectors.get(selectedRow)[i];
            endAngle = Math.round(((double) currSector.getValue()/maxValue*360));
            gr.setColor(currSector.getColor());
            gr.fillArc(x, y, width, height, (int) Math.round(startAngle), (int) Math.round(endAngle));
            int pointx = x + height/2 +(int)Math.round(Math.cos( Math.toRadians(startAngle))*100),
                    pointy = y +width/2+ (int)Math.round(Math.sin(-1 * Math.toRadians(startAngle))*100);
            points[i] = new Point(pointx+this.getX(),pointy+this.getY());
            startAngle += endAngle;
            gr.setColor(Color.gray);
            gr.setStroke(new BasicStroke(2));
            gr.drawLine(x+height/2,y+width/2,x +height/2 +(int)Math.round(Math.cos( Math.toRadians(startAngle) )*100),
                    y +width/2+ (int)Math.round(Math.sin(-1 * Math.toRadians(startAngle))*100));
        }
        gr.drawOval(x,y,width,height);
        gr.setColor(Color.red);
        for(int i = 0; i < points.length; i++)
            gr.fillOval(points[i].x-3-this.getX(),points[i].y-3-this.getY(),6,6);
    }

    private Color getNewColor(){
        if (colorCount>4)
            colorCount = 0;
        return colors[colorCount++];
    }
}
