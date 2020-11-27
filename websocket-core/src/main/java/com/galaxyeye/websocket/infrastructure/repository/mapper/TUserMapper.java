package com.galaxyeye.websocket.infrastructure.repository.mapper;

import com.galaxyeye.websocket.infrastructure.repository.entity.TUser;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.TUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TUserMapper {
    long countByExample(TUserExample example);

    int deleteByExample(TUserExample example);

    int insert(TUser record);

    int insertSelective(TUser record);

    List<TUser> selectByExample(TUserExample example);

    int updateByExampleSelective(@Param("record") TUser record, @Param("example") TUserExample example);

    int updateByExample(@Param("record") TUser record, @Param("example") TUserExample example);
}