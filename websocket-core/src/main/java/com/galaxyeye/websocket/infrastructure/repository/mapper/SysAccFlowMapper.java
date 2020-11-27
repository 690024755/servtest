package com.galaxyeye.websocket.infrastructure.repository.mapper;

import com.galaxyeye.websocket.infrastructure.repository.entity.SysAccFlow;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.SysAccFlowExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysAccFlowMapper {

    long countByExample(SysAccFlowExample example);

    int deleteByExample(SysAccFlowExample example);

    int insert(SysAccFlow record);

    int insertSelective(SysAccFlow record);

    List<SysAccFlow> selectByExample(SysAccFlowExample example);

    int updateByExampleSelective(@Param("record") SysAccFlow record, @Param("example") SysAccFlowExample example);

    int updateByExample(@Param("record") SysAccFlow record, @Param("example") SysAccFlowExample example);
}