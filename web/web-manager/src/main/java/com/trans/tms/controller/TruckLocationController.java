package com.trans.tms.controller;

import com.trans.tms.DTO.TaskTransportDTO;
import com.trans.tms.dto.transportline.TransportTripsTruckDriverDto;
import com.trans.tms.dto.truck.TruckDto;
import com.trans.tms.dto.truck.TruckTypeDto;
import com.trans.tms.authority.api.UserApi;
import com.trans.tms.authority.entity.auth.User;
import com.trans.tms.base.R;
import com.trans.tms.enums.transporttask.TransportTaskStatus;
import com.trans.tms.feign.TransportTaskFeign;
import com.trans.tms.feign.transportline.TransportTripsFeign;
import com.trans.tms.feign.truck.TruckFeign;
import com.trans.tms.feign.truck.TruckTypeFeign;
import com.trans.tms.vo.base.transforCenter.business.TruckLocationVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("transfor-center")
@Api(tags = "位置查询")
@Log
public class TruckLocationController {
    @Autowired
    private UserApi userApi;
    @Autowired
    private TruckFeign truckFeign;
    @Autowired
    private TruckTypeFeign truckTypeFeign;
    @Autowired
    private TransportTaskFeign transportTaskFeign;
    @Autowired
    private TransportTripsFeign transportTripsFeign;


    @ApiOperation(value = "获取车辆位置详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "车辆id", required = true, example = "1", paramType = "{path}")
    })
    @GetMapping("truck-place-info/{id}")
    public TruckLocationVo findTruckById(@PathVariable(name = "id") String id) {
        TruckLocationVo truckLocationVo = new TruckLocationVo();
        TruckDto truck = truckFeign.fineById(id);
        if (ObjectUtils.isEmpty(truck)) {
            return truckLocationVo;
        }
        truckLocationVo.setLicensePlate(truck.getLicensePlate());

        TruckTypeDto truckTypeDto = truckTypeFeign.fineById(truck.getTruckTypeId());
        if (!ObjectUtils.isEmpty(truckTypeDto)) {
            truckLocationVo.setTruckTypeName(truckTypeDto.getName());
        }

        TaskTransportDTO taskTransportDto = new TaskTransportDTO();
        taskTransportDto.setTruckId(truck.getId());
        taskTransportDto.setStatus(TransportTaskStatus.PROCESSING.getCode());
        List<TaskTransportDTO> transportTaskDtos = transportTaskFeign.findAll(taskTransportDto);
        if (CollectionUtils.isEmpty(transportTaskDtos)) {
            return truckLocationVo;
        }

        taskTransportDto = transportTaskDtos.get(0);
        List<TransportTripsTruckDriverDto> transportTripsTruckDriverDtos = transportTripsFeign.findAllTruckDriverTransportTrips(taskTransportDto.getTransportTripsId(), truck.getId(), null);
        if (CollectionUtils.isEmpty(transportTripsTruckDriverDtos)) {
            return truckLocationVo;
        }

        TransportTripsTruckDriverDto transportTripsTruckDriverDto = transportTripsTruckDriverDtos.get(0);
        String userId = transportTripsTruckDriverDto.getUserId();
        R<User> userR = userApi.get(Long.valueOf(userId));
        User user = userR.getData();
        truckLocationVo.setName(user.getName());
        truckLocationVo.setMobile(user.getMobile());
        truckLocationVo.setAvatar(user.getAvatar());

        return truckLocationVo;
    }
}
