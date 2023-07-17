package com.trans.tms.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.trans.tms.entity.OrderClassifyEntity;
import java.util.List;

/**
 * 订单分类
 */
public interface IOrderClassifyService extends IService<OrderClassifyEntity> {
    List<OrderClassifyEntity> findByJobLogId(String id);
}