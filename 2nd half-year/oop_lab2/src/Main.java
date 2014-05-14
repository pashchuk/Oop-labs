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
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        String path;
        Scanner asd = new Scanner(System.in);
        path = asd.nextLine();
        CSVProcessor a = new CSVProcessor(path);
        Thread thr = new Thread(new Runnable() {
                @Override
                public void run() {
                    a.Parse();
                }
        });
        thr.start();
        thr.join();
        a.Print();
    }
}