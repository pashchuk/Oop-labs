/**
 * Created with IntelliJ IDEA.
 * User: Eduard
 * Date: 3/20/14
 * Time: 12:25 AM
 * To change this template use File | Settings | File Templates.
 */
import javafx.application.Application;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import javax.swing.*;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        JFrame frame = new JFrame("MainWindow");
        CSVProcessor processor = new CSVProcessor("asd.csv");
        DiagramDrawer drawer = new DiagramDrawer(processor);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 600, 600);
        frame.setResizable(false);
        frame.setLayout(null);
        drawer.setLocation(200, 150);
        drawer.setSize(300, 300);
        frame.add(drawer);
        frame.getContentPane().add(drawer.getTable());
        frame.validate();
        frame.setVisible(true);
    }
}