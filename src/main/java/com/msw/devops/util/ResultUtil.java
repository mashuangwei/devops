package com.msw.devops.util;

import com.msw.devops.enums.ResultEnum;
import com.msw.devops.exception.Result;
import java.util.Date;

/**
 * Created by mashuangwei on 2017/7/16.
 */
public class ResultUtil {

    public static Result success(Integer code, Object object){
        Result result = new Result();
        result.setCode(code);
        result.setMessage("success");
        result.setData(object);
        result.setDate(new Date());
        return result;
    }

    public static Result success(Integer code){
        Result result = new Result();
        result.setCode(code);
        result.setMessage("success");
        result.setDate(new Date());
        return result;
    }

    public static Result error(Integer code,String message,Object object){
        Result result = new Result();
        result.setCode(code);
        result.setMessage(message);
        result.setData(object);
        result.setDate(new Date());
        return result;
    }

    public static Result error(ResultEnum resultEnum, Object object){
        Result result = new Result();
        result.setCode(resultEnum.getCode());
        result.setMessage(resultEnum.getMessage());
        result.setData(object);
        result.setDate(new Date());
        return result;
    }

    public static Result error(Integer code,String message){
        Result result = new Result();
        result.setCode(code);
        result.setMessage(message);
        result.setDate(new Date());
        return result;
    }

    public static Result error( ResultEnum resultEnum){
        Result result = new Result();
        result.setCode(resultEnum.getCode());
        result.setMessage(resultEnum.getMessage());
        result.setDate(new Date());
        return result;
    }

}
