package exceptions;

/**
 * Created by OM on 02.10.2016.
 */
public class FindByParametersException extends Exception{

    private String message;

    public FindByParametersException(String message) {
        this.message = message;
    }
    public FindByParametersException() {}


        public String getMessage(){
        return "[FindByNameException]: There are no records with specified parameters='" + message;
    }


}
