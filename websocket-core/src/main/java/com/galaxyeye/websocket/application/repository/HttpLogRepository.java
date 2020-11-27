package com.galaxyeye.websocket.application.repository; /*
 * Description:com.galaxyeye.websocket.application.repository
 * @Date Create on 16:26
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/11/13日galaxyeye All Rights Reserved.
 */

import com.galaxyeye.websocket.infrastructure.repository.entity.CaseLog;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.CaseLogExample;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.HttpLogExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HttpLogRepository {

    long countByExample(HttpLogExample example);

    int deleteByExample(HttpLogExample example);

    Long insert(HttpLog record);

    int insertSelective(HttpLog record);

    List<HttpLog> selectByExample(HttpLogExample example);

    int updateByExampleSelective(@Param("record") HttpLog record, @Param("example") HttpLogExample example);

    int updateByExample(@Param("record") HttpLog record, @Param("example") HttpLogExample example);

}
