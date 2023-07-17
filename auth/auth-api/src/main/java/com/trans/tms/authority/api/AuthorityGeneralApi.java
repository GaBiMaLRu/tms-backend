package com.trans.tms.authority.api;


import java.util.Map;

import com.trans.tms.authority.api.hystrix.AuthorityGeneralApiFallback;
import com.trans.tms.base.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        name = "${tms.feign.authority-server:auth-server}",
        fallback = AuthorityGeneralApiFallback.class
)
public interface AuthorityGeneralApi {
    @GetMapping({"/enums"})
    R<Map<String, Map<String, String>>> enums();
}
