package com.trans.tms.service.transportline.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trans.tms.common.CustomIdGenerator;
import com.trans.tms.common.utils.Constant;
import com.trans.tms.mapper.transportline.PdTransportTripsMapper;
import com.trans.tms.entity.transportline.PdTransportTrips;
import com.trans.tms.service.transportline.IPdTransportTripsService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 车次信息表 服务实现类
 * </p>
 *
 * @author author
 * @since 2019-12-20
 */
@Service
public class PdTransportTripsServiceImpl extends ServiceImpl<PdTransportTripsMapper, PdTransportTrips>
        implements IPdTransportTripsService {
    @Autowired
    private CustomIdGenerator idGenerator;

    @Override
    public PdTransportTrips saveTransportTrips(PdTransportTrips pdTransportTrips) {
        pdTransportTrips.setId(idGenerator.nextId(pdTransportTrips) + "");
        baseMapper.insert(pdTransportTrips);
        return pdTransportTrips;
    }

    @Override
    public List<PdTransportTrips> findAll(String transportLineId, List<String> ids) {
        LambdaQueryWrapper<PdTransportTrips> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotEmpty(transportLineId)) {
            lambdaQueryWrapper.eq(PdTransportTrips::getTransportLineId, transportLineId);
        }
        if (ids != null && ids.size() > 0) {
            lambdaQueryWrapper.in(PdTransportTrips::getId, ids);
        }
        lambdaQueryWrapper.orderBy(true, true, PdTransportTrips::getId);
        lambdaQueryWrapper.eq(PdTransportTrips::getStatus, Constant.DATA_DEFAULT_STATUS);
        return baseMapper.selectList(lambdaQueryWrapper);
    }

    @Override
    public void disable(String id) {
        PdTransportTrips pdTransportTrips = new PdTransportTrips();
        pdTransportTrips.setId(id);
        pdTransportTrips.setStatus(Constant.DATA_DISABLE_STATUS);
        baseMapper.updateById(pdTransportTrips);
    }

}
