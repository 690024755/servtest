package com.galaxyeye.websocket.infrastructure.repository.mapper;

import com.galaxyeye.websocket.infrastructure.repository.entity.MainType;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.MainTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MainTypeMapper {
    long getId();

    long countByExample(MainTypeExample example);

    int deleteByExample(MainTypeExample example);

    int insert(MainType record);

    int insertSelective(MainType record);

    List<MainType> selectByExample(MainTypeExample example);

    List<MainType> selectAll();

    int updateByExampleSelective(@Param("record") MainType record, @Param("example") MainTypeExample example);

    int updateByExample(@Param("record") MainType record, @Param("example") MainTypeExample example);
}