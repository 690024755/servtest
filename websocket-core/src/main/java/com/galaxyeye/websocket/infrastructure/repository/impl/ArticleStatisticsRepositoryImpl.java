package com.galaxyeye.websocket.infrastructure.repository.impl; /*
 * Description:com.galaxyeye.websocket.infrastructure.repository.impl
 * @Date Create on 15:35
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/9/25日galaxyeye All Rights Reserved.
 */

import com.galaxyeye.websocket.application.repository.ArticleStatisticsRepository;
import com.galaxyeye.websocket.infrastructure.repository.entity.ArticleStatistics;
import com.galaxyeye.websocket.infrastructure.repository.entity.SxcItemSku;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.ArticleStatisticsExample;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.SxcGoodsGroupRelationExample;
import com.galaxyeye.websocket.infrastructure.repository.mapper.ArticleStatisticsMapper;
import com.galaxyeye.websocket.infrastructure.repository.mapper.SxcItemSkuMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Array;
import java.util.List;

@Slf4j
@Repository
public class ArticleStatisticsRepositoryImpl implements ArticleStatisticsRepository {

    @Autowired
    private ArticleStatisticsMapper articleStatisticsMapper;

    @Override
    public ArticleStatistics getByArticleId(Long articleId) {
        return articleStatisticsMapper.getByArticleId(articleId);
    }



    @Override
    public List<ArticleStatistics> getByListArticleId(List list) {
        List<ArticleStatistics> ss=articleStatisticsMapper.getByListArticleId(list);
        if(CollectionUtils.isEmpty(ss)){
            return null;
        }
        return ss;
    }

    @Override
    public int deleteAllArticleStatistics() {
        return articleStatisticsMapper.deleteAllArticleStatistics();
    }

    @Override
    public int deleteByArticleId(Long articleId) {
        return articleStatisticsMapper.deleteByArticleId(articleId);
    }


    //TODO
    @Override
    public List<ArticleStatistics> selectByExample(ArticleStatisticsExample example) {
        return null;
    }
}
