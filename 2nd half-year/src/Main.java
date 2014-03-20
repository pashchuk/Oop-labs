/**
 * Created with IntelliJ IDEA.
 * User: Eduard
 * Date: 3/20/14
 * Time: 12:25 AM
 * To change this template use File | Settings | File Templates.
 */
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        CSVProcessor a = new CSVProcessor("LicenseImportSample.csv");
        a.Print();
        a.Serialise("asd.dat");
        a.Deserialise("asd.dat");
        a.Print();
    }
}