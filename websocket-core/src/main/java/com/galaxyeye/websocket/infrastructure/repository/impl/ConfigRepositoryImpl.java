package com.galaxyeye.websocket.infrastructure.repository.impl; /*
 * Description:com.galaxyeye.websocket.infrastructure.repository.impl
 * @Date Create on 16:26
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/11/13日galaxyeye All Rights Reserved.
 */

import com.galaxyeye.websocket.application.repository.AppidRepository;
import com.galaxyeye.websocket.application.repository.ConfigRepository;
import com.galaxyeye.websocket.infrastructure.repository.entity.Appid;
import com.galaxyeye.websocket.infrastructure.repository.entity.Config;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.AppidExample;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.ConfigExample;
import com.galaxyeye.websocket.infrastructure.repository.mapper.AppidMapper;
import com.galaxyeye.websocket.infrastructure.repository.mapper.ConfigMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class ConfigRepositoryImpl implements ConfigRepository {

    @Autowired
    private ConfigMapper configMapper;


    @Override
    public Long countByExample(ConfigExample example) {
        return null;
    }

    @Override
    public Integer deleteByExample(ConfigExample example) {
        return null;
    }

    @Override
    public Integer insert(Config record) {
        return configMapper.insert(record);
    }

    @Override
    public Integer insertSelective(Config record) {
        return configMapper.insertSelective(record);
    }

    @Override
    public List<Config> selectByExample(ConfigExample example) {
        List<Config> list=configMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(list)){
            return new ArrayList<>();
        }
        return list;
    }

    @Override
    public Integer updateByExampleSelective(Config record, ConfigExample example) {
        return configMapper.updateByExampleSelective(record, example);
    }

    @Override
    public Integer updateByExample(Config record, ConfigExample example) {
        return configMapper.updateByExample(record, example);
    }

}
