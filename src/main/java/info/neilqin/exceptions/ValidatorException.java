package info.neilqin.exceptions;

/**
 * @author Neil
 * @date 2018/11/14 11:26
 */
public class ValidatorException extends GlobalException {


    public static final ValidatorException VALIDATOR_ERR = new ValidatorException(100010,"非法参数:%s");
    public static final ValidatorException USER_NOT_EXIST = new ValidatorException(100011,"用户不存在");
    public static final ValidatorException PWD_ERR = new ValidatorException(100012,"密码错误");
    public static final ValidatorException ID_EXISTS = new ValidatorException(100013,"已存在注册账号");

    public ValidatorException(String message) {
        super(message);
        this.message = message;
    }

    public ValidatorException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidatorException(int code,String message,Object... args) {
        super(code,message,args);
    }

    public ValidatorException newInstance(String msgFormat, Object... args) {
        return new ValidatorException(this.code, msgFormat, args);
    }
    public ValidatorException format(Object... args) {
        return new ValidatorException(this.code, this.message, args);
    }


}
