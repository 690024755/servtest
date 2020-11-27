package com.galaxyeye.websocket.infrastructure.repository.mapper;

import com.galaxyeye.websocket.infrastructure.repository.entity.TAllUsers;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.TAllUsersExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TAllUsersMapper {
    long countByExample(TAllUsersExample example);

    int deleteByExample(TAllUsersExample example);

    int insert(TAllUsers record);

    int insertSelective(TAllUsers record);

    List<TAllUsers> selectByExample(TAllUsersExample example);

    int updateByExampleSelective(@Param("record") TAllUsers record, @Param("example") TAllUsersExample example);

    int updateByExample(@Param("record") TAllUsers record, @Param("example") TAllUsersExample example);
}