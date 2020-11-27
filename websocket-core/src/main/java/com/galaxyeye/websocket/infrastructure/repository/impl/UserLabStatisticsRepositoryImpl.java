package com.galaxyeye.websocket.infrastructure.repository.impl; /*
 * Description:com.galaxyeye.websocket.infrastructure.repository.impl
 * @Date Create on 16:35
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/9/25日galaxyeye All Rights Reserved.
 */

import com.galaxyeye.websocket.application.repository.UserLabStatisticsRepository;
import com.galaxyeye.websocket.infrastructure.repository.entity.UserLabStatistics;
import com.galaxyeye.websocket.infrastructure.repository.mapper.UserLabStatisticsMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Repository
public class UserLabStatisticsRepositoryImpl implements UserLabStatisticsRepository {
    @Autowired
    private UserLabStatisticsMapper userLabStatisticsMapper;


    @Override
    public List<UserLabStatistics> getByUId(Long uid) {
        List<UserLabStatistics> ss=userLabStatisticsMapper.getByUId(uid);
        if(CollectionUtils.isEmpty(ss)){
            return null;
        }
        return ss;
    }

    @Override
    public Integer getUserSubTypeScoreByUIdAndSubType(Long uid,List subType) {
        HashMap<String,Object> hs=new HashMap<>();
        hs.put("uid",uid);
        hs.put("subType",subType);
        return userLabStatisticsMapper.getUserSubTypeScoreByUIdAndSubType(hs);
    }

    @Override
    public Integer getTesSubTypeScoreByUIdAndSubType(Long uid,List subType) {
        HashMap<String,Object> hs=new HashMap<>();
        hs.put("uid",uid);
        hs.put("subType",subType);
        return userLabStatisticsMapper.getTesSubTypeScoreByUIdAndSubType(hs);
    }

    @Override
    public Integer getUserLabScoreByUIdAndLab(Long uid,List lab) {
        HashMap<String,Object> hs=new HashMap<>();
        hs.put("uid",uid);
        hs.put("lab",lab);
        return userLabStatisticsMapper.getUserLabScoreByUIdAndLab(hs);
    }

    @Override
    public Integer getTesLabScoreByUIdAndLab(Long uid,List lab) {
        HashMap<String,Object> hs=new HashMap<>();
        hs.put("uid",uid);
        hs.put("lab",lab);
        return userLabStatisticsMapper.getTesLabScoreByUIdAndLab(hs);
    }

    @Override
    public int deleteAllUserLabStatistics() {
        return userLabStatisticsMapper.deleteAllUserLabStatistics();
    }

    @Override
    public int deleteByUId(Long uid) {
        return userLabStatisticsMapper.deleteByUId(uid);
    }
}
