package alexamarandei.exceptions;

/**
 * Thrown when providing a Luggage Code in the wrong format (i.e. not
 * LAST_NAME#LUGGAGE_ID).
 */
public class InvalidCodeFormatException extends Exception {
    /**
     * @param errorMessage The error message indicating the reason of this
     *                     exception's occurrence.
     */
    public InvalidCodeFormatException(String errorMessage) {
        super(errorMessage);
    }
}