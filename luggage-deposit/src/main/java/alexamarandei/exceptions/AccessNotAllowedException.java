package alexamarandei.exceptions;

public class AccessNotAllowedException extends Exception {
    public AccessNotAllowedException(String errorMessage) {
        super(errorMessage);
    }
}
