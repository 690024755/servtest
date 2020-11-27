package com.galaxyeye.websocket.infrastructure.repository.impl; /*
 * Description:com.galaxyeye.websocket.infrastructure.repository.impl
 * @Date Create on 16:34
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/9/25日galaxyeye All Rights Reserved.
 */

import com.galaxyeye.websocket.application.repository.ArticleUserStatisticsRepository;
import com.galaxyeye.websocket.infrastructure.repository.entity.ArticleStatistics;
import com.galaxyeye.websocket.infrastructure.repository.entity.ArticleUserStatistics;
import com.galaxyeye.websocket.infrastructure.repository.mapper.ArticleUserStatisticsMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class ArticleUserStatisticsRepositoryImpl implements ArticleUserStatisticsRepository {

    @Autowired
    private ArticleUserStatisticsMapper articleUserStatisticsMapper;

    @Override
    public int getByArticleId(Long articleId) {
        return articleUserStatisticsMapper.getByArticleId(articleId);
    }

    @Override
    public List<ArticleUserStatistics> getByListUId(List list) {
        List<ArticleUserStatistics> ss=articleUserStatisticsMapper.getByListUId(list);
        if(CollectionUtils.isEmpty(ss)){
            return null;
        }
        return ss;
    }

    @Override
    public List<ArticleUserStatistics> getByUId(Long uid) {
        List<ArticleUserStatistics> ss=articleUserStatisticsMapper.getByUId(uid);
        if(CollectionUtils.isEmpty(ss)){
            return null;
        }
        return ss;
    }


    @Override
    public ArticleUserStatistics getByUIdAndArticleId(Long uid, Long articleId) {
        HashMap<String,Long> hs=new HashMap<>();
        hs.put("uid",uid);
        hs.put("articleId",articleId);
        return articleUserStatisticsMapper.getByUIdAndArticleId(hs);
    }

    @Override
    public int deleteAllArticleUserStatistics() {
        return articleUserStatisticsMapper.deleteAllArticleUserStatistics();
    }

    @Override
    public int deleteByUId(Long uid) {
        return articleUserStatisticsMapper.deleteByUId(uid);
    }

    @Override
    public int deleteByArticleId(Long articleId) {
        return articleUserStatisticsMapper.deleteByArticleId(articleId);
    }

    @Override
    public int deleteByUIdAndArticleId(Long uid, Long articleId) {
        HashMap<String,Long> hs=new HashMap<>();
        hs.put("uid",uid);
        hs.put("articleId",articleId);
        return articleUserStatisticsMapper.deleteByUIdAndArticleId(hs);
    }
}
