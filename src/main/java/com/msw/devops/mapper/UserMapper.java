package com.msw.devops.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.msw.devops.entity.User;
@DS("at_master")
public interface UserMapper extends BaseMapper<User> {
    User findByUserName(String userName);

    int addUser(User user);
}
