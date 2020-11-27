package com.galaxyeye.websocket.infrastructure.repository.impl; /*
 * Description:com.galaxyeye.websocket.infrastructure.repository.impl
 * @Date Create on 16:26
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/11/13日galaxyeye All Rights Reserved.
 */

import com.galaxyeye.websocket.application.repository.HealthUserRepository;
import com.galaxyeye.websocket.infrastructure.repository.entity.HealthUser;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.HealthUserExample;
import com.galaxyeye.websocket.infrastructure.repository.mapper.HealthUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class HealthUserRepositoryImpl implements HealthUserRepository {

    @Autowired
    private HealthUserMapper healthUserMapper;


    @Override
    public Integer deleteByExample(HealthUserExample example) {
        return healthUserMapper.deleteByExample(example);
    }

    @Override
    public Integer deleteAll() {
        return healthUserMapper.deleteAll();
    }

    @Override
    public Integer insert(HealthUser record) {
        return healthUserMapper.insert(record);
    }

    @Override
    public Integer insertSelective(HealthUser record) {
        return healthUserMapper.insertSelective(record);
    }

    @Override
    public List<HealthUser> selectByExample(HealthUserExample example) {
        List<HealthUser> list=healthUserMapper.selectByExample(example);
        if(list.isEmpty() || list.size()<=0 || list==null){
            return new ArrayList<>();
        }else{
            return list;
        }
    }

    @Override
    public Integer updateByExampleSelective(HealthUser record, HealthUserExample example) {
        return healthUserMapper.updateByExampleSelective(record,example);
    }
}
