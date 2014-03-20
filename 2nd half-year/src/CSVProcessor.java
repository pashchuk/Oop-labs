
import java.io.*;
import java.util.ArrayList;

public class CSVProcessor {
    private ArrayList<ArrayList<String>> data;

    public CSVProcessor() {}
    public CSVProcessor(String path)throws IOException{
        data = new ArrayList<ArrayList<String>>();
        BufferedReader f = new BufferedReader(new FileReader(path));
        String line = f.readLine();
        ArrayList<String> buffer = new ArrayList<String>();
        while(line != null){
            buffer.add(line);
            line = f.readLine();
        }
        Parse(buffer);
        int a = 5;
    }

    private void Parse(ArrayList<String> buffer){
        for(String s : buffer){
            data.add(new ArrayList<String>());
            int firstindex = 0;
            for(int i = 0; i < s.length(); i++){
                if(s.charAt(i) == ','){
                    data.get(data.size()-1).add(s.substring(firstindex,i));
                    firstindex = i + 1;
                }
            }
            data.get(data.size()-1).add(s.substring(firstindex,s.length()));
        }
    }
    public void Upload(String path) throws IOException {
        File file = new File(path);
        if (file.exists())
            if(file.isFile())
                file.delete();
        file.createNewFile();
        FileOutputStream str = new FileOutputStream(file);
        for(ArrayList<String> i : data){
            for(String s : i){
                str.write(s.getBytes());
                str.write(',');
            }
            str.write('\n');
        }
        str.close();
    }

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
    public void Print(){
        for(ArrayList<String> i : data){
            int asd = 0;
            for(; asd < i.size()-1; asd++)
                System.out.print(i.get(asd) + ',');
            System.out.println(i.get(asd));
        }
    }
}
