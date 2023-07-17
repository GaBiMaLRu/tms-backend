package com.trans.tms.authority.api;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trans.tms.authority.api.hystrix.OrgApiFallback;
import com.trans.tms.base.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import com.trans.tms.authority.entity.core.Org;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import com.trans.tms.authority.dto.core.OrgTreeDTO;

import java.util.List;

@FeignClient(
        name = "${tms.feign.authority-server:auth-server}",
        fallback = OrgApiFallback.class,
        path = "/org"
)
public interface OrgApi {
    @GetMapping({"/{id}"})
    R<Org> get(@PathVariable Long id);

    @GetMapping
    R<List<Org>> list(@RequestParam(name = "orgType",required = false) Integer orgType, @RequestParam(name = "ids",required = false) List<Long> ids, @RequestParam(name = "countyId",required = false) Long countyId, @RequestParam(name = "pid",required = false) Long pid, @RequestParam(name = "pids",required = false) List<Long> pids);

    @GetMapping({"/tree"})
    R<List<OrgTreeDTO>> tree(@RequestParam(value = "name",required = false) String name, @RequestParam(value = "status",required = false) Boolean status);

    @GetMapping({"/pageLike"})
    R<Page> pageLike(@RequestParam(value = "size",required = false) Integer size, @RequestParam(value = "current",required = false) Integer current, @RequestParam(value = "keyword",required = false) String keyword, @RequestParam(value = "cityId",required = false) Long cityId, @RequestParam(value = "latitude",required = false) String latitude, @RequestParam(value = "longitude",required = false) String longitude);

    @GetMapping({"/listByCountyIds"})
    R<List<Org>> listByCountyIds(@RequestParam(name = "orgType",required = false) Integer orgType, @RequestParam(name = "countyIds",required = false) List<Long> countyIds);
}
