package com.msw.devops.mapper;

import com.msw.devops.entity.DbInfo;

public interface DbInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DbInfo record);

    int insertSelective(DbInfo record);

    DbInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DbInfo record);

    int updateByPrimaryKey(DbInfo record);
}