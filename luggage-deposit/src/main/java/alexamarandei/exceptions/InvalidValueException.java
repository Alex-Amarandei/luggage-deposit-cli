package alexamarandei.exceptions;

/**
 * Thrown when providing an invalid value for the storage facility
 * properties.
 */
public class InvalidValueException extends Exception {
    /**
     * @param errorMessage The error message indicating the reason of this
     *                     exception's occurrence.
     */
    public InvalidValueException(String errorMessage) {
        super(errorMessage);
    }
}
