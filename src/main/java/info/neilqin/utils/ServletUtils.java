package info.neilqin.utils;

import info.neilqin.common.constants.Constants;
import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.NativeWebRequest;

/**
 * @author Neil
 * @date 2018/11/14 17:30
 */
public class ServletUtils {

    public static String getRequestBody(NativeWebRequest webRequest) {
        HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        String jsonBody = (String) servletRequest.getAttribute(Constants.Catch.JSON_REQUEST_BODY);
        if (jsonBody == null) {
            try {
                StringBuffer sb = new StringBuffer();
                String line;
                BufferedReader reader = servletRequest.getReader();
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                jsonBody = sb.toString();
                // body 存入attribute域，避免重复读取
                servletRequest.setAttribute(Constants.Catch.JSON_REQUEST_BODY, jsonBody);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return jsonBody;
    }
}
