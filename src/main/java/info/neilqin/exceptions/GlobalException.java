package info.neilqin.exceptions;

/**
 * 全局异常类
 * @author Neil
 * @date 2018/11/14 11:25
 */
public class GlobalException extends RuntimeException {

    protected String message;
    protected int code;

    public static final GlobalException SYSTEM_ERR = new GlobalException(0,"系统异常");
    public static final GlobalException REQUEST_ILLEGAL = new GlobalException(-1,"非法请求");
    public static final GlobalException REQUEST_LIMIT = new GlobalException(100,"服务器繁忙");

    public GlobalException(String message) {
        super(message);
    }

    public GlobalException(String message, Throwable cause) {
        super(message, cause);
    }

    public GlobalException(int code ,String message,Object... args){
        this.code = code;
        this.message = args.length > 0?String.format(message,args):message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
