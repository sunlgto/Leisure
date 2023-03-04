package com.lg.wechat.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class WxTokenVo implements Serializable {
    private String signature;
    private String timestamp;
    private String nonce;
    private String echostr;

}
