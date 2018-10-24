package com.msw.devops.service.impl;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.msw.devops.entity.User;
import com.msw.devops.mapper.UserMapper;
import com.msw.devops.service.UserService;
import org.springframework.stereotype.Service;

@Service
@DS("easydb")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    @DS("atdb")//这里必须包一层，不能调用mp默认的插入，因为会走到从库去
    public int addUser(User user) {
         return baseMapper.insert(user);
    }
}
