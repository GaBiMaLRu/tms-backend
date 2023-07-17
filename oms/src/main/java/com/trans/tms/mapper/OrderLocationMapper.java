package com.trans.tms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.trans.tms.entity.OrderLocation;
import org.apache.ibatis.annotations.Mapper;

/**
 * 位置信息
 */
@Mapper
public interface OrderLocationMapper extends BaseMapper<OrderLocation> {
}
