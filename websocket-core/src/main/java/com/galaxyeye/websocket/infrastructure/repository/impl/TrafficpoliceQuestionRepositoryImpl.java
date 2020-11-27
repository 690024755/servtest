package com.galaxyeye.websocket.infrastructure.repository.impl; /*
 * Description:com.galaxyeye.websocket.infrastructure.repository.impl
 * @Date Create on 16:26
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/11/13日galaxyeye All Rights Reserved.
 */

import com.galaxyeye.websocket.application.repository.TrafficpoliceQuestionRepository;
import com.galaxyeye.websocket.application.repository.TrafficpoliceQuestionUserRepository;
import com.galaxyeye.websocket.infrastructure.repository.entity.TrafficpoliceQuestion;
import com.galaxyeye.websocket.infrastructure.repository.entity.TrafficpoliceQuestionUser;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.TrafficpoliceQuestionExample;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.TrafficpoliceQuestionUserExample;
import com.galaxyeye.websocket.infrastructure.repository.mapper.TrafficpoliceQuestionMapper;
import com.galaxyeye.websocket.infrastructure.repository.mapper.TrafficpoliceQuestionUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class TrafficpoliceQuestionRepositoryImpl implements TrafficpoliceQuestionRepository {

    @Autowired
    private TrafficpoliceQuestionMapper trafficpoliceQuestionMapper;


    @Override
    public Long countByExample(TrafficpoliceQuestionExample example) {
        return trafficpoliceQuestionMapper.countByExample(example);
    }

    @Override
    public Integer deleteByExample(TrafficpoliceQuestionExample example) {
        return trafficpoliceQuestionMapper.deleteByExample(example);
    }

    @Override
    public Integer insert(TrafficpoliceQuestion record) {
        return trafficpoliceQuestionMapper.insert(record);
    }

    @Override
    public Integer insertSelective(TrafficpoliceQuestion record) {
        return trafficpoliceQuestionMapper.insertSelective(record);
    }

    @Override
    public List<TrafficpoliceQuestion> selectAll() {
        List<TrafficpoliceQuestion> list=trafficpoliceQuestionMapper.selectAll();
        if(CollectionUtils.isEmpty(list)){
            return new ArrayList<>();
        }
        return list;
    }


    @Override
    public List<TrafficpoliceQuestion> selectByExample(TrafficpoliceQuestionExample example) {
        List<TrafficpoliceQuestion> list=trafficpoliceQuestionMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(list)){
            return new ArrayList<>();
        }
        return list;
    }


    @Override
    public Integer updateByExampleSelective(TrafficpoliceQuestion record, TrafficpoliceQuestionExample example) {
        return trafficpoliceQuestionMapper.updateByExampleSelective(record,example);
    }

    @Override
    public Integer updateByExample(TrafficpoliceQuestion record, TrafficpoliceQuestionExample example) {
        return trafficpoliceQuestionMapper.updateByExample(record,example);
    }

    @Override
    public Integer updateByAllDeleteTimeIsNull() {
        return trafficpoliceQuestionMapper.updateByAllDeleteTimeIsNull();
    }
}
