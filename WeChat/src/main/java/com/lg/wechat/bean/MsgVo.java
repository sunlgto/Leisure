package com.lg.wechat.bean;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class MsgVo {
    private String touser;
    private String template_id;
    private String url;
    private Map<String,String> miniprogram;
    private String client_msg_id;
    private Map data;
}
