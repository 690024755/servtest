package com.galaxyeye.websocket.infrastructure.repository.impl; /*
 * Description:com.galaxyeye.websocket.infrastructure.repository.impl
 * @Date Create on 16:33
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/9/25日galaxyeye All Rights Reserved.
 */

import com.galaxyeye.websocket.application.repository.ArticleRepository;
import com.galaxyeye.websocket.application.repository.AssSubTypeRepository;
import com.galaxyeye.websocket.infrastructure.repository.entity.Article;
import com.galaxyeye.websocket.infrastructure.repository.entity.AssLab;
import com.galaxyeye.websocket.infrastructure.repository.entity.AssSubType;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.AssSubTypeExample;
import com.galaxyeye.websocket.infrastructure.repository.mapper.ArticleMapper;
import com.galaxyeye.websocket.infrastructure.repository.mapper.AssLabMapper;
import com.galaxyeye.websocket.infrastructure.repository.mapper.AssSubTypeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Repository
public class AssSubTypeRepositoryImpl implements AssSubTypeRepository {
    @Autowired
    private AssSubTypeMapper assSubTypeMapper;

    @Override
    public Integer insertOne(HashMap<String, Object> hs) {
        return assSubTypeMapper.insertOne(hs);
    }

    @Override
    public Integer insertBatch(List<AssSubType> list) {
        return assSubTypeMapper.insertBatch(list);
    }

    @Override
    public Integer insertSelective(AssSubType record) {
        return assSubTypeMapper.insertSelective(record);
    }

    @Override
    public List<AssSubType> selectByExample(AssSubTypeExample example) {
        List<AssSubType> list=assSubTypeMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(list)){
            return new ArrayList<>();
        }
        return list;
    }

    @Override
    public Long getId() {
        return assSubTypeMapper.getId();
    }

    @Override
    public Integer deleteByExample(AssSubTypeExample example) {
        return assSubTypeMapper.deleteByExample(example);
    }

    @Override
    public Integer updateByExampleSelective(AssSubType record, AssSubTypeExample example) {
        return assSubTypeMapper.updateByExampleSelective(record, example);
    }

    @Override
    public Integer updateByExample(AssSubType record, AssSubTypeExample example) {
        return assSubTypeMapper.updateByExample(record, example);
    }


}
