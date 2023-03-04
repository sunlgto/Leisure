package com.lg.wechat.bean.wthrcdn;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Alarm {


    private String alarmType;

    private String alarmLevel;

    private String alarmContent;

}