package info.neilqin.interceptors;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 登录拦截器
 * @author Neil
 * @date 2018/11/14 17:50
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckLogin {

    @Component
    @Slf4j
    class LoginInterceptor extends HandlerInterceptorAdapter {

        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            if (!(handler instanceof HandlerMethod)){return false;}
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            // 判断类和方法上是否有注解
            if (handlerMethod.hasMethodAnnotation(CheckLogin.class)||handlerMethod.getBeanType().getAnnotation(CheckLogin.class)!=null){
                // 登录拦截也可用注解实现 具体胆码请参照 LoginResolver
                log.info("============= request uri : {} =============",request.getRequestURI());
            }
            return true;
        }
    }
}
