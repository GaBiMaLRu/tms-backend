package com.trans.tms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trans.tms.common.utils.CustomIdGenerator;
import com.trans.tms.entity.OrderCargo;
import com.trans.tms.mapper.OrderCargoMapper;
import com.trans.tms.service.IOrderCargoService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 货物服务实现类
 */
@Service
public class OrderCargoServiceImpl extends ServiceImpl<OrderCargoMapper, OrderCargo> implements IOrderCargoService {
    @Autowired
    private CustomIdGenerator idGenerator;

    @Override
    public OrderCargo saveSelective(OrderCargo record) {
        if (record.getId() != null) {
            this.baseMapper.updateByPrimaryKey(record);
        } else {
            record.setId(idGenerator.nextId(record) + "");
            this.baseMapper.insertSelective(record);
        }
        return record;
    }

    @Override
    public List<OrderCargo> findAll(String tranOrderId, String orderId) {
        LambdaQueryWrapper<OrderCargo> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotEmpty(tranOrderId)) {
            queryWrapper.eq(OrderCargo::getTranOrderId, tranOrderId);
        }
        if (StringUtils.isNotEmpty(orderId)) {
            queryWrapper.eq(OrderCargo::getOrderId, orderId);
        }
        queryWrapper.orderBy(true, true, OrderCargo::getId);
        return baseMapper.selectList(queryWrapper);
    }
}
