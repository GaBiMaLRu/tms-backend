package com.trans.tms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.trans.tms.entity.OrderClassifyOrderEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface OrderClassifyOrderMapper extends BaseMapper<OrderClassifyOrderEntity> {
}
