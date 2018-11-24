package info.neilqin.resolver;

import com.alibaba.fastjson.JSONObject;
import info.neilqin.anno.Validator;
import info.neilqin.common.constants.Constants;
import info.neilqin.common.enums.ValidatorEnum;
import info.neilqin.utils.ServletUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 参数校验处理
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
        HttpServletRequest httpServletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        Object val;
        if(Constants.HttpMethod.POST.equalsIgnoreCase(httpServletRequest.getMethod())){
            String requestBody = ServletUtils.getRequestBody(httpServletRequest);
            JSONObject jsonObject = JSONObject.parseObject(requestBody);
            val = jsonObject.get(parameter.getParameterName());
        } else {
            val = httpServletRequest.getParameter(parameter.getParameterName());
        }
        Validator anno = parameter.getParameterAnnotation(Validator.class);
        ValidatorEnum[] value = anno.value();
        if(value.length > 0){
            for (ValidatorEnum validator : value) {
                validator.validate(parameter.getParameterName(), val);
            }
        }
        // 强制类型转换
        if(null != binderFactory && null != val){
            WebDataBinder binder = binderFactory.createBinder(webRequest, parameter, parameter.getParameterName());
            val = binder.convertIfNecessary(val,parameter.getParameterType(),parameter);
        }
        return val;
    }
}
