package com.galaxyeye.websocket.infrastructure.repository.impl; /*
 * Description:com.galaxyeye.websocket.infrastructure.repository.impl
 * @Date Create on 16:35
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/9/25日galaxyeye All Rights Reserved.
 */

import com.galaxyeye.websocket.application.repository.UserCollectStatisticsRepository;
import com.galaxyeye.websocket.infrastructure.repository.entity.ArticleUserStatistics;
import com.galaxyeye.websocket.infrastructure.repository.entity.UserCollectStatistics;
import com.galaxyeye.websocket.infrastructure.repository.mapper.ArticleUserStatisticsMapper;
import com.galaxyeye.websocket.infrastructure.repository.mapper.UserCollectStatisticsMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class UserCollectStatisticsRepositoryImpl implements UserCollectStatisticsRepository {
    @Autowired
    private UserCollectStatisticsMapper userCollectStatisticsMapper;

    @Override
    public Integer getByArticleId(Long articleId) {
        return userCollectStatisticsMapper.getByArticleId(articleId);
    }

    @Override
    public List<UserCollectStatistics> getByListUId(List list) {
        List<UserCollectStatistics> ss=userCollectStatisticsMapper.getByListUId(list);
        if(CollectionUtils.isEmpty(ss)){
            return null;
        }
        return ss;
    }

    @Override
    public List<UserCollectStatistics> getByUId(Long uid) {
        List<UserCollectStatistics> ss=userCollectStatisticsMapper.getByUId(uid);
        if(CollectionUtils.isEmpty(ss)){
            return null;
        }
        return ss;
    }

    @Override
    public Integer getByUIdAndArticleId(Long uid, Long articleId) {
        HashMap<String,Long> hs=new HashMap<>();
        hs.put("uid",uid);
        hs.put("articleId",articleId);
        return userCollectStatisticsMapper.getByUIdAndArticleId(hs);
    }

    @Override
    public int deleteAllUserCollectStatistics() {
        return userCollectStatisticsMapper.deleteAllUserCollectStatistics();
    }

    @Override
    public int deleteByUId(Long uid) {
        return userCollectStatisticsMapper.deleteByUId(uid);
    }

    @Override
    public int deleteByArticleId(Long articleId) {
        return userCollectStatisticsMapper.deleteByArticleId(articleId);
    }

    @Override
    public int deleteByUIdAndArticleId(Long uid, Long articleId) {
        HashMap<String,Long> hs=new HashMap<>();
        hs.put("uid",uid);
        hs.put("articleId",articleId);
        return userCollectStatisticsMapper.deleteByUIdAndArticleId(hs);
    }

}
