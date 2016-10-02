package exceptions;

/**
 * Created by qwer on 02.10.2016.
 */
public class FindByParametersException {

    private String message;

    public FindByParametersException(String message) {
        this.message = message;
    }

    public String getMessage(){
        return "[FindByNameException]: There are no records with specified parameters='" + message;
    }


}
