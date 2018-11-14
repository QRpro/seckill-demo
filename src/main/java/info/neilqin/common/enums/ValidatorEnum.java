package info.neilqin.common.enums;

import info.neilqin.exceptions.ValidatorException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.util.StringUtils;

/**
 * @author Neil
 * @date 2018/11/14 15:32
 */
public enum ValidatorEnum {

    NOT_BLANK{
        @Override
        public void validate(String name, Object val) {
            if (StringUtils.isEmpty(val)){
                throw new ValidatorException(name);
            }
        }
    },
    PHONE{
        @Override
        public void validate(String name, Object val) {
            String valStr = val.toString();
            String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
            if (valStr.length() != 11) {
                throw new ValidatorException(name);
            } else {
                Pattern p = Pattern.compile(regex);
                Matcher m = p.matcher(valStr);
                boolean isMatch = m.matches();
                if (!isMatch) {
                    throw new ValidatorException(name);
                }
            }

        }
    },
    EMAIL{
        @Override
        public void validate(String name, Object val) {
            String valStr = val.toString();
            String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(valStr);
            boolean isMatched = matcher.matches();
            if(!isMatched){
                throw new ValidatorException(name);
            }
        }
    };

    public abstract void validate(String name, Object val);

}
