package com.msw.devops.input;

import lombok.Data;

/**
 * @author mashuangwei
 * @date 2018-12-24 14:17
 * @description:
 */
@Data
public class LoginUser {
    private String password;
    private String username;
    private boolean rememberMe;
}
