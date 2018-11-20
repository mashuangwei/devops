package com.msw.devops.mapper;

import com.msw.devops.entity.Role;
import com.msw.devops.entity.UserRole;

import java.util.List;

public interface UserRoleMapper {
    int insert(UserRole record);
    List<Role> findByUserName(String userName);

    int insertSelective(UserRole record);
}
