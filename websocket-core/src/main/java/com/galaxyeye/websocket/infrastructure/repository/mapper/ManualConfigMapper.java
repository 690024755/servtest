package com.galaxyeye.websocket.infrastructure.repository.mapper;

import com.galaxyeye.websocket.infrastructure.repository.entity.ManualConfig;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.ManualConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ManualConfigMapper {

    int insertBatch(List<ManualConfig> list);

    long countByExample(ManualConfigExample example);

    int deleteByExample(ManualConfigExample example);

    int insert(ManualConfig record);

    int insertSelective(ManualConfig record);

    List<ManualConfig> selectByExampleWithBLOBs(ManualConfigExample example);

    List<ManualConfig> selectByExample(ManualConfigExample example);

    int updateByExampleSelective(@Param("record") ManualConfig record, @Param("example") ManualConfigExample example);

    int updateByExampleWithBLOBs(@Param("record") ManualConfig record, @Param("example") ManualConfigExample example);

    int updateByExample(@Param("record") ManualConfig record, @Param("example") ManualConfigExample example);
}