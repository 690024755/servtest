package com.galaxyeye.websocket.infrastructure.repository.impl; /*
 * Description:com.galaxyeye.websocket.infrastructure.repository.impl
 * @Date Create on 16:26
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/11/13日galaxyeye All Rights Reserved.
 */

import com.galaxyeye.websocket.application.repository.SysLogRepository;
import com.galaxyeye.websocket.infrastructure.repository.entity.SysLog;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.SysLogExample;
import com.galaxyeye.websocket.infrastructure.repository.mapper.SysLogMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class SysLogRepositoryImpl implements SysLogRepository {

    @Autowired
    private SysLogMapper sysLogMapper;


    @Override
    public Long countByExample(SysLogExample example) {
        return sysLogMapper.countByExample(example);
    }

    @Override
    public Integer deleteByExample(SysLogExample example) {
        return sysLogMapper.deleteByExample(example);
    }

    @Override
    public Integer insert(SysLog record) {
        return sysLogMapper.insert(record);
    }

    @Override
    public Integer insertSelective(SysLog record) {
        return sysLogMapper.insertSelective(record);
    }

    @Override
    public List<SysLog> selectByExample(SysLogExample example) {
        List<SysLog> list=sysLogMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(list)){
            return new ArrayList<>();
        }
        return list;
    }

    @Override
    public Integer updateByExampleSelective(SysLog record, SysLogExample example) {
        return sysLogMapper.updateByExampleSelective(record, example);
    }

    @Override
    public Integer updateByExample(SysLog record, SysLogExample example) {
        return sysLogMapper.updateByExample(record, example);
    }
}
