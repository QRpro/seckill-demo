package info.neilqin.config;

import info.neilqin.interceptors.CheckLogin;
import info.neilqin.resolver.JsonParamResolver;
import info.neilqin.resolver.LoginResolver;
import info.neilqin.resolver.ValidatorResolver;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Neil
 * @date 2018/11/14 11:23
 */
@Configuration
public class MvcConfiguration implements WebMvcConfigurer {

    @Autowired
    JsonParamResolver jsonParamResolver;
    @Autowired
    ValidatorResolver validatorResolver;
    @Autowired
    LoginResolver loginResolver;


    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(loginResolver);
        argumentResolvers.add(validatorResolver);
        argumentResolvers.add(jsonParamResolver);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 静态资源不做拦截   springboot已经做好了静态资源映射
        registry.addInterceptor(new CheckLogin.LoginInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/static/**");
    }
}
