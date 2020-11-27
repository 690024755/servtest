package com.galaxyeye.websocket.infrastructure.repository.mapper;

import com.galaxyeye.websocket.infrastructure.repository.entity.TUserkeys;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.TUserkeysExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TUserkeysMapper {
    long countByExample(TUserkeysExample example);

    int deleteByExample(TUserkeysExample example);

    int insert(TUserkeys record);

    int insertBatch(List<TUserkeys> recordList);

    int insertSelective(TUserkeys record);

    List<TUserkeys> selectByExample(TUserkeysExample example);

    int updateByExampleSelective(@Param("record") TUserkeys record, @Param("example") TUserkeysExample example);

    int updateByExample(@Param("record") TUserkeys record, @Param("example") TUserkeysExample example);
}