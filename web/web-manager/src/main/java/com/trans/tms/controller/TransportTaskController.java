package com.trans.tms.controller;

import com.trans.tms.DTO.TaskTransportDTO;
import com.trans.tms.DTO.webManager.TaskTransportQueryDTO;
import com.trans.tms.authority.api.AreaApi;
import com.trans.tms.authority.api.OrgApi;
import com.trans.tms.authority.api.UserApi;
import com.trans.tms.authority.entity.core.Org;
import com.trans.tms.base.R;
import com.trans.tms.common.utils.PageResponse;
import com.trans.tms.feign.OrderFeign;
import com.trans.tms.feign.TransportOrderFeign;
import com.trans.tms.feign.TransportTaskFeign;
import com.trans.tms.feign.transportline.TransportTripsFeign;
import com.trans.tms.feign.truck.TruckFeign;
import com.trans.tms.feign.webManager.WebManagerFeign;
import com.trans.tms.util.BeanUtil;
import com.trans.tms.vo.work.PointDTO;
import com.trans.tms.vo.work.TaskTransportVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;


/**
 * <p>
 * 运输任务表 前端控制器
 * </p>
 *
 * @author jpf
 * @since 2019-12-29
 */
@Slf4j
@RestController
@Api(tags = "运输任务API")
@RequestMapping("transport-task-manager")
public class TransportTaskController {
    @Autowired
    private TransportTaskFeign transportTaskFeign;
    @Autowired
    private TransportTripsFeign transportTripsFeign;
    @Autowired
    private OrgApi orgApi;
    @Autowired
    private UserApi userApi;
    @Autowired
    private TruckFeign truckFeign;
    @Autowired
    private TransportOrderFeign transportOrderFeign;
    @Autowired
    private OrderFeign orderFeign;
    @Autowired
    private AreaApi areaApi;
    @Autowired
    private WebManagerFeign webManagerFeign;

    @ApiOperation(value = "获取运输任务分页数据")
    @PostMapping("/page")
    public PageResponse<TaskTransportVo> findByPage(@RequestBody TaskTransportVo vo) {
        TaskTransportQueryDTO dto = new TaskTransportQueryDTO();
        if (vo != null) {
            dto.setPage(vo.getPage());
            dto.setPageSize(vo.getPageSize());
            dto.setStatus(vo.getStatus());
            dto.setId(vo.getId());
            dto.setDriverName(vo.getDriverName());
        }
        PageResponse<TaskTransportDTO> dtoPageResponse = webManagerFeign.findTaskTransportByPage(dto);
        List<TaskTransportDTO> dtoList = dtoPageResponse.getItems();
        List<TaskTransportVo> voList = dtoList.stream().map(taskTransportDTO -> BeanUtil.parseTaskTransportDTO2Vo(taskTransportDTO, transportTripsFeign, orgApi, userApi, truckFeign, transportOrderFeign, orderFeign, areaApi)).collect(Collectors.toList());
        return PageResponse.<TaskTransportVo>builder().items(voList).size(vo.getPageSize()).current(vo.getPage()).counts(dtoPageResponse.getCounts()).total(dtoPageResponse.getTotal()).build();
    }

    @ApiOperation(value = "获取运输任务详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "运输任务id", required = true, example = "1", paramType = "{path}")
    })
    @GetMapping("/{id}")
    public TaskTransportVo findById(@PathVariable(name = "id") String id) {
        TaskTransportDTO dto = transportTaskFeign.findById(id);
        TaskTransportVo vo;
        // TODO: 2020/4/8 任务轨迹待实现
        if (dto != null) {
            vo = BeanUtil.parseTaskTransportDTO2Vo(dto, transportTripsFeign, orgApi, userApi, truckFeign, transportOrderFeign, orderFeign, areaApi);
        } else {
            vo = new TaskTransportVo();
            vo.setId(id);
        }
        return vo;
    }

    @ApiOperation(value = "获取运输任务坐标")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "运输任务id", required = true, example = "1", paramType = "{path}")
    })
    @GetMapping("point/{id}")
    public LinkedHashSet<PointDTO> findPointById(@PathVariable(name = "id") String id) {
        LinkedHashSet<PointDTO> pointDTOS = new LinkedHashSet<>();
        TaskTransportDTO dto = transportTaskFeign.findById(id);
        R<Org> startOrgR = orgApi.get(Long.parseLong(dto.getStartAgencyId()));
        Org startOrg = startOrgR.getData();
        R<Org> endOrgR = orgApi.get(Long.parseLong(dto.getEndAgencyId()));
        Org endOrg = endOrgR.getData();
        PointDTO pointDTO1 = new PointDTO();
        pointDTO1.setName(startOrg.getName());
        pointDTO1.setMarkerPoints(startOrg.getLongitude(), startOrg.getLatitude());
        pointDTOS.add(pointDTO1);
        PointDTO pointDTO2 = new PointDTO();
        pointDTO2.setName(endOrg.getName());
        pointDTO2.setMarkerPoints(endOrg.getLongitude(), endOrg.getLatitude());
        pointDTOS.add(pointDTO2);
        return pointDTOS;
    }

    @ApiOperation(value = "更新运输任务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "运输任务id", required = true, example = "1", paramType = "{path}")
    })
    @PutMapping("/{id}")
    public TaskTransportVo update(@PathVariable(name = "id") String id, @RequestBody TaskTransportVo vo) {
        TaskTransportDTO dto = transportTaskFeign.updateById(id, BeanUtil.parseTaskTransportVo2DTO(vo));
        return BeanUtil.parseTaskTransportDTO2Vo(dto, transportTripsFeign, orgApi, userApi, truckFeign, transportOrderFeign, orderFeign, areaApi);
    }
}
