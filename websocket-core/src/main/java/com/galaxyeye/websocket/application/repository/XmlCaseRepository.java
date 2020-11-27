package com.galaxyeye.websocket.application.repository; /*
 * Description:com.galaxyeye.websocket.application.repository
 * @Date Create on 16:26
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/11/13日galaxyeye All Rights Reserved.
 */

import com.galaxyeye.websocket.infrastructure.repository.entity.ArticleCatAssLab;
import com.galaxyeye.websocket.infrastructure.repository.entity.XmlCase;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.XmlCaseExample;

import java.util.List;

public interface XmlCaseRepository {

    Long countByExample(XmlCaseExample example);

    Integer deleteByExample(XmlCaseExample example);

    Integer deleteAll();

    Integer insert(XmlCase record);

    Integer insertSelective(XmlCase record);

    List<XmlCase> selectByExample(XmlCaseExample example);

    List<String> selectAllModuleName();

}
