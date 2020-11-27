package com.galaxyeye.websocket.infrastructure.repository.mapper;

import com.galaxyeye.websocket.infrastructure.repository.entity.TKeyindex;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.TKeyindexExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TKeyindexMapper {

    long countByExample(TKeyindexExample example);

    int deleteByExample(TKeyindexExample example);

    int insert(TKeyindex record);

    int insertBatch(List<TKeyindex> recordList);

    int insertSelective(TKeyindex record);

    List<TKeyindex> selectByExample(TKeyindexExample example);

    int updateByExampleSelective(@Param("record") TKeyindex record, @Param("example") TKeyindexExample example);

    int updateByExample(@Param("record") TKeyindex record, @Param("example") TKeyindexExample example);
}