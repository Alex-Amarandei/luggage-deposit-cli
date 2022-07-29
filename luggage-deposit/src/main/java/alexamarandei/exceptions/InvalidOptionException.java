package alexamarandei.exceptions;

/**
 * Thrown when providing an option that is not present in the Option
 * Menu.
 */
public class InvalidOptionException extends Exception {
    /**
     * @param errorMessage The error message indicating the reason of this
     *                     exception's occurrence.
     */
    public InvalidOptionException(String errorMessage) {
        super(errorMessage);
    }
}
