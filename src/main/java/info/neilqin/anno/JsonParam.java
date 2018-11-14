package info.neilqin.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 将Json请求对象封装 转换为参数列表格式
 * @author Neil
 * @date 2018/11/14 11:14
 */
@Target({ElementType.PARAMETER,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonParam {

    String value() default "";

    boolean required() default true;

    String defaultValue() default "";

}
