package alexamarandei.exceptions;

/**
 * Thrown when unable to find a luggage corresponding with the provided
 * data.
 */
public class LuggageNotFoundException extends Exception {
    /**
     * @param errorMessage The error message indicating the reason of this
     *                     exception's occurrence.
     */
    public LuggageNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
