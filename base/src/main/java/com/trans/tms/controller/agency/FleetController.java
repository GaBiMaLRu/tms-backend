package com.trans.tms.controller.agency;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.trans.tms.common.utils.PageResponse;
import com.trans.tms.common.utils.Result;
import com.trans.tms.entity.agency.PdFleet;
import com.trans.tms.service.agency.IPdFleetService;
import com.trans.tms.dto.angency.FleetDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * FleetController
 */
@RestController
@RequestMapping("sys/agency/fleet")
public class FleetController {
    @Autowired
    private IPdFleetService fleetService;

    /**
     * 添加车队
     *
     * @param dto 车队信息
     * @return 车队信息
     */
    @PostMapping("")
    public FleetDto saveAgencyType(@RequestBody FleetDto dto) {
        PdFleet pdFleet = new PdFleet();
        BeanUtils.copyProperties(dto, pdFleet);
        pdFleet = fleetService.saveFleet(pdFleet);
        BeanUtils.copyProperties(pdFleet, dto);
        return dto;
    }

    /**
     * 根据id获取车队详情
     *
     * @param id 车队id
     * @return 车队信息
     */
    @GetMapping("/{id}")
    public FleetDto fineById(@PathVariable(name = "id") String id) {
        PdFleet pdFleet = fleetService.getById(id);
        FleetDto dto = new FleetDto();
        BeanUtils.copyProperties(pdFleet, dto);
        return dto;
    }

    /**
     * 获取车队分页数据
     *
     * @param page        页码
     * @param pageSize    页尺寸
     * @param name        车队名称
     * @param fleetNumber 车队编号
     * @param manager     负责人id
     * @return 车队分页数据
     */
    @GetMapping("/page")
    public PageResponse<FleetDto> findByPage(@RequestParam(name = "page") Integer page,
                                             @RequestParam(name = "pageSize") Integer pageSize,
                                             @RequestParam(name = "name", required = false) String name,
                                             @RequestParam(name = "fleetNumber", required = false) String fleetNumber,
                                             @RequestParam(name = "manager", required = false) String manager) {
        IPage<PdFleet> fleetPage = fleetService.findByPage(page, pageSize, name, fleetNumber, manager);
        List<FleetDto> dtoList = new ArrayList<>();
        fleetPage.getRecords().forEach(PdFleet -> {
            FleetDto dto = new FleetDto();
            BeanUtils.copyProperties(PdFleet, dto);
            dtoList.add(dto);
        });
        return PageResponse.<FleetDto>builder().items(dtoList).size(pageSize).current(page).counts(fleetPage.getTotal())
                .total(fleetPage.getPages()).build();
    }

    /**
     * 获取车队列表
     *
     * @param ids 车队Id列表
     * @return 车队列表
     */
    @GetMapping("")
    public List<FleetDto> findAll(@RequestParam(value = "ids", required = false) List<String> ids, @RequestParam(value = "agencyId", required = false) String agencyId) {
        return fleetService.findAll(ids, agencyId).stream().map(pdFleet -> {
            FleetDto dto = new FleetDto();
            BeanUtils.copyProperties(pdFleet, dto);
            return dto;
        }).collect(Collectors.toList());
    }

    /**
     * 更新车队信息
     *
     * @param dto 车队信息
     * @return 车队信息
     */
    @PutMapping("/{id}")
    public FleetDto update(@PathVariable(name = "id") String id, @RequestBody FleetDto dto) {
        dto.setId(id);
        PdFleet pdFleet = new PdFleet();
        BeanUtils.copyProperties(dto, pdFleet);
        fleetService.updateById(pdFleet);
        return dto;
    }

    /**
     * 删除车队
     *
     * @param id 车队id
     * @return 返回信息
     */
    @PutMapping("/{id}/disable")
    public Result disable(@PathVariable(name = "id") String id) {
        fleetService.disableById(id);
        return Result.ok();
    }
}