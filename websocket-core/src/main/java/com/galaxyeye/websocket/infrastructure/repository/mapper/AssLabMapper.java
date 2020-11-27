package com.galaxyeye.websocket.infrastructure.repository.mapper;

import com.galaxyeye.websocket.infrastructure.repository.entity.AssLab;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.AssLabExample;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AssLabMapper {

    int insertOne(HashMap<String, Object> hs);

    int insertBatch( List<AssLab> list);

    long getId();

    long countByExample(AssLabExample example);

    int deleteByExample(AssLabExample example);

    int insert(AssLab record);

    int insertSelective(AssLab record);

    List<AssLab> selectByExample(AssLabExample example);

    int updateByExampleSelective(@Param("record") AssLab record, @Param("example") AssLabExample example);

    int updateByExample(@Param("record") AssLab record, @Param("example") AssLabExample example);


}