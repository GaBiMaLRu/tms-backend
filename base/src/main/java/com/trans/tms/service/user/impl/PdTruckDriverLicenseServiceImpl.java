package com.trans.tms.service.user.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trans.tms.common.CustomIdGenerator;
import com.trans.tms.mapper.user.PdTruckDriverLicenseMapper;
import com.trans.tms.entity.user.PdTruckDriverLicense;
import com.trans.tms.service.user.IPdTruckDriverLicenseService;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 司机驾驶证表 服务实现类
 * </p>
 *
 * @author author
 * @since 2019-12-20
 */
@Service
public class PdTruckDriverLicenseServiceImpl extends ServiceImpl<PdTruckDriverLicenseMapper, PdTruckDriverLicense> implements IPdTruckDriverLicenseService {
    @Autowired
    private CustomIdGenerator idGenerator;

    @Override
    public PdTruckDriverLicense saveTruckDriverLicense(PdTruckDriverLicense pdTruckDriverLicense) {
        PdTruckDriverLicense driverLicense = baseMapper.selectOne(new LambdaQueryWrapper<PdTruckDriverLicense>().eq(PdTruckDriverLicense::getUserId, pdTruckDriverLicense.getUserId()));
        if (driverLicense == null) {
            pdTruckDriverLicense.setId(idGenerator.nextId(pdTruckDriverLicense) + "");
        } else {
            pdTruckDriverLicense.setId(driverLicense.getId());
        }
        saveOrUpdate(pdTruckDriverLicense);
        return pdTruckDriverLicense;
    }

    @Override
    public PdTruckDriverLicense findOne(String userId) {
        LambdaQueryWrapper<PdTruckDriverLicense> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotEmpty(userId)) {
            lambdaQueryWrapper.eq(PdTruckDriverLicense::getUserId, userId);
        }
        return getOne(lambdaQueryWrapper);
    }

}
