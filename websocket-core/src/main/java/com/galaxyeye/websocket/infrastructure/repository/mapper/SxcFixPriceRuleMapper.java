package com.galaxyeye.websocket.infrastructure.repository.mapper;

import com.galaxyeye.websocket.infrastructure.repository.entity.SxcFixPriceRule;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.SxcFixPriceRuleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SxcFixPriceRuleMapper {
    int insert(SxcFixPriceRule record);

    int insertSelective(SxcFixPriceRule record);

    List<SxcFixPriceRule> selectByExample(SxcFixPriceRuleExample example);

    int updateByExampleSelective(@Param("record") SxcFixPriceRule record, @Param("example") SxcFixPriceRuleExample example);

    int updateByExample(@Param("record") SxcFixPriceRule record, @Param("example") SxcFixPriceRuleExample example);

}