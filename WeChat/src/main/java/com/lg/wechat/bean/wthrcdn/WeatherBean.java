package com.lg.wechat.bean.wthrcdn;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherBean {

    private String cityid;
    private String date;
    private String week;
    private String updateTime;
    private String city;
    private String cityen;
    private String country;
    private String countryen;
    private String wea;
    @JSONField(name = "wea_img")
    private String weaImg;
    private String tem;
    private String tem1;
    private String tem2;
    private String win;
    @JSONField(name = "win_speed")
    private String winSpeed;
    @JSONField(name = "win_meter")
    private String winMeter;
    private String humidity;
    private String visibility;
    private String pressure;
    private String air;
    @JSONField(name = "air_pm25")
    private String airPm25;
    @JSONField(name = "air_level")
    private String airLevel;
    @JSONField(name = "air_tips")
    private String airTips;
    private Alarm alarm;
    private String winSpeedDay;
    private String winSpeedNight;
    private Aqi aqi;

}