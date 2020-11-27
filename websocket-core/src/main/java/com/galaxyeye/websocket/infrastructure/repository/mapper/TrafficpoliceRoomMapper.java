package com.galaxyeye.websocket.infrastructure.repository.mapper;

import com.galaxyeye.websocket.infrastructure.repository.entity.TrafficpoliceRoom;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.TrafficpoliceRoomExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TrafficpoliceRoomMapper {
    Integer countByExample(TrafficpoliceRoomExample example);

    Integer deleteByExample(TrafficpoliceRoomExample example);

    Integer insert(TrafficpoliceRoom record);

    Integer insertSelective(TrafficpoliceRoom record);

    List<TrafficpoliceRoom> selectByExample(TrafficpoliceRoomExample example);

    Integer updateByExampleSelective(@Param("record") TrafficpoliceRoom record, @Param("example") TrafficpoliceRoomExample example);

    Integer updateByExample(@Param("record") TrafficpoliceRoom record, @Param("example") TrafficpoliceRoomExample example);
}