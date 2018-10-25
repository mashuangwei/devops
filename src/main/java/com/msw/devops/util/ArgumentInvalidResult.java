package com.msw.devops.util;

import lombok.Data;

/**
 * @author : mashuangwei
 */
@Data
public class ArgumentInvalidResult {
    private String field;
    private Object rejectedValue;
    private String defaultMessage;
}
