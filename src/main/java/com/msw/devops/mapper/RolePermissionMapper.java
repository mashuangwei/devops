package com.msw.devops.mapper;

import com.msw.devops.entity.RolePermission;

public interface RolePermissionMapper {
    int insert(RolePermission record);

    int insertSelective(RolePermission record);
}