package com.galaxyeye.websocket.infrastructure.repository.mapper;

import com.galaxyeye.websocket.infrastructure.repository.entity.SxcServiceRule;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.SxcServiceRuleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SxcServiceRuleMapper {
    int insert(SxcServiceRule record);

    int insertSelective(SxcServiceRule record);

    List<SxcServiceRule> selectByExample(SxcServiceRuleExample example);

    int updateByExampleSelective(@Param("record") SxcServiceRule record, @Param("example") SxcServiceRuleExample example);

    int updateByExample(@Param("record") SxcServiceRule record, @Param("example") SxcServiceRuleExample example);
}