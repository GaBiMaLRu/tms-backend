package com.trans.tms.dto;

import com.trans.tms.entity.CacheLineEntity;
import lombok.Data;

import java.util.List;

@Data
public class CacheLineDTO extends CacheLineEntity {

    private List<CacheLineDetailDTO> cacheLineDetailDTOS;

}
