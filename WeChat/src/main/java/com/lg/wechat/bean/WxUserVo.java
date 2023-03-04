package com.lg.wechat.bean;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author lg
 */
@Data
public class WxUserVo {
    private  String total;
    private  String count;
    private  Map<String, List> data;
    private  String next_openid;
}
