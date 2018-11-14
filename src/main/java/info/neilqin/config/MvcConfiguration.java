package info.neilqin.config;

import info.neilqin.interceptors.CheckLogin;
import info.neilqin.resovler.JsonParamResovler;
import info.neilqin.resovler.ValidatorResovler;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author Neil
 * @date 2018/11/14 11:23
 */
@Configuration
public class MvcConfiguration extends WebMvcConfigurerAdapter{

    @Autowired
    JsonParamResovler jsonParamResovler;
    @Autowired
    ValidatorResovler validatorResovler;


    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(validatorResovler);
        argumentResolvers.add(jsonParamResovler);
        super.addArgumentResolvers(argumentResolvers);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CheckLogin.LoginInterceptor());
        super.addInterceptors(registry);
    }
}
