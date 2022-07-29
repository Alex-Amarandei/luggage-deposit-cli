package alexamarandei.exceptions;

/**
 * Thrown when attempting to access Admin-level data without
 * proper authorization.
 */
public class AccessNotAllowedException extends Exception {
    /**
     * @param errorMessage The error message indicating the reason of this
     *                     exception's occurrence.
     */
    public AccessNotAllowedException(String errorMessage) {
        super(errorMessage);
    }
}
