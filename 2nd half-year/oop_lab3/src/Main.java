/**
 * Created with IntelliJ IDEA.
 * User: Eduard
 * Date: 3/20/14
 * Time: 12:25 AM
 * To change this template use File | Settings | File Templates.
 */
import javafx.application.Application;
import javafx.stage.FileChooser;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        JFrame frame = new JFrame("MainWindow");
        CSVProcessor processor = new CSVProcessor("asd.csv");
        DiagramDrawer drawer = new DiagramDrawer(processor);
        JButton saveDiagram = new JButton("Save diagram");
        JButton saveTable = new JButton("Save table");
        JFileChooser choser = new JFileChooser();
        saveDiagram.setLocation(10,10);
        saveDiagram.setSize(150, 30);
        saveTable.setLocation(180, 10);
        saveTable.setSize(150, 30);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 600, 600);
        frame.setResizable(false);
        frame.setLayout(null);
        drawer.setLocation(200, 150);
        drawer.setSize(300, 300);
        frame.add(saveDiagram);
        frame.add(saveTable);
        frame.add(drawer);
        frame.getContentPane().add(drawer.getTable());
        frame.validate();
        frame.setVisible(true);
        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                drawer.onChange = true;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                drawer.onChange=false;
            }
        });
        frame.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                drawer.changeDiagram(e.getPoint());
            }
        });

        saveDiagram.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(choser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION){
                    BufferedImage image = new BufferedImage(220,220,BufferedImage.TYPE_INT_RGB);
                    drawer.draw(image.getGraphics());
                    File file = choser.getSelectedFile();
                    try {
                        ImageIO.write(image,"jpg",file);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }

            }
        });
        saveTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try {
                    processor.Serialise();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });


    }
}