package com.galaxyeye.websocket.infrastructure.repository.impl; /*
 * Description:com.galaxyeye.websocket.infrastructure.repository.impl
 * @Date Create on 16:33
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/9/25日galaxyeye All Rights Reserved.
 */

import com.galaxyeye.websocket.application.repository.ArticleRepository;
import com.galaxyeye.websocket.infrastructure.repository.entity.Article;
import com.galaxyeye.websocket.infrastructure.repository.entity.ArticleStatistics;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.ArticleExample;
import com.galaxyeye.websocket.infrastructure.repository.mapper.ArticleMapper;
import com.galaxyeye.websocket.infrastructure.repository.mapper.ArticleStatisticsMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@Repository
public class ArticleRepositoryImpl implements ArticleRepository {
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public List<Article> getByListArticleId(List list) {
        List<Article> ss=articleMapper.getByListArticleId(list);
        if(CollectionUtils.isEmpty(ss)){
            return null;
        }
        return ss;
    }

    @Override
    public List<Article> getByAllArticle() {
        List<Article> ss=articleMapper.getByAllArticle();
        if(CollectionUtils.isEmpty(ss)){
            return new ArrayList<>();
        }
        return ss;
    }

    @Override
    public Article getByArticleId(Long articleId) {
        Article article=articleMapper.getByArticleId(articleId);
        if(article ==null){
            return null;
        }
        return article;
    }

    @Override
    public Integer insert(Article record) {
        return articleMapper.insert(record);
    }

    @Override
    public Integer insertSelective(Article record) {
        return articleMapper.insertSelective(record);
    }

    @Override
    public Integer deleteByExample(ArticleExample example) {
        return articleMapper.deleteByExample(example);
    }

    @Override
    public List<Article> selectByExample(ArticleExample example) {
        List<Article> list=articleMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(list)){
            return new ArrayList<>();
        }
        return list;
    }

    @Override
    public Integer updateByExampleSelective(Article record, ArticleExample example) {
        return articleMapper.updateByExampleSelective(record,example);
    }

}
