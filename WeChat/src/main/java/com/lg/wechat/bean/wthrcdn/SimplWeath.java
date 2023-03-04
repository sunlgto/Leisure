package com.lg.wechat.bean.wthrcdn;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SimplWeath {
    private String max;
    private String min;
    private String curr;
    private String tips;
    private String city;
    private String type;

}
