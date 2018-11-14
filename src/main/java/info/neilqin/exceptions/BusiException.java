package info.neilqin.exceptions;

/**
 * @author Neil
 * @date 2018/11/14 11:25
 */
public class BusiException extends GlobalException {
    private String message;
    public BusiException(String message) {
        super(message);
        this.message = message;
    }

    public BusiException(String message, Throwable cause) {
        super(message, cause);
    }
}
