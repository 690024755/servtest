package com.galaxyeye.websocket.infrastructure.repository.mapper;

import com.galaxyeye.websocket.infrastructure.repository.entity.CaseLog;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.CaseLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CaseLogMapper {
    long countByExample(CaseLogExample example);

    int deleteByExample(CaseLogExample example);

    int insert(CaseLog record);

    int insertSelective(CaseLog record);

    List<CaseLog> selectByExample(CaseLogExample example);

    int updateByExampleSelective(@Param("record") CaseLog record, @Param("example") CaseLogExample example);

    int updateByExample(@Param("record") CaseLog record, @Param("example") CaseLogExample example);
}