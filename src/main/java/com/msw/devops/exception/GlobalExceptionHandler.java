package com.msw.devops.exception;

import com.msw.devops.util.ResultUtil;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理
 *
 * @author mashuangwei
 */
@ControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 系统异常处理，比如：404,500
     *
     * @param req
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    @ResponseStatus(code= HttpStatus.INTERNAL_SERVER_ERROR,reason="")
    public Result defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        logger.error("GlobalExceptionHandler:", e);
        if (e instanceof NoHandlerFoundException) {
            return ResultUtil.error(404, e.getMessage());
        } else if (e instanceof MyException) {
            MyException myException = (MyException) e;
            return ResultUtil.error(myException.getCode(), myException.getMessage());
        } else {
            return ResultUtil.error(500, e.getMessage());
        }

    }

    @ExceptionHandler(value = AuthorizationException.class)
    public String handleAuthorizationException() {
        return "403";
    }

}
