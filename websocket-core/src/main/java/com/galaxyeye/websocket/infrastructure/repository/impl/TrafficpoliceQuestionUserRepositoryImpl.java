package com.galaxyeye.websocket.infrastructure.repository.impl; /*
 * Description:com.galaxyeye.websocket.infrastructure.repository.impl
 * @Date Create on 16:26
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/11/13日galaxyeye All Rights Reserved.
 */

import com.galaxyeye.websocket.application.repository.AppidRepository;
import com.galaxyeye.websocket.application.repository.TrafficpoliceQuestionUserRepository;
import com.galaxyeye.websocket.infrastructure.repository.entity.Appid;
import com.galaxyeye.websocket.infrastructure.repository.entity.TrafficpoliceQuestionUser;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.AppidExample;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.TrafficpoliceQuestionUserExample;
import com.galaxyeye.websocket.infrastructure.repository.mapper.AppidMapper;
import com.galaxyeye.websocket.infrastructure.repository.mapper.TrafficpoliceQuestionUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class TrafficpoliceQuestionUserRepositoryImpl implements TrafficpoliceQuestionUserRepository {

    @Autowired
    private TrafficpoliceQuestionUserMapper trafficpoliceQuestionUserMapper;




    @Override
    public Long countByExample(TrafficpoliceQuestionUserExample example) {
        return trafficpoliceQuestionUserMapper.countByExample(example);
    }

    @Override
    public Integer deleteByExample(TrafficpoliceQuestionUserExample example) {
        return trafficpoliceQuestionUserMapper.deleteByExample(example);
    }

    @Override
    public Integer deleteAll() {
        return trafficpoliceQuestionUserMapper.deleteAll();
    }

    @Override
    public Integer insert(TrafficpoliceQuestionUser record) {
        return trafficpoliceQuestionUserMapper.insert(record);
    }

    @Override
    public Integer insertSelective(TrafficpoliceQuestionUser record) {
        return trafficpoliceQuestionUserMapper.insertSelective(record);
    }

    @Override
    public List<TrafficpoliceQuestionUser> selectByExample(TrafficpoliceQuestionUserExample example) {
        List<TrafficpoliceQuestionUser> list=trafficpoliceQuestionUserMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(list)){
            return new ArrayList<>();
        }
        return list;
    }

    @Override
    public Integer updateByExampleSelective(TrafficpoliceQuestionUser record, TrafficpoliceQuestionUserExample example) {
        return trafficpoliceQuestionUserMapper.updateByExampleSelective(record,example);
    }

    @Override
    public Integer updateByExample(TrafficpoliceQuestionUser record, TrafficpoliceQuestionUserExample example) {
        return trafficpoliceQuestionUserMapper.updateByExample(record,example);
    }
}
