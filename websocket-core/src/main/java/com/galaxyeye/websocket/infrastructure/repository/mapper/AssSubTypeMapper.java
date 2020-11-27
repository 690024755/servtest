package com.galaxyeye.websocket.infrastructure.repository.mapper;

import com.galaxyeye.websocket.infrastructure.repository.entity.AssSubType;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.AssSubTypeExample;
import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AssSubTypeMapper {

    int insertOne(HashMap<String, Object> hs);

    int insertBatch( List<AssSubType> list);

    long getId();

    long countByExample(AssSubTypeExample example);

    int deleteByExample(AssSubTypeExample example);

    int insert(AssSubType record);

    int insertSelective(AssSubType record);

    List<AssSubType> selectByExample(AssSubTypeExample example);

    int updateByExampleSelective(@Param("record") AssSubType record, @Param("example") AssSubTypeExample example);

    int updateByExample(@Param("record") AssSubType record, @Param("example") AssSubTypeExample example);
}