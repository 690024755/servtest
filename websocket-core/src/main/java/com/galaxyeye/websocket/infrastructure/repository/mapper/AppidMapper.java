package com.galaxyeye.websocket.infrastructure.repository.mapper;

import com.galaxyeye.websocket.infrastructure.repository.entity.Appid;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.AppidExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AppidMapper {
    long countByExample(AppidExample example);

    int deleteByExample(AppidExample example);

    int insert(Appid record);

    int insertSelective(Appid record);

    int updateSetDeletedAtValueIsNull(@Param("record") Appid record, @Param("example") AppidExample example);

    List<Appid> selectByExample(AppidExample example);

    int updateByExampleSelective(@Param("record") Appid record, @Param("example") AppidExample example);

    int updateByExample(@Param("record") Appid record, @Param("example") AppidExample example);
}