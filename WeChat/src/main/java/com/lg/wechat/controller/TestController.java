package com.lg.wechat.controller;

import com.alibaba.fastjson.JSONObject;
import com.lg.wechat.bean.WxTokenVo;
import com.lg.wechat.bean.wthrcdn.WeatherBean;
import com.lg.wechat.impl.WxServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
@RequestMapping("/wx")
public class TestController {

    @Autowired
    private WxServiceImpl wxServiceImpl;

    @GetMapping("/receiveWxInfo")
    public String receiveWxInfo(WxTokenVo reqVo) throws IOException {
        return wxServiceImpl.checkToken(reqVo);
    }

    @GetMapping("/sendMsg")
    public Object sendMessage() throws Exception {
        return wxServiceImpl.sendMsg();
    }


    public static void main(String[] args) {
        String data="{\n" +
                "\t\"cityid\": \"101010100\",\n" +
                "\t\"date\": \"2023-03-03\",\n" +
                "\t\"week\": \"星期五\",\n" +
                "\t\"update_time\": \"13:47\",\n" +
                "\t\"city\": \"北京\",\n" +
                "\t\"cityEn\": \"beijing\",\n" +
                "\t\"country\": \"中国\",\n" +
                "\t\"countryEn\": \"China\",\n" +
                "\t\"wea\": \"晴\",\n" +
                "\t\"wea_img\": \"qing\",\n" +
                "\t\"tem\": \"18\",\n" +
                "\t\"tem1\": \"18\",\n" +
                "\t\"tem2\": \"0\",\n" +
                "\t\"win\": \"西风\",\n" +
                "\t\"win_speed\": \"2级\",\n" +
                "\t\"win_meter\": \"6km/h\",\n" +
                "\t\"humidity\": \"9%\",\n" +
                "\t\"visibility\": \"24km\",\n" +
                "\t\"pressure\": \"1015\",\n" +
                "\t\"air\": \"65\",\n" +
                "\t\"air_pm25\": \"32\",\n" +
                "\t\"air_level\": \"良\",\n" +
                "\t\"air_tips\": \"空气好，可以外出活动，除极少数对污染物特别敏感的人群以外，对公众没有危害！\",\n" +
                "\t\"alarm\": {\n" +
                "\t\t\"alarm_type\": \"\",\n" +
                "\t\t\"alarm_level\": \"\",\n" +
                "\t\t\"alarm_content\": \"\"\n" +
                "\t},\n" +
                "\t\"win_speed_day\": \"<3级\",\n" +
                "\t\"win_speed_night\": \"\",\n" +
                "\t\"aqi\": {\n" +
                "\t\t\"update_time\": \"12:47\",\n" +
                "\t\t\"cityid\": \"101010100\",\n" +
                "\t\t\"city\": \"北京\",\n" +
                "\t\t\"cityEn\": \"beijing\",\n" +
                "\t\t\"country\": \"中国\",\n" +
                "\t\t\"countryEn\": \"China\",\n" +
                "\t\t\"air\": \"65\",\n" +
                "\t\t\"air_level\": \"良\",\n" +
                "\t\t\"air_tips\": \"空气好，可以外出活动，除极少数对污染物特别敏感的人群以外，对公众没有危害！\",\n" +
                "\t\t\"pm25\": \"32\",\n" +
                "\t\t\"pm25_desc\": \"优\",\n" +
                "\t\t\"pm10\": \"80\",\n" +
                "\t\t\"pm10_desc\": \"良\",\n" +
                "\t\t\"o3\": \"62\",\n" +
                "\t\t\"o3_desc\": \"优\",\n" +
                "\t\t\"no2\": \"35\",\n" +
                "\t\t\"no2_desc\": \"优\",\n" +
                "\t\t\"so2\": \"4\",\n" +
                "\t\t\"so2_desc\": \"优\",\n" +
                "\t\t\"co\": \"-\",\n" +
                "\t\t\"co_desc\": \"-\",\n" +
                "\t\t\"kouzhao\": \"不用佩戴口罩\",\n" +
                "\t\t\"yundong\": \"适宜运动\",\n" +
                "\t\t\"waichu\": \"适宜外出\",\n" +
                "\t\t\"kaichuang\": \"适宜开窗\",\n" +
                "\t\t\"jinghuaqi\": \"关闭净化器\"\n" +
                "\t}\n" +
                "}";

        WeatherBean weatherBean = JSONObject.parseObject(data, WeatherBean.class);
        System.out.println(weatherBean);
    }

}
