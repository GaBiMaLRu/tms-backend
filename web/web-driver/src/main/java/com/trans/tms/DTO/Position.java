package com.trans.tms.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Position {

    //纬度值
    private String latitude;
    //经度值
    private String longitude;
}
