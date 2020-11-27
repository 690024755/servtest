package com.galaxyeye.websocket.infrastructure.repository.mapper;

import com.galaxyeye.websocket.infrastructure.repository.entity.SxcOrder;
import java.util.HashMap;
import java.util.List;

public interface SxcOrderMapper {

    List<SxcOrder> getByBuyerId(Integer buyerId);

    int modifyStatee(HashMap<String,Object> hs);

    int updateBatch(List<SxcOrder> list);

	int insertBatch(List<SxcOrder> list);

	int insertOneBatch(List<SxcOrder> list);

}