package com.galaxyeye.websocket.infrastructure.repository.impl; /*
 * Description:com.galaxyeye.websocket.infrastructure.repository.impl
 * @Date Create on 16:26
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/11/13日galaxyeye All Rights Reserved.
 */

import com.galaxyeye.websocket.application.repository.AppidRepository;
import com.galaxyeye.websocket.application.repository.ArticleCatAssLabRepository;
import com.galaxyeye.websocket.infrastructure.repository.entity.Appid;
import com.galaxyeye.websocket.infrastructure.repository.entity.ArticleCatAssLab;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.AppidExample;
import com.galaxyeye.websocket.infrastructure.repository.mapper.AppidMapper;
import com.galaxyeye.websocket.infrastructure.repository.mapper.ArticleCatAssLabMapper;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class AppidRepositoryImpl implements AppidRepository {

    @Autowired
    private AppidMapper appidMapper;


    @Override
    public Integer updateSetDeletedAtValueIsNull(Appid record,AppidExample example) {
        return appidMapper.updateSetDeletedAtValueIsNull(record,example);
    }

    @Override
    public Integer deleteByExample(AppidExample example) {
        return appidMapper.deleteByExample(example);
    }

    @Override
    public Integer insert(Appid record) {
        return appidMapper.insert(record);
    }

    @Override
    public Integer insertSelective(Appid record) {
        return appidMapper.insertSelective(record);
    }

    @Override
    public List<Appid> selectByExample(AppidExample example) {
        List<Appid> list=appidMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(list)){
            return new ArrayList<>();
        }
        return list;
    }

    @Override
    public Integer updateByExampleSelective(Appid record, AppidExample example) {
        return appidMapper.updateByExampleSelective(record,example);
    }

    @Override
    public Integer updateByExample(Appid record, AppidExample example) {
        return appidMapper.updateByExample(record,example);
    }
}
