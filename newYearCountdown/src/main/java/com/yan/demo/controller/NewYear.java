package com.yan.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.hiwepy.ip2region.spring.boot.IP2regionTemplate;
import com.github.hiwepy.ip2region.spring.boot.ext.RegionAddress;
import com.yan.demo.pojo.User;
import com.yan.demo.util.AddressUtils;
import com.yan.demo.util.DateTime;
import com.yan.demo.util.RedisTempUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.yan.demo.util.DateTime.getDateFromString;
import static com.yan.demo.util.DateTime.printDifference;

/**
 * @description:
 * @author: lg
 * @create: 2021-12-08 13:36
 **/
@Controller
@PropertySource("classpath:application.properties")
public class NewYear {
    @Value("${newYear}")
    private String newYear;
    @Autowired
    private RedisTempUtil<String,String> redisTempUtil;

    @Autowired
    private IP2regionTemplate ip2regionTemplate;

    @GetMapping("/")
    public ModelAndView goTo(HttpServletRequest request) throws IOException {
        ModelAndView modelAndView =new ModelAndView();
        if(!StringUtils.isEmpty(newYear)){
            int currYear = Integer.parseInt(newYear.split("-")[0])-1;
            modelAndView.addObject("year",currYear);
        }else {
            modelAndView.addObject("year", DateTime.getYear());
        }
        List<User> userList=null;
        String ip = getRemortIP(request);
		System.out.println("getRemortIP=>"+getRemortIP(request)+"_________getRemoteHost=>"+getRemoteHost(request));
        redisTempUtil.set("ip1",getRemortIP(request));
        redisTempUtil.set("ip2",getRemoteHost(request));
        if(ip==null){
            ip=getRemoteHost(request);
        }
        if(redisTempUtil.get("userList")!=null){
            userList = JSONObject.parseArray(redisTempUtil.get("userList"), User.class);
            String finalIp = ip;
            boolean flag=false;
            for (User item : userList) {
                System.out.println(item);
                if(item.getIpAddress().equals("getRemortIP=>"+getRemortIP(request)+"_________getRemoteHost=>"+getRemoteHost(request))){
                    item.setCount((Integer.parseInt(item.getCount())+1)+"");
                    flag=true;
                }
            }
            if(!flag){
                User user = new User();
                if(ip!=null){
                    RegionAddress regionAddress = ip2regionTemplate.getRegionAddress(ip);
                    user.setOtherOne(regionAddress.getCountry()+"-"+regionAddress.getProvince()+"-"+regionAddress.getCity()+"-"+regionAddress.getISP()+"-"+regionAddress.getArea());
                    user.setAddress(AddressUtils.getCity(ip));
                }
                user.setClientType(getClientType(request));
                user.setCount("1");
                user.setIpAddress("getRemortIP=>"+getRemortIP(request)+"_________getRemoteHost=>"+getRemoteHost(request));
                user.setUserName(getRemortIP(request));
                user.setOther(request.getHeader("User-Agent"));
                userList.add(user);
            }


        }else{
           userList=new ArrayList<>();
            User user = new User();
           if(ip!=null){
               RegionAddress regionAddress = ip2regionTemplate.getRegionAddress(ip);
               user.setOtherOne(regionAddress.getCountry()+"-"+regionAddress.getProvince()+"-"+regionAddress.getCity()+"-"+regionAddress.getISP()+"-"+regionAddress.getArea());
               user.setAddress(AddressUtils.getCity(ip));
           }
            user.setClientType(getClientType(request));
            user.setCount("1");
            user.setIpAddress("getRemortIP=>"+getRemortIP(request)+"_________getRemoteHost=>"+getRemoteHost(request));
            user.setUserName(getRemoteHost(request));
            user.setOther(request.getHeader("User-Agent"));
            userList.add(user);
        }
        redisTempUtil.set("userList",JSONObject.toJSONString(userList));

        modelAndView.setViewName("index2");
        return modelAndView;
    }


    @PostMapping("/getData")
    @ResponseBody
    public Map<String, Long>  hello() throws Exception {
        String result="";
        Date currDate = new Date();
        Date year = getDateFromString(newYear);
        Map<String, Long> stringLongMap = printDifference(currDate, year);
        return stringLongMap;
    }


    public String getRemortIP(HttpServletRequest request) {
        if (request.getHeader("x-forwarded-for") == null) {
            return request.getRemoteAddr();
        }
        return request.getHeader("x-forwarded-for");
    }
    //获取真实IP二
    public String getRemoteHost(HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public String  getClientType(HttpServletRequest request){
        String User_Agent = request.getHeader("User-Agent");
        String clientType="";
        if (User_Agent.contains("Android")||User_Agent.contains("Linux")) {
            clientType+="Android移动客户端 ";
            if (User_Agent.contains("MicroMessenger")) {
                clientType+="-微信";
            }
        } else if (User_Agent.contains("iPhone")) {
            clientType+="iPhone移动客户端";
            if (User_Agent.contains("MicroMessenger")) {
                clientType+="-微信";
            }
        } else if (User_Agent.contains("iPad")) {
            clientType+="iPad客户端";
            if (User_Agent.contains("MicroMessenger")) {
                clientType+="-微信";
            }
        } else if(User_Agent.contains("Windows")){
            clientType+="Windows";
        }
        return clientType;
    }



}
