package com.lg.wechat.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Yesterday {

    private String date;
    private String high;
    private String fx;
    private String low;
    private String fl;
    private String type;

}