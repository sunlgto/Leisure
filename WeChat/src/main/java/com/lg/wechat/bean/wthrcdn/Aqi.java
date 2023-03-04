package com.lg.wechat.bean.wthrcdn;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Aqi {

    @JSONField(name = "update_time")
    private String updateTime;
    private String cityid;
    private String city;
    private String cityen;
    private String country;
    private String countryen;
    private String air;
    @JSONField(name = "air_level")
    private String airLevel;
    @JSONField(name = "air_tips")
    private String airTips;
    private String pm25;
    @JSONField(name = "pm25_desc")
    private String pm25Desc;
    private String pm10;
    @JSONField(name = "pm10_desc")
    private String pm10Desc;
    private String o3;
    @JSONField(name = "co_desc")
    private String o3Desc;
    private String no2;
    @JSONField(name = "no2_desc")
    private String no2Desc;
    private String so2;
    @JSONField(name = "so2_desc")
    private String so2Desc;
    private String co;
    @JSONField(name = "co_desc")
    private String coDesc;
    private String kouzhao;
    private String yundong;
    private String waichu;
    private String kaichuang;
    private String jinghuaqi;


}