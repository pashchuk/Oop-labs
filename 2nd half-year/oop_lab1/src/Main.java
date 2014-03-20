/**
 * Created with IntelliJ IDEA.
 * User: Eduard
 * Date: 3/20/14
 * Time: 12:25 AM
 * To change this template use File | Settings | File Templates.
 */
import java.io.*;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws IOException {
        //CSVProcessor a = new CSVProcessor("LicenseImportSample.csv");
        CSVProcessor a = new CSVProcessor();
        if(a.Deserialise("serialized.dat"))
            a.Print();
        else{
            Scanner scan = new Scanner(System.in);
            String csv = null;
            int tryCount = 0;
            do{
                csv = scan.nextLine();
                if(tryCount==3){
                    a = new CSVProcessor((csv));
                    break;
                }
                try{
                    a = new CSVProcessor(csv);
                    break;
                }
                catch(IOException ex){tryCount++;}
            }while(true);
            a.Serialise("serialized.dat");
            a.Print();
        }
    }
}