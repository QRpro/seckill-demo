package info.neilqin.common.enums;

/**
 * 响应状态码
 * @author Neil
 * @date 2018/11/14 11:47
 */
public enum ResultEnum {

    SUCCESS(200,"SUCCESS");

    private int code;
    private String message;


    ResultEnum(int code,String message){
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
