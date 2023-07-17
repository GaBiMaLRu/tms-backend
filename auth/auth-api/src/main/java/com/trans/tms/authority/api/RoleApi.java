package com.trans.tms.authority.api;

import com.trans.tms.authority.api.hystrix.RoleApiFallback;
import com.trans.tms.authority.dto.auth.RoleDTO;
import com.trans.tms.authority.dto.auth.RoleResourceDTO;
import com.trans.tms.base.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(
        name = "${tms.feign.authority-server:auth-server}",
        path = "/role",
        fallback = RoleApiFallback.class
)
public interface RoleApi {
    @GetMapping({"/codes"})
    R<List<Long>> findUserIdByCode(@RequestParam("codes") String[] codes);

    @RequestMapping(
            value = {"/findRoleByUserId/{id}"},
            method = {RequestMethod.GET}
    )
    R<List<Long>> findRoleByUserId(@PathVariable("id") Long id);

    @RequestMapping(
            value = {"/findAllRoles"},
            method = {RequestMethod.GET}
    )
    R<List<RoleResourceDTO>> findAllRoles();

    @GetMapping
    R<List<RoleDTO>> list(@RequestParam("userId") Long userId);
}
