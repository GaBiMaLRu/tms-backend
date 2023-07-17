package com.trans.tms.authority.api;



import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trans.tms.authority.api.hystrix.UserApiFallback;
import com.trans.tms.base.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.trans.tms.authority.entity.auth.User;

@FeignClient(
        name = "${tms.feign.authority-server:auth-server}",
        fallback = UserApiFallback.class,
        path = "/user"
)
public interface UserApi {
    @RequestMapping(
            value = {"/ds/{id}"},
            method = {RequestMethod.GET}
    )
    Map<String, Object> getDataScopeById(@PathVariable("id") Long id);

    @RequestMapping(
            value = {"/find"},
            method = {RequestMethod.GET}
    )
    R<List<Long>> findAllUserId();

    @GetMapping({"/{id}"})
    R<User> get(@PathVariable Long id);

    @GetMapping({"/page"})
    R<Page<User>> page(@RequestParam("current") Long current, @RequestParam("size") Long size, @RequestParam(name = "orgId",required = false) Long orgId, @RequestParam(name = "stationId",required = false) Long stationId, @RequestParam(name = "name",required = false) String name, @RequestParam(name = "account",required = false) String account, @RequestParam(name = "mobile",required = false) String mobile);

    @GetMapping({""})
    R<List<User>> list(@RequestParam(name = "ids",required = false) List<Long> ids, @RequestParam(name = "stationId",required = false) Long stationId, @RequestParam(name = "name",required = false) String name, @RequestParam(name = "orgId",required = false) Long orgId);
}
