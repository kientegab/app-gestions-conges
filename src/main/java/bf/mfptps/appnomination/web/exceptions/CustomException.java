package bf.mfptps.appnomination.web.exceptions;

import org.zalando.problem.*;

public class CustomException extends AbstractThrowableProblem {

    private static final long serialVersionUID = 1L;

    public CustomException(String message) {
        super(null, message, Status.BAD_REQUEST);
    }

}
