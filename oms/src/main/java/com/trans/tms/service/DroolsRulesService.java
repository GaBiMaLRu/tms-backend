package com.trans.tms.service;

import com.trans.tms.entity.fact.AddressRule;

public interface DroolsRulesService {
    /**
     * 根据条件计算订单价格
     * @param addressRule
     * @return
     */
    String calcFee(AddressRule addressRule);
}
