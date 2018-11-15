package info.neilqin.exceptions;

/**
 * @author Neil
 * @date 2018/11/14 11:25
 */
public class BusiException extends GlobalException {

    private static final BusiException PWD_ERR = new BusiException(100001,"密码错误");

    public BusiException(String message) {
        super(message);
        this.message = message;
    }

    public BusiException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusiException(int code,String message,Object... args) {
        super(code,message,args);
    }

    public BusiException newInstance(String msgFormat, Object... args) {
        return new BusiException(this.code, msgFormat, args);
    }
    public BusiException format(Object... args) {
        return new BusiException(this.code, this.message, args);
    }
}
