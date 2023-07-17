package com.trans.tms.authority.api.hystrix;


import com.trans.tms.authority.api.LogApi;
import com.trans.tms.base.R;
import com.trans.tms.log.entity.OptLogDTO;
import org.springframework.stereotype.Component;

@Component
public class LogApiHystrix implements LogApi {
    public LogApiHystrix() {
    }

    public R<OptLogDTO> save(OptLogDTO log) {
        return R.timeout();
    }
}
