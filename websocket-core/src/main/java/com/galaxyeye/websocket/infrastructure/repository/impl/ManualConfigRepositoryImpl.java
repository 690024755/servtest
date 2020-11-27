package com.galaxyeye.websocket.infrastructure.repository.impl; /*
 * Description:com.galaxyeye.websocket.infrastructure.repository.impl
 * @Date Create on 16:11
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/5/8日galaxyeye All Rights Reserved.
 */

import com.galaxyeye.websocket.application.repository.ManualConfigRepository;
import com.galaxyeye.websocket.infrastructure.repository.entity.ManualConfig;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.ManualConfigExample;
import com.galaxyeye.websocket.infrastructure.repository.mapper.ManualConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ManualConfigRepositoryImpl implements ManualConfigRepository {

    @Autowired
    private ManualConfigMapper manualConfigMapper;

    @Override
    public Integer insertBatch(List<ManualConfig> list) {
        return manualConfigMapper.insertBatch(list);
    }

    @Override
    public Integer insert(ManualConfig record) {

        return manualConfigMapper.insert(record);
    }

    @Override
    public Integer insertSelective(ManualConfig record) {

        return manualConfigMapper.insertSelective(record);
    }

    public Integer deleteByExample(ManualConfigExample example) {
        return manualConfigMapper.deleteByExample(example);
    }

    public List<ManualConfig> selectByExample(ManualConfigExample example) {

        List<ManualConfig> list = manualConfigMapper.selectByExample(example);
        if (list.size() <= 0 || list == null || list.isEmpty()) {
            return new ArrayList<>();
        } else {
            return list;
        }

    }

    @Override
    public Integer updateByExampleSelective(ManualConfig record, ManualConfigExample example) {
        return manualConfigMapper.updateByExampleSelective(record, example);
    }


}
