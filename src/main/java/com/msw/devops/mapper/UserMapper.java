package com.msw.devops.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.msw.devops.entity.User;

public interface UserMapper extends BaseMapper<User> {
    @DS("easydb")
    User selectByMsw(Integer id);
}
