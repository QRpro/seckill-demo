package info.neilqin.exceptions;

/**
 * @author Neil
 * @date 2018/11/14 11:26
 */
public class ValidatorException extends GlobalException {

    protected String message;

    public ValidatorException(String message) {
        super(message);
        this.message = message;
    }

    public ValidatorException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
