package com.trans.tms.mapper.transportline;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.trans.tms.entity.transportline.PdTransportTripsTruckDriver;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 车次与车辆关联信息表  Mapper 接口
 * </p>
 *
 * @author author
 * @since 2019-12-20
 */
@Mapper
public interface PdTransportTripsTruckDriverMapper extends BaseMapper<PdTransportTripsTruckDriver> {

}
