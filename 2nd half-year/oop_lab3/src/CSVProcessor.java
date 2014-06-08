
import java.io.*;
import java.util.ArrayList;

public class CSVProcessor {

    private String[][] data;
    private ArrayList<String> buffer;

    public CSVProcessor() {}

    /**
     * Create a object of csv data
     *
     * @param path Path to *.csv file
     * @throws IOException
     */
    public CSVProcessor(String path)throws IOException{
        if(!path.substring(path.length()-3,path.length()).equals("csv"))
            throw new CSVParseException(CSVParseErrors.isNotCSVFile);
        try(BufferedReader f = new BufferedReader(new FileReader(path))){
            String line = f.readLine();
           buffer = new ArrayList<String>();
            while(line != null){
                buffer.add(line);
                line = f.readLine();
            }
        }
    }
    //parse data from ArrayList<String> and split all lines to ',' delimiter
    //and fill a main field "data"
    public void Parse(){
        int rowcount = buffer.get(0).split(",").length;
        data = new String[buffer.size()][rowcount];
        try{
            for(int i = 0; i < buffer.size(); i++){
                String[] values = buffer.get(i).split(",");
                for(int j = 0; j < rowcount; j++){
                    data[i][j] = values[j];
                }
            }
        } catch (IndexOutOfBoundsException ex){
            int a = 5;
        }
    }

    /**
     * Upload all data from current object to *.csv file. When file is exist, it will be replaced on new empty file.
     * @param path Path to new file
     * @throws IOException
     */
    public void Upload(String path) throws IOException {
        File file = new File(path);
        if (file.exists())
            if(file.isFile())
                file.delete();
        file.createNewFile();
        FileOutputStream str = new FileOutputStream(file);
        for(ArrayList<String> i : data){
            int asd = 0;
            for(; asd < i.size()-1; asd++){
                str.write(i.get(asd).getBytes());
                str.write(',');
            }
            str.write('\n');
        }
        str.close();
    }

    /**
     * Serialize all data in current object to file "serialized.dat".
     * @param path Path to directory whe file will be created.
     * @throws IOException
     */
    public void Serialise(String path) throws IOException {
        File file = new File(path);
        if (file.exists())
            if(file.isFile())
                file.delete();
        file.createNewFile();
        try(FileOutputStream f = new FileOutputStream(file)){
            try(ObjectOutputStream str = new ObjectOutputStream(f)){
                str.writeObject(data);
            }
        }
    }

    /**
     * Deserialized all data from "serialized.dat" file.
     * @param path Path to file "serialized.dat"
     * @return True if file has been deserialized.
     * @throws IOException
     */
    public boolean Deserialise(String path) throws IOException {
        File file = new File(path);
        if (file.exists()&&file.isFile())
            try(FileInputStream f = new FileInputStream(file)){
                try(ObjectInputStream str = new ObjectInputStream(f)){
                    data = (ArrayList<ArrayList<String>>) str.readObject();
                    return true;
                }
                catch(ClassNotFoundException ex){
                    System.out.println(ex.getMessage());
                    return false;
                }
            }
        return false;
    }

    /**
     * Print all data in current object to console
     */
    public void Print(){
        for(ArrayList<String> i : data){
            int asd = 0;
            for(; asd < i.size()-1; asd++)
                System.out.print(i.get(asd) + ',');
            System.out.println(i.get(asd));
        }
    }
}
