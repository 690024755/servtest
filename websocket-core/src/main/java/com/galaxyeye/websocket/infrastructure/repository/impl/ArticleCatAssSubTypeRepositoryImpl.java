package com.galaxyeye.websocket.infrastructure.repository.impl; /*
 * Description:com.galaxyeye.websocket.infrastructure.repository.impl
 * @Date Create on 16:26
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/11/13日galaxyeye All Rights Reserved.
 */

import com.galaxyeye.websocket.application.repository.ArticleCatAssLabRepository;
import com.galaxyeye.websocket.application.repository.ArticleCatAssSubTypeRepository;
import com.galaxyeye.websocket.infrastructure.repository.entity.ArticleCatAssLab;
import com.galaxyeye.websocket.infrastructure.repository.entity.ArticleCatAssSubType;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.ArticleCatAssSubTypeExample;
import com.galaxyeye.websocket.infrastructure.repository.mapper.ArticleCatAssLabMapper;
import com.galaxyeye.websocket.infrastructure.repository.mapper.ArticleCatAssSubTypeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Slf4j
@Repository
public class ArticleCatAssSubTypeRepositoryImpl implements ArticleCatAssSubTypeRepository {

    @Autowired
    private ArticleCatAssSubTypeMapper articleCatAssSubTypeMapper;

    @Override
    public List<ArticleCatAssSubType> getByArticleId(Long articleId) {
        List<ArticleCatAssSubType> articleCatAssSubType=articleCatAssSubTypeMapper.getByArticleId(articleId);
        if(CollectionUtils.isEmpty(articleCatAssSubType)){
            return null;
        }
        return articleCatAssSubType;
    }

    @Override
    public Integer insertSelective(ArticleCatAssSubType record) {
        return articleCatAssSubTypeMapper.insertSelective(record);
    }

    @Override
    public Integer deleteByExample(ArticleCatAssSubTypeExample example) {
        return articleCatAssSubTypeMapper.deleteByExample(example);
    }


}
