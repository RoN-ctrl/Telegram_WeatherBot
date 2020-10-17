package exceptions;

public class IncorrectCityNameException extends RuntimeException {

    public IncorrectCityNameException(String message) {
        super(message);
    }
}
