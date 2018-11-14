package info.neilqin.config;

import info.neilqin.common.enums.ResultEnum;
import info.neilqin.common.views.JSONView;
import info.neilqin.exceptions.BusiException;
import info.neilqin.exceptions.GlobalException;
import info.neilqin.exceptions.ValidatorException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Neil
 * @date 2018/11/14 11:34
 */
@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(ValidatorException.class)
    @ResponseStatus(HttpStatus.OK)
    public JSONView validateException(ValidatorException e){
        ResultEnum resultEnum = ResultEnum.VALIDATOR_ERR;
        return JSONView.parseError(resultEnum.getCode(),String.format(resultEnum.getMessage(), e.getMessage()));
    }

    @ExceptionHandler(BusiException.class)
    @ResponseStatus(HttpStatus.OK)
    public JSONView exception(BusiException e){
        return JSONView.parseError(0,"程序异常"+e.getMessage());
    }

    @ExceptionHandler(GlobalException.class)
    @ResponseStatus(HttpStatus.OK)
    public JSONView exception(GlobalException e){
        return JSONView.parseError(0,"程序异常"+e.getMessage());
    }
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public JSONView exception(Exception e){
        return JSONView.parseError(0,"程序异常"+e.getMessage());
    }
}
