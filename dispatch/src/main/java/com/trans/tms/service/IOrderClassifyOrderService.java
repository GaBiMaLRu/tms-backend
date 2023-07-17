package com.trans.tms.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.trans.tms.entity.OrderClassifyOrderEntity;

/**
 * 订单分类关联订单
 */
public interface IOrderClassifyOrderService extends IService<OrderClassifyOrderEntity> {
    String getClassifyId(String orderId);
}