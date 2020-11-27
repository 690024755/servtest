package com.galaxyeye.websocket.application.repository; /*
 * Description:com.galaxyeye.websocket.application.repository
 * @Date Create on 16:26
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/11/13日galaxyeye All Rights Reserved.
 */

import com.galaxyeye.websocket.infrastructure.repository.entity.TUser;
import com.galaxyeye.websocket.infrastructure.repository.entity.TUserkeys;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.TUserExample;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.TUserkeysExample;

import java.util.List;

public interface TUserkeysRepository {

    Integer deleteByExample(TUserkeysExample example);

    Integer insert(TUserkeys record);

    Integer insertBatch(List<TUserkeys> recordList);

    Integer insertSelective(TUserkeys record);

    List<TUserkeys> selectByExample(TUserkeysExample example);


}
