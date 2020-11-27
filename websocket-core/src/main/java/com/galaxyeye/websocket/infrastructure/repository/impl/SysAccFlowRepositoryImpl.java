package com.galaxyeye.websocket.infrastructure.repository.impl; /*
 * Description:com.galaxyeye.websocket.infrastructure.repository.impl
 * @Date Create on 16:26
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/11/13日galaxyeye All Rights Reserved.
 */

import com.galaxyeye.websocket.application.repository.SysAccFlowRepository;
import com.galaxyeye.websocket.infrastructure.repository.entity.SysAccFlow;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.SysAccFlowExample;
import com.galaxyeye.websocket.infrastructure.repository.mapper.SysAccFlowMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class SysAccFlowRepositoryImpl implements SysAccFlowRepository {

    @Autowired
    private SysAccFlowMapper sysAccFlowMapper;

    @Override
    public Long countByExample(SysAccFlowExample example) {
        return sysAccFlowMapper.countByExample(example);
    }

    @Override
    public Integer deleteByExample(SysAccFlowExample example) {
        return sysAccFlowMapper.deleteByExample(example);
    }

    @Override
    public Integer insert(SysAccFlow record) {
        return sysAccFlowMapper.insert(record);
    }

    @Override
    public Integer insertSelective(SysAccFlow record) {
        return sysAccFlowMapper.insertSelective(record);
    }

    @Override
    public List<SysAccFlow> selectByExample(SysAccFlowExample example) {
        List<SysAccFlow> list=sysAccFlowMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(list)){
            return new ArrayList<>();
        }
        return list;
    }

    @Override
    public Integer updateByExampleSelective(SysAccFlow record, SysAccFlowExample example) {
        return sysAccFlowMapper.updateByExampleSelective(record,example);
    }

    @Override
    public Integer updateByExample(SysAccFlow record, SysAccFlowExample example) {
        return sysAccFlowMapper.updateByExample(record, example);
    }

}
