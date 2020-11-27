package com.galaxyeye.websocket.infrastructure.repository.mapper;

import com.galaxyeye.websocket.infrastructure.repository.entity.TbProAccount;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.TbProAccountExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbProAccountMapper {
    Long countByExample(TbProAccountExample example);

    Integer deleteByExample(TbProAccountExample example);

    Integer insert(TbProAccount record);

    Integer insertSelective(TbProAccount record);

    List<TbProAccount> selectByExample(TbProAccountExample example);

    Integer updateByExampleSelective(@Param("record") TbProAccount record, @Param("example") TbProAccountExample example);

    Integer updateByExample(@Param("record") TbProAccount record, @Param("example") TbProAccountExample example);

}