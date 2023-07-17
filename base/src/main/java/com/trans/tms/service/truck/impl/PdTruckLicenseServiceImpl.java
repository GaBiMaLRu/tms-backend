package com.trans.tms.service.truck.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trans.tms.common.CustomIdGenerator;
import com.trans.tms.mapper.truck.PdTruckLicenseMapper;
import com.trans.tms.entity.truck.PdTruck;
import com.trans.tms.entity.truck.PdTruckLicense;
import com.trans.tms.service.truck.IPdTruckLicenseService;
import com.trans.tms.service.truck.IPdTruckService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 车辆行驶证表 服务实现类
 * </p>
 *
 * @author author
 * @since 2019-12-20
 */
@Service
public class PdTruckLicenseServiceImpl extends ServiceImpl<PdTruckLicenseMapper, PdTruckLicense>
        implements IPdTruckLicenseService {
    @Autowired
    private CustomIdGenerator idGenerator;
    @Autowired
    private IPdTruckService truckService;

    @Override
    public PdTruckLicense saveTruckLicense(PdTruckLicense pdTruckLicense) {
        if (pdTruckLicense.getId() == null) {
            pdTruckLicense.setId(idGenerator.nextId(pdTruckLicense) + "");
            baseMapper.insert(pdTruckLicense);
            // 处理车辆信息中的关联字段
            if (pdTruckLicense.getTruckId() != null) {
                PdTruck pdTruck = truckService.getById(pdTruckLicense.getTruckId());
                pdTruck.setTruckLicenseId(pdTruckLicense.getId());
                truckService.updateById(pdTruck);
            }
        } else {
            baseMapper.updateById(pdTruckLicense);
        }
        return pdTruckLicense;
    }

}
