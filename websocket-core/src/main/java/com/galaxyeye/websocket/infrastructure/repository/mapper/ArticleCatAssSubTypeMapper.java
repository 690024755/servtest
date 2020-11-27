package com.galaxyeye.websocket.infrastructure.repository.mapper;

import com.galaxyeye.websocket.infrastructure.repository.entity.ArticleCatAssSubType;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.ArticleCatAssSubTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ArticleCatAssSubTypeMapper {

    List<ArticleCatAssSubType> getByArticleId(Long articleId);

    long countByExample(ArticleCatAssSubTypeExample example);

    int deleteByExample(ArticleCatAssSubTypeExample example);

    int insert(ArticleCatAssSubType record);

    int insertSelective(ArticleCatAssSubType record);

    List<ArticleCatAssSubType> selectByExample(ArticleCatAssSubTypeExample example);

    int updateByExampleSelective(@Param("record") ArticleCatAssSubType record, @Param("example") ArticleCatAssSubTypeExample example);

    int updateByExample(@Param("record") ArticleCatAssSubType record, @Param("example") ArticleCatAssSubTypeExample example);
}