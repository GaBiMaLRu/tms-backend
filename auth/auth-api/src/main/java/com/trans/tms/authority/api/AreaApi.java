package com.trans.tms.authority.api;


import java.util.List;

import com.trans.tms.authority.entity.common.Area;
import com.trans.tms.base.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "${tms.feign.authority-server:auth-server}",
        path = "/area"
)
public interface AreaApi {
    @GetMapping({"/{id}"})
    R<Area> get(@PathVariable Long id);

    @GetMapping({"/code/{code}"})
    R<Area> getByCode(@PathVariable String code);

    @GetMapping
    R<List<Area>> findAll(@RequestParam(value = "parentId",required = false) Long parentId, @RequestParam(value = "ids",required = false) List<Long> ids);
}
