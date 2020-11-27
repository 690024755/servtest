package com.galaxyeye.websocket.infrastructure.repository.impl; /*
 * Description:com.galaxyeye.websocket.infrastructure.repository.impl
 * @Date Create on 16:26
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/11/13日galaxyeye All Rights Reserved.
 */

import com.galaxyeye.websocket.application.repository.CaseLogRepository;
import com.galaxyeye.websocket.application.repository.HttpLogRepository;
import com.galaxyeye.websocket.infrastructure.repository.entity.CaseLog;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.CaseLogExample;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.HttpLogExample;
import com.galaxyeye.websocket.infrastructure.repository.mapper.CaseLogMapper;
import com.galaxyeye.websocket.infrastructure.repository.mapper.HttpLogMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Options;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class HttpLogRepositoryImpl implements HttpLogRepository {

    @Autowired
    private HttpLogMapper httpLogMapper;


    @Override
    public long countByExample(HttpLogExample example) {
        return httpLogMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(HttpLogExample example) {
        return httpLogMapper.deleteByExample(example);
    }

    @Override
    public Long insert(HttpLog record) {
        if(httpLogMapper.insert(record)>0){
            return record.getId();
        }else {
            return 0L;
        }
    }

    @Override
    public int insertSelective(HttpLog record) {
        return httpLogMapper.insertSelective(record);
    }

    @Override
    public List<HttpLog> selectByExample(HttpLogExample example) {
        List<HttpLog> list=httpLogMapper.selectByExample(example);
        if(list.isEmpty() || list.size()<=0 || list==null){
            return new ArrayList<>();
        }else{
            return list;
        }
    }

    @Override
    public int updateByExampleSelective(HttpLog record, HttpLogExample example) {
        return httpLogMapper.updateByExampleSelective(record,example);
    }

    @Override
    public int updateByExample(HttpLog record, HttpLogExample example) {
        return httpLogMapper.updateByExample(record,example);
    }
}
