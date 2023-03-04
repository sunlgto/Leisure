package com.lg.wechat.impl;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.lg.wechat.bean.*;
import com.lg.wechat.bean.wthrcdn.SimplWeath;
import com.lg.wechat.bean.wthrcdn.WeatherBean;
import com.lg.wechat.util.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.*;
import java.util.zip.GZIPInputStream;

@Service
public class WxServiceImpl {

    @Value("${appId}")
    private  String appId = "";

    @Value("${secret}")
    private  String secret = "";

    @Value("${tmplId}")
    private  String tmplId="";

    @Value("${openUserId}")
    private  String[] openUserId;

    @Value("${tokenUrl}")
    private  String wxTokenUrl = "";

    @Value("${birthday}")
    private String birthday="";

    @Value("${beginDate}")
    private String beginDate="";

    @Value("${city}")
    private String city="";

    private  String wxUserListUrl="https://api.weixin.qq.com/cgi-bin/user/get?next_openid=&access_token=";

    private  String wxSendMsgUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";

    private String weatherRrl="https://v0.yiketianqi.com/api?unescape=1&version=v61&appid=59337113&appsecret=Y7m1GWuF&city=";
    //private String weatherRrl="http://wthrcdn.etouch.cn/weather_mini?city=";

    private String twqhUrl="https://api.1314.cool/words/api.php";


    @Autowired
    private RestTemplate restTemplate;

    public String getToken() {
        //{"access_token":"ACCESS_TOKEN","expires_in":7200}
        //可以存下缓存 获取token接口每天限定调用2000次
        String weatherData = getRestData(wxTokenUrl);
        JSONObject result = JSONObject.parseObject(weatherData);
        //Map result = restTemplate.getForObject(wxTokenUrl, Map.class);
        return (String) Optional.ofNullable(result.get("access_token")).orElseGet(() -> result.get("errmsg"));
    }

    public WxUserVo getOpenUser(String token){
        return restTemplate.getForObject(wxUserListUrl+token, WxUserVo.class);
    }



    public String getTuWeiQingHua(){
        return restTemplate.getForObject(twqhUrl,String.class);
    }

    public List sendMsg() throws Exception {
        String token = getToken();
        if(openUserId==null||openUserId.length<=0){
            openUserId= (String[]) getOpenUser(token).getData().get("openid").toArray(new String[0]);
        }
        SimplWeath weather = getWeather(WeatherBean.class);
        String birthday = DateTime.getBirthday(this.birthday);
        Long day = DateTime.printDifference(DateTime.getDateFromString(beginDate), new Date()).get("day");
        MsgVo msgVo = new MsgVo();
        Map<String, String> first = new HashMap<String, String>();
        Map<String, String> keyword1 = new HashMap<String, String>();
        Map<String, String> keyword2 = new HashMap<String, String>();
        Map<String, String> keyword3 = new HashMap<String, String>();
        Map<String, String> keyword4 = new HashMap<String, String>();
        Map<String, String> keyword5 = new HashMap<String, String>();


        /**模版信息*/
        putData(birthday,"#173177",first);
        putData(day+"","#173177",keyword1);
        putData(weather.getCity()+"当前温度："+weather.getCurr()+"°C "+
                weather.getType()+
                " 最高"+weather.getMax()+
                " 最低"+weather.getMin(),"#173177",keyword2);
        putData(weather.getTips(),"#173177",keyword3);
        putData(getTuWeiQingHua().replace("<br>",""),"#173177",keyword4);

        Map<String ,Map> content=new HashMap<>();
        content.put("first",first);
        content.put("keyword1",keyword1);
        content.put("keyword2",keyword2);
        content.put("keyword3",keyword3);
        content.put("keyword4",keyword4);
        //putData(JSONObject.toJSONString(content),"#173177",keyword5);

        //content.put("body",keyword5);
        msgVo.setData(content);
        msgVo.setTemplate_id(tmplId);
        List result=new ArrayList();
        for (String s : openUserId) {
            msgVo.setTouser(s);
            ResponseEntity<Map> mapResponseEntity = restTemplate.postForEntity(wxSendMsgUrl + token, msgVo, Map.class);
            result.add(mapResponseEntity.getBody());
        }


        return result;
    }

    public String checkToken(WxTokenVo reqVo) {
        return "";
    }

    public void  putData(String val,String color,Map map){
        map.put("value",val);
        map.put("color",color);
    }


    public  static String getRestData(String weatherRrl) {
        StringBuilder sb=new StringBuilder();;
        try {
            URL url = new URL(weatherRrl);
            URLConnection conn = url.openConnection();
            InputStream is = conn.getInputStream();
            //GZIPInputStream gzin = new GZIPInputStream(is);
            InputStreamReader isr = new InputStreamReader(is, "utf-8");
            BufferedReader reader = new BufferedReader(isr);
            String line = null;
            while((line=reader.readLine())!=null) {
                sb.append(line).append(" ");
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();

    }


    public SimplWeath getWeather(Class authorClass) throws Exception {
        String cityName = URLEncoder.encode(city, "UTF-8");
        String s = getRestData(weatherRrl + cityName);

        Object target = authorClass.newInstance();
        if(target instanceof WeatherBean ){
            WeatherBean weatherBean = JSONObject.parseObject(s, WeatherBean.class);
            return  SimplWeath.builder()
                    .city(weatherBean.getCity())
                    .curr(weatherBean.getTem()+"°C")
                    .type(weatherBean.getWea())
                    .max(weatherBean.getTem1()+"°C")
                    .min(weatherBean.getTem2()+"°C")
                    .tips(weatherBean.getAirTips())
                    .build();
        }else if(target instanceof JsonRootBean){
            JsonRootBean weather = JSONObject.parseObject(s, JsonRootBean.class);
            WeatherInfo weatherData = weather.getData();
            Forecast forecast = weather.getData().getForecast().get(0);
            return  SimplWeath.builder()
                    .city(weatherData.getCity())
                    .curr(weatherData.getWendu()+"°C")
                    .type(forecast.getType())
                    .max(forecast.getHigh()+"°C")
                    .min(forecast.getLow()+"°C")
                    .tips(weather.getData().getGanmao())
                    .build();
        }


        return  SimplWeath.builder().city("").curr("").min("").max("").type("").tips("").build();

    }
}
