package com.galaxyeye.websocket.infrastructure.repository.mapper;

import com.galaxyeye.websocket.infrastructure.repository.entity.SxcGoodsGroupRelation;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.SxcGoodsGroupRelationExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SxcGoodsGroupRelationMapper {
    int insert(SxcGoodsGroupRelation record);

    int insertSelective(SxcGoodsGroupRelation record);

    List<SxcGoodsGroupRelation> selectByExample(SxcGoodsGroupRelationExample example);

    int updateByExampleSelective(@Param("record") SxcGoodsGroupRelation record, @Param("example") SxcGoodsGroupRelationExample example);

    int updateByExample(@Param("record") SxcGoodsGroupRelation record, @Param("example") SxcGoodsGroupRelationExample example);
}