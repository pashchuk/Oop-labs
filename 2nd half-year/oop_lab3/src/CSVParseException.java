import java.io.IOException;

/**
 * Created by Eduard on 3/20/2014.
 */
public class CSVParseException extends IOException {
        private String message;
    public CSVParseException(CSVParseErrors error){
        switch (error){
            case invalidData:
                message = "CSV file have some incorrect data";
                break;
            case isNotCSVFile:
                message = "The current file is not a .csv file";
                break;
            case mustHaveMoreData:
                message = "This file must have a more data";
                break;
            default:
                break;
        }
    }
    public CSVParseException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}