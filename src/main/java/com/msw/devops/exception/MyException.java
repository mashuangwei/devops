package com.msw.devops.exception;

import com.msw.devops.enums.ResultEnum;
import lombok.Data;

/**
 * 作者: mashuangwei
 * 日期: 2017/12/2
 */

@Data
public class MyException extends RuntimeException{
    private Integer code;
    private String message;

    public MyException(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.message = resultEnum.getMessage();
    }
}
