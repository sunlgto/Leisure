
package com.lg.wechat.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Forecast {

    private String date;
    private String high;
    private String fengli;
    private String low;
    private String fengxiang;
    private String type;

}