/**
 *
 */
package bf.mfptps.appgestionsconges.web.exceptions;

/**
 * @author aboubacary
 *
 */
public class AppTechnicalException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = -5519716678305708623L;

    public AppTechnicalException(String message, Object... params) {
        super(String.format(message, params));
    }
}
