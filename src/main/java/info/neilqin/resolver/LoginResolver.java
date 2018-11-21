package info.neilqin.resolver;

import info.neilqin.api.IUserService;
import info.neilqin.common.constants.Constants;
import info.neilqin.entity.po.UserPO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author Neil
 * @date 2018/11/19 15:48
 */
@Component
public class LoginResolver implements HandlerMethodArgumentResolver{

    private static final String LOGIN_URI = "/user/login";

    @Autowired
    IUserService userService;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType() == UserPO.class;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        HttpServletResponse response = nativeWebRequest.getNativeResponse(HttpServletResponse.class);

        String paramToken = request.getParameter(Constants.Catch.COOKIE_NAME_TOKEN);
        String cookieToken = getCookieValue(request, Constants.Catch.COOKIE_NAME_TOKEN);
        boolean isLoginUri = LOGIN_URI.equals(request.getRequestURI());
        if (StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken)) {
            if (!isLoginUri){
                response.sendRedirect(LOGIN_URI);
                return null;
            }
        }
        String token = StringUtils.isEmpty(paramToken) ? cookieToken : paramToken;
        UserPO user = userService.getByToken(response, token);
        if (!isLoginUri && user == null){
            response.sendRedirect(LOGIN_URI);
        }
        return user;
    }

    private String getCookieValue(HttpServletRequest request, String cookiName) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length <= 0) {
            return null;
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(cookiName)) {
                return cookie.getValue();
            }
        }
        return null;
    }
}
