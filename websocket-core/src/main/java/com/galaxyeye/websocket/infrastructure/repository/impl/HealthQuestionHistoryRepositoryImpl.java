package com.galaxyeye.websocket.infrastructure.repository.impl; /*
 * Description:com.galaxyeye.websocket.infrastructure.repository.impl
 * @Date Create on 16:26
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/11/13日galaxyeye All Rights Reserved.
 */

import com.galaxyeye.websocket.application.repository.HealthQuestionHistoryRepository;
import com.galaxyeye.websocket.infrastructure.repository.entity.ArticleCatAssLab;
import com.galaxyeye.websocket.infrastructure.repository.entity.HealthQuestionHistory;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.HealthQuestionHistoryExample;
import com.galaxyeye.websocket.infrastructure.repository.mapper.ArticleCatAssLabMapper;
import com.galaxyeye.websocket.infrastructure.repository.mapper.HealthQuestionHistoryMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class HealthQuestionHistoryRepositoryImpl implements HealthQuestionHistoryRepository {

    @Autowired
    private HealthQuestionHistoryMapper healthQuestionHistoryMapper;


    @Override
    public Integer deleteByExample(HealthQuestionHistoryExample example) {
        return healthQuestionHistoryMapper.deleteByExample(example);
    }

    @Override
    public Integer deleteAll() {
        return healthQuestionHistoryMapper.deleteAll();
    }

    @Override
    public Integer insert(HealthQuestionHistory record) {
        return healthQuestionHistoryMapper.insert(record);
    }

    @Override
    public Integer insertSelective(HealthQuestionHistory record) {
        return healthQuestionHistoryMapper.insertSelective(record);
    }

    @Override
    public List<HealthQuestionHistory> selectByExample(HealthQuestionHistoryExample example) {
        List<HealthQuestionHistory> list=healthQuestionHistoryMapper.selectByExample(example);
        if(list.isEmpty() || list.size()<=0 || list==null){
            return new ArrayList<>();
        }else{
            return list;
        }
    }
}
