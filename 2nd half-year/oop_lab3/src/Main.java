/**
 * Created with IntelliJ IDEA.
 * User: Eduard
 * Date: 3/20/14
 * Time: 12:25 AM
 * To change this template use File | Settings | File Templates.
 */
import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import javax.swing.*;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        JFrame frame = new JFrame("MainFrame");
        frame.setBounds(100,100,300,300);
        frame.getContentPane().add(new DiagramDrawer());
        frame.setVisible(true);

    }
}