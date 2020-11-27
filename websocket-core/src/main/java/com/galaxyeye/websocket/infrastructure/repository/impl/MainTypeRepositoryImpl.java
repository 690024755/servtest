package com.galaxyeye.websocket.infrastructure.repository.impl; /*
 * Description:com.galaxyeye.websocket.infrastructure.repository.impl
 * @Date Create on 16:33
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/9/25日galaxyeye All Rights Reserved.
 */

import com.galaxyeye.websocket.application.repository.AssLabRepository;
import com.galaxyeye.websocket.application.repository.MainTypeRepository;
import com.galaxyeye.websocket.infrastructure.repository.entity.AssLab;
import com.galaxyeye.websocket.infrastructure.repository.entity.CaseLog;
import com.galaxyeye.websocket.infrastructure.repository.entity.MainType;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.MainTypeExample;
import com.galaxyeye.websocket.infrastructure.repository.mapper.AssLabMapper;
import com.galaxyeye.websocket.infrastructure.repository.mapper.MainTypeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Repository
public class MainTypeRepositoryImpl implements MainTypeRepository {

    @Autowired
    private MainTypeMapper mainTypeMapper;


    @Override
    public Integer insertSelective(MainType record) {
        return mainTypeMapper.insertSelective(record);
    }

    /**
     * 获取自增的id值
     * @return
     */
    @Override
    public Long getId() {
        return mainTypeMapper.getId();
    }


    @Override
    public List<MainType> selectAll() {

        List<MainType> list=mainTypeMapper.selectAll();
        if(list.isEmpty() || list.size()<=0 || list==null){
            return new ArrayList<>();
        }else{
            return list;
        }
    }

    @Override
    public Integer deleteByExample(MainTypeExample example) {
        return mainTypeMapper.deleteByExample(example);
    }

    @Override
    public List<MainType> selectByExample(MainTypeExample example) {
        List<MainType> list=mainTypeMapper.selectByExample(example);
        if(list.isEmpty() || list.size()<=0 || list==null){
            return new ArrayList<>();
        }else{
            return list;
        }
    }

    @Override
    public Integer updateByExampleSelective(MainType record, MainTypeExample example) {
        return mainTypeMapper.updateByExampleSelective(record,example);
    }

    @Override
    public Integer updateByExample(MainType record, MainTypeExample example) {
        return mainTypeMapper.updateByExample(record,example);
    }

}
