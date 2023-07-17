package com.trans.tms.zuul.api;


import com.trans.tms.authority.dto.auth.ResourceQueryDTO;
import com.trans.tms.authority.entity.auth.Resource;
import com.trans.tms.base.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "${tms.feign.authority-server:auth-server}",
        fallback = ResourceApiFallback.class)
public interface ResourceApi {
    //获取所有需要鉴权的资源
    @GetMapping("/resource/list")
    public R<List> list();

    //查询当前登录用户拥有的资源权限
    @GetMapping("/resource/visible")
    public R<List<Resource>> visible(ResourceQueryDTO resource);
}