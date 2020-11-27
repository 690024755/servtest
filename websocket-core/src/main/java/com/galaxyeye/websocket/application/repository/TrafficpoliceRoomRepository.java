package com.galaxyeye.websocket.application.repository; /*
 * Description:com.galaxyeye.websocket.application.repository
 * @Date Create on 16:26
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/11/13日galaxyeye All Rights Reserved.
 */

import com.galaxyeye.websocket.infrastructure.repository.entity.Appid;
import com.galaxyeye.websocket.infrastructure.repository.entity.TrafficpoliceRoom;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.AppidExample;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.TrafficpoliceRoomExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TrafficpoliceRoomRepository {

    Integer countByExample(TrafficpoliceRoomExample example);

    Integer deleteByExample(TrafficpoliceRoomExample example);

    Integer insert(TrafficpoliceRoom record);

    Integer insertSelective(TrafficpoliceRoom record);

    List<TrafficpoliceRoom> selectByExample(TrafficpoliceRoomExample example);

    Integer updateByExampleSelective(@Param("record") TrafficpoliceRoom record, @Param("example") TrafficpoliceRoomExample example);

    Integer updateByExample(@Param("record") TrafficpoliceRoom record, @Param("example") TrafficpoliceRoomExample example);

}
