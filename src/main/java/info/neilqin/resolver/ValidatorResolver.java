package info.neilqin.resolver;

import com.alibaba.fastjson.JSONObject;
import info.neilqin.anno.Validator;
import info.neilqin.common.enums.ValidatorEnum;
import info.neilqin.utils.ServletUtils;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author Neil
 * @date 2018/11/14 17:12
 */
@Component
public class ValidatorResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(Validator.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String requestBody = ServletUtils.getRequestBody(webRequest);
        JSONObject jsonObject = JSONObject.parseObject(requestBody);
        Object val = jsonObject.get(parameter.getParameterName());
        Validator anno = parameter.getParameterAnnotation(Validator.class);
        ValidatorEnum[] value = anno.value();
        if(value.length > 0){
            for (ValidatorEnum validator : value) {
                validator.validate(parameter.getParameterName(), val);
            }
        }
        return val;
    }
}
