
package com.lg.wechat.bean;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonRootBean {

    private WeatherInfo data;
    private int status;
    private String desc;

}