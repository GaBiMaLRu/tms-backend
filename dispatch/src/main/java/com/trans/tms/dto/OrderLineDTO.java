package com.trans.tms.dto;

import com.trans.tms.entity.CacheLineDetailEntity;
import lombok.Data;

@Data
public class OrderLineDTO {

    private CacheLineDetailEntity cacheLineDetailEntity;

    private OrderClassifyGroupDTO orderClassifyGroupDTO;

    public OrderLineDTO(CacheLineDetailEntity cacheLineDetailEntity,OrderClassifyGroupDTO orderClassifyGroupDTO) {
        this.cacheLineDetailEntity = cacheLineDetailEntity;
        this.orderClassifyGroupDTO = orderClassifyGroupDTO;
    }
}
