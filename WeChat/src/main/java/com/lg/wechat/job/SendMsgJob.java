package com.lg.wechat.job;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.lg.wechat.impl.WxServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author lg
 */
@Configuration
@EnableScheduling
@Slf4j
public class SendMsgJob {


    @Autowired
    private WxServiceImpl wxServiceImpl;

    @Scheduled(cron = "0 0 8 * * ?")
    private void configureTasks() throws Exception {
        System.err.println("执行静态定时任务时间: " + LocalDateTime.now());
        List list = wxServiceImpl.sendMsg();
        log.info(new JsonMapper().writeValueAsString(list));
    }
}
