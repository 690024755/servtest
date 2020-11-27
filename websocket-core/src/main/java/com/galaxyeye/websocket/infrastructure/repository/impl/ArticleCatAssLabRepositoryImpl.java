package com.galaxyeye.websocket.infrastructure.repository.impl; /*
 * Description:com.galaxyeye.websocket.infrastructure.repository.impl
 * @Date Create on 16:26
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/11/13日galaxyeye All Rights Reserved.
 */

import com.galaxyeye.websocket.application.repository.ArticleCatAssLabRepository;
import com.galaxyeye.websocket.infrastructure.repository.entity.ArticleCatAssLab;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.ArticleCatAssLabExample;
import com.galaxyeye.websocket.infrastructure.repository.mapper.ArticleCatAssLabMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class ArticleCatAssLabRepositoryImpl implements ArticleCatAssLabRepository{

    @Autowired
    private ArticleCatAssLabMapper ArticleCatAssLabMapper;


    @Override
    public List<ArticleCatAssLab> getByArticleId(Long articleId) {
        List<ArticleCatAssLab> articleCatAssLab=ArticleCatAssLabMapper.getByArticleId(articleId);
        if(CollectionUtils.isEmpty(articleCatAssLab)){
            return new ArrayList<>();
        }
        return articleCatAssLab;
    }

    @Override
    public Integer insertSelective(ArticleCatAssLab record) {
        return ArticleCatAssLabMapper.insertSelective(record);
    }

    @Override
    public Integer deleteByExample(ArticleCatAssLabExample example) {
        return ArticleCatAssLabMapper.deleteByExample(example);
    }


}
