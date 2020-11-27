package com.galaxyeye.websocket.infrastructure.repository.impl; /*
 * Description:com.galaxyeye.websocket.infrastructure.repository.impl
 * @Date Create on 16:26
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/11/13日galaxyeye All Rights Reserved.
 */

import com.galaxyeye.websocket.application.repository.XmlCaseRepository;
import com.galaxyeye.websocket.infrastructure.repository.entity.XmlCase;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.XmlCaseExample;
import com.galaxyeye.websocket.infrastructure.repository.mapper.XmlCaseMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class XmlCaseRepositoryImpl implements XmlCaseRepository {

    @Autowired
    private XmlCaseMapper xmlCaseMapper;


    @Override
    public Long countByExample(XmlCaseExample example) {
        return xmlCaseMapper.countByExample(example);
    }

    @Override
    public Integer deleteByExample(XmlCaseExample example) {
        return xmlCaseMapper.deleteByExample(example);
    }

    @Override
    public Integer deleteAll() {
        return xmlCaseMapper.deleteAll();
    }

    @Override
    public Integer insert(XmlCase record) {
        return xmlCaseMapper.insert(record);
    }

    @Override
    public Integer insertSelective(XmlCase record) {
        return xmlCaseMapper.insertSelective(record);
    }

    @Override
    public List<XmlCase> selectByExample(XmlCaseExample example) {
        List<XmlCase> list=xmlCaseMapper.selectByExample(example);
        if(list.isEmpty() || list.size()<=0 || list==null){
            return new ArrayList<>();
        }else{
            return list;
        }
    }

    @Override
    public List<String> selectAllModuleName() {
        List<XmlCase> list=xmlCaseMapper.selectAllModuleName();
        if(list.isEmpty() || list.size()<=0 || list==null){
            return new ArrayList<>();
        }else{
            return list.stream().map(x->x.getModuleName()).collect(Collectors.toList());
        }
    }
}
