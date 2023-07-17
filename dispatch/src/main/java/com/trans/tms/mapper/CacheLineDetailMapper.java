package com.trans.tms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.trans.tms.entity.CacheLineDetailEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface CacheLineDetailMapper extends BaseMapper<CacheLineDetailEntity> {
}
