/**
 * Created with IntelliJ IDEA.
 * User: Eduard
 * Date: 3/20/14
 * Time: 12:25 AM
 * To change this template use File | Settings | File Templates.
 */
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        final JFrame frame=new JFrame("Gistogram");
        frame.setBounds(100,100,600,600);
        JButton button=new JButton("Open");
        button.setBounds(0,0,70,40);
        final JFileChooser fileChooser=new JFileChooser();

        frame.add(fileChooser);
        frame.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fileChooser.showDialog(null, "choose csv") == JFileChooser.APPROVE_OPTION) {
                    try {
                        CSVProcessor csv = new CSVProcessor(fileChooser.getSelectedFile().getAbsolutePath());
                        String[][]src = csv.getData();
                        DiagramDrawer dr=new DiagramDrawer();
                        frame.add(dr);
                        //dr.show(frame, 0, 100, 500);
                        JTable table=new JTable(src.length,src[0].length);
                        //dr.connectTable(table);
                        table.setBounds(100,600,600,200);
                        frame.add(table);
                        frame.validate();
                        frame.repaint();
                    } catch (Exception ex) {
                    }
                }
            }
        });

        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        frame.setLayout(null);
        frame.show();
    }
}