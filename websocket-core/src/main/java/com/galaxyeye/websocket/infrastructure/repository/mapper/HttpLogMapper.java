package com.galaxyeye.websocket.infrastructure.repository.mapper;

import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.HttpLogExample;
import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

public interface HttpLogMapper {
    long countByExample(HttpLogExample example);

    int deleteByExample(HttpLogExample example);

    int insert(HttpLog record);

    int insertSelective(HttpLog record);

    List<HttpLog> selectByExample(HttpLogExample example);

    int updateByExampleSelective(@Param("record") HttpLog record, @Param("example") HttpLogExample example);

    int updateByExample(@Param("record") HttpLog record, @Param("example") HttpLogExample example);
}