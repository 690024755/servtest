package com.galaxyeye.websocket.infrastructure.repository.impl; /*
 * Description:com.galaxyeye.websocket.infrastructure.repository.impl
 * @Date Create on 16:26
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/11/13日galaxyeye All Rights Reserved.
 */

import com.galaxyeye.websocket.application.repository.AppidRepository;
import com.galaxyeye.websocket.application.repository.TrafficpoliceRoomRepository;
import com.galaxyeye.websocket.infrastructure.repository.entity.Appid;
import com.galaxyeye.websocket.infrastructure.repository.entity.TrafficpoliceRoom;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.AppidExample;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.TrafficpoliceRoomExample;
import com.galaxyeye.websocket.infrastructure.repository.mapper.AppidMapper;
import com.galaxyeye.websocket.infrastructure.repository.mapper.TrafficpoliceRoomMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class TrafficpoliceRoomRepositoryImpl implements TrafficpoliceRoomRepository {

    @Autowired
    private TrafficpoliceRoomMapper trafficpoliceRoomMapper;


    @Override
    public Integer countByExample(TrafficpoliceRoomExample example) {
        return trafficpoliceRoomMapper.countByExample(example);
    }

    @Override
    public Integer deleteByExample(TrafficpoliceRoomExample example) {
        return trafficpoliceRoomMapper.deleteByExample(example);
    }

    @Override
    public Integer insert(TrafficpoliceRoom record) {
        return trafficpoliceRoomMapper.insert(record);
    }

    @Override
    public Integer insertSelective(TrafficpoliceRoom record) {
        return trafficpoliceRoomMapper.insertSelective(record);
    }

    @Override
    public List<TrafficpoliceRoom> selectByExample(TrafficpoliceRoomExample example) {
        List<TrafficpoliceRoom> list=trafficpoliceRoomMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(list)){
            return new ArrayList<>();
        }
        return list;
    }

    @Override
    public Integer updateByExampleSelective(TrafficpoliceRoom record, TrafficpoliceRoomExample example) {
        return trafficpoliceRoomMapper.updateByExampleSelective(record,example);
    }

    @Override
    public Integer updateByExample(TrafficpoliceRoom record, TrafficpoliceRoomExample example) {
        return trafficpoliceRoomMapper.updateByExample(record,example);
    }
}
