
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class CSVProcessor {

    private String[][] data;
    private ArrayList<String> buffer;

    /**
     * Create a object of csv data
     *
     * @param path Path to *.csv file
     * @throws IOException
     */
    public CSVProcessor(String path)throws IOException{
        boolean read;
        try{
            read = Deserialise("serialised.dat");
        }catch (IOException ex){
            read = false;
        }
        if(!read){
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
            Parse();
        }
    }
    //parse data from ArrayList<String> and split all lines to ',' delimiter
    //and fill a main field "data"
    public void Parse() throws CSVParseException{
        int rowcount = buffer.get(0).split(",").length;
        data = new String[buffer.size()][rowcount];
        try{
            for(int i = 0; i < buffer.size(); i++){
                String[] values = buffer.get(i).split(",");
                if(values.length < rowcount)
                    throw new CSVParseException(CSVParseErrors.mustHaveMoreData);
                for(int j = 0; j < values.length; j++){
                    data[i][j] = values[j];
                }
            }
        } catch (IndexOutOfBoundsException ex){
            throw new CSVParseException(CSVParseErrors.mustHaveLessData);
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
        for(int i = 0; i < data.length; i++){
            for(int j = 0; j < data[0].length-1; j++){
                str.write(data[i][j].getBytes());
                str.write(',');
            }
            str.write(data[i][data[i].length-1].getBytes());
            str.write('\n');
        }
        str.close();
    }

    /**
     * Serialize all data in current object to file "serialized.dat".
     * @param path Path to directory whe file will be created.
     * @throws IOException
     */
    public void Serialise() throws IOException {
        File file = new File("serialised.dat");
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
                    data = (String[][]) str.readObject();
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
        for(int i = 0; i < data.length; i++){
            for(int j = 0; j < data[0].length-1; j++)
                System.out.print(data[i][j] + ',');
            System.out.println(data[i][data[i].length-1]);
        }
    }
    public String[][] getData(){
        return data;
    }
}
