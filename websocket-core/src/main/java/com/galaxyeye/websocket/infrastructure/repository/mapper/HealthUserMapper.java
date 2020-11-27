package com.galaxyeye.websocket.infrastructure.repository.mapper;

import com.galaxyeye.websocket.infrastructure.repository.entity.HealthUser;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.HealthUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HealthUserMapper {
    long countByExample(HealthUserExample example);

    int deleteByExample(HealthUserExample example);

    int deleteAll();

    int insert(HealthUser record);

    int insertSelective(HealthUser record);

    List<HealthUser> selectByExample(HealthUserExample example);

    int updateByExampleSelective(@Param("record") HealthUser record, @Param("example") HealthUserExample example);

    int updateByExample(@Param("record") HealthUser record, @Param("example") HealthUserExample example);
}