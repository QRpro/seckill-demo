package info.neilqin.common.views;

import com.alibaba.fastjson.JSONObject;
import info.neilqin.common.enums.ResultEnum;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.View;

/**
 * @author Neil
 * @date 2018/11/14 11:26
 */
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class JSONView<T> implements View {

    private int responseCode;
    private String msg;
    private T data;

    @Override
    public String getContentType() {
        return null;
    }

    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().print(JSONObject.toJSONString(this));
    }

    public static JSONView parseError(int code,String msg){
        return new JSONView(code,msg,null);
    }

    public static JSONView parseCode(ResultEnum emun){
        return parseError(emun.getCode(),emun.getMessage());
    }

    public static<T> JSONView<T> parseSuccess(T obj){
        return new JSONView(ResultEnum.SUCCESS.getCode(),ResultEnum.SUCCESS.getMessage(),obj);
    }

}
