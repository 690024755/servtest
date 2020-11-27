package com.galaxyeye.websocket.infrastructure.repository.impl; /*
 * Description:com.galaxyeye.websocket.infrastructure.repository.impl
 * @Date Create on 16:33
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/9/25日galaxyeye All Rights Reserved.
 */

import com.galaxyeye.websocket.application.repository.ArticleRepository;
import com.galaxyeye.websocket.application.repository.AssLabRepository;
import com.galaxyeye.websocket.infrastructure.repository.entity.Article;
import com.galaxyeye.websocket.infrastructure.repository.entity.AssLab;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.AssLabExample;
import com.galaxyeye.websocket.infrastructure.repository.mapper.ArticleMapper;
import com.galaxyeye.websocket.infrastructure.repository.mapper.AssLabMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Repository
public class AssLabRepositoryImpl implements AssLabRepository {

    @Autowired
    private AssLabMapper assLabMapper;

    @Override
    public Integer insertOne(HashMap<String, Object> hs) {
        return assLabMapper.insertOne(hs);
    }

    @Override
    public Integer insertBatch(List<AssLab> list) {
        return assLabMapper.insertBatch(list);
    }

    @Override
    public Integer insertSelective(AssLab record) {
        return assLabMapper.insertSelective(record);
    }

    @Override
    public List<AssLab> selectByExample(AssLabExample example) {
        List<AssLab> list=assLabMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(list)){
            return new ArrayList<>();
        }
        return list;
    }

    @Override
    public Long getId(){
        return assLabMapper.getId();
    }

    @Override
    public Integer deleteByExample(AssLabExample example) {
        return assLabMapper.deleteByExample(example);
    }

    @Override
    public Integer updateByExampleSelective(AssLab record, AssLabExample example) {
        return assLabMapper.updateByExampleSelective(record, example);
    }

    @Override
    public Integer updateByExample(AssLab record, AssLabExample example) {
        return assLabMapper.updateByExample(record, example);
    }


}
