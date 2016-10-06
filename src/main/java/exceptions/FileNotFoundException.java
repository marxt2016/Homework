package exceptions;

/**
 * Created by OM on 02.10.2016.
 */
public class FileNotFoundException extends RuntimeException{

    private String message;

    public FileNotFoundException(String message) {
        this.message = message;
    }
    public FileNotFoundException() {}


    public String getMessage(){
        return "[FileNotFoundException]: The path to file wasn't provided'" + message;
    }


}
