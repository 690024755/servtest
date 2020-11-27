package com.galaxyeye.websocket.infrastructure.repository.mapper;

import com.galaxyeye.websocket.infrastructure.repository.entity.Euser;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.EuserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EuserMapper {
    long countByExample(EuserExample example);

    int deleteByExample(EuserExample example);

    int insert(Euser record);

    int insertSelective(Euser record);

    List<Euser> selectByExample(EuserExample example);

    int updateByExampleSelective(@Param("record") Euser record, @Param("example") EuserExample example);

    int updateByExample(@Param("record") Euser record, @Param("example") EuserExample example);
}