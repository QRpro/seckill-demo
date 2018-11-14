package info.neilqin.anno;

import info.neilqin.common.enums.ValidatorEnum;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 对参数进行校验
 * @author Neil
 * @date 2018/11/14 11:15
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface Validator {

    ValidatorEnum[] value();
}
