package com.trans.tms.authority.api;

import com.trans.tms.authority.api.hystrix.LogApiHystrix;
import com.trans.tms.base.R;
import com.trans.tms.log.entity.OptLogDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(
        name = "${tms.feign.authority-server:auth-server}",
        fallback = LogApiHystrix.class
)
public interface LogApi {
    @RequestMapping(
            value = {"/optLog"},
            method = {RequestMethod.POST}
    )
    R<OptLogDTO> save(@RequestBody OptLogDTO log);
}
