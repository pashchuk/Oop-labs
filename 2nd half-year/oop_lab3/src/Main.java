/**
 * Created with IntelliJ IDEA.
 * User: Eduard
 * Date: 3/20/14
 * Time: 12:25 AM
 * To change this template use File | Settings | File Templates.
 */
import com.sun.corba.se.impl.orbutil.graph.Graph;

import java.awt.*;
import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import javax.swing.*;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        JFrame frame = new JFrame("MainFrame");
        Graphics a = frame.getGraphics();
        CSVProcessor processor = new CSVProcessor("asd.csv");
        DiagramDrawer drawer = new DiagramDrawer(processor);
        drawer.setPreferredSize(new Dimension(500,500));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 600, 600);
        frame.setResizable(false);
        frame.setLayout(null);
        drawer.setSize(300, 300);
        drawer.setLocation(300, 300);
        frame.add(drawer);
        frame.getContentPane().add(drawer.getTable());
        frame.validate();
        Graphics b = frame.getGraphics();
        frame.setVisible(true);
    }
}