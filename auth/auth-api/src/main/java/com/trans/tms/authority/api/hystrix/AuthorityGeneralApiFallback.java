package com.trans.tms.authority.api.hystrix;


import com.trans.tms.authority.api.AuthorityGeneralApi;
import com.trans.tms.base.R;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AuthorityGeneralApiFallback implements AuthorityGeneralApi {
    public AuthorityGeneralApiFallback() {
    }

    public R<Map<String, Map<String, String>>> enums() {
        return R.timeout();
    }
}
