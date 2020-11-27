package com.galaxyeye.websocket.application.repository; /*
 * Description:com.galaxyeye.websocket.application.repository
 * @Date Create on 16:26
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/11/13日galaxyeye All Rights Reserved.
 */

import com.galaxyeye.websocket.infrastructure.repository.entity.CaseLog;
import com.galaxyeye.websocket.infrastructure.repository.entity.TKeyindex;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.CaseLogExample;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.TKeyindexExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TKeyindexRepository {

    Integer insert(TKeyindex record);

    Integer insertBatch(List<TKeyindex> recordList);

    Integer insertSelective(TKeyindex record);

    Integer deleteByExample(TKeyindexExample example);

    List<TKeyindex> selectByExample(TKeyindexExample example);


}
