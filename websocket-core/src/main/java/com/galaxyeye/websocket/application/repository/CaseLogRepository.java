package com.galaxyeye.websocket.application.repository; /*
 * Description:com.galaxyeye.websocket.application.repository
 * @Date Create on 16:26
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/11/13日galaxyeye All Rights Reserved.
 */

import com.galaxyeye.websocket.infrastructure.repository.entity.CaseLog;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.CaseLogExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CaseLogRepository {

    long countByExample(CaseLogExample example);

    int deleteByExample(CaseLogExample example);

    int insert(CaseLog record);

    int insertSelective(CaseLog record);

    List<CaseLog> selectByExample(CaseLogExample example);

    int updateByExampleSelective(@Param("record") CaseLog record, @Param("example") CaseLogExample example);

    int updateByExample(@Param("record") CaseLog record, @Param("example") CaseLogExample example);


}
