package com.galaxyeye.websocket.infrastructure.repository.mapper;

import com.galaxyeye.websocket.infrastructure.repository.entity.SxcFreightRuleMap;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.SxcFreightRuleMapExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SxcFreightRuleMapMapper {
    int insert(SxcFreightRuleMap record);

    int insertSelective(SxcFreightRuleMap record);

    List<SxcFreightRuleMap> selectByExample(SxcFreightRuleMapExample example);

    int updateByExampleSelective(@Param("record") SxcFreightRuleMap record, @Param("example") SxcFreightRuleMapExample example);

    int updateByExample(@Param("record") SxcFreightRuleMap record, @Param("example") SxcFreightRuleMapExample example);
}