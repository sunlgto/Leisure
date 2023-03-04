package com.lg.wechat.util;

import lombok.SneakyThrows;

import java.text.SimpleDateFormat;
import java.util.*;


public class DateTime {

    public static final String datetimeFormat = "yyyy-MM-dd HH:mm:ss";
    public static final String datetimeFormat2 = "yyyy-MM-dd";
    public static Date getDateFromString(String s) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(datetimeFormat);
        return sdf.parse(s);
    }

	@SneakyThrows
	public static Date getDateFromString(String s, int i) {
		SimpleDateFormat sdf = new SimpleDateFormat(datetimeFormat2);
		return sdf.parse(s);
	}

    public static Map<String, Long> printDifference(Date startDate, Date endDate) {
        Map<String, Long> dateMap = new HashMap<String, Long>();
        long different = endDate.getTime() - startDate.getTime();
        long secondsInMilli = 1000;
        long miutesInMilli = secondsInMilli * 60;
        long hoursInMilli = miutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;
        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;
        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;
        long elapsedMinutes = different / miutesInMilli;
        different = different % miutesInMilli;
        long elapsedSeconds = different / secondsInMilli;
        dateMap.put("day", elapsedDays);
        dateMap.put("hour", elapsedHours);
        dateMap.put("minute", elapsedMinutes);
        dateMap.put("second", elapsedSeconds);
        return dateMap;
    }

    public static String getYear() {
        SimpleDateFormat sdf=new SimpleDateFormat(datetimeFormat);
        String year = sdf.format(new Date()).split("-")[0];
        return year;
    }

    /**
     *
     * @param date 生日 01-01
     * @return
     */
    public static String getBirthday(String date) throws Exception {
        String year = getYear();
        Long day = printDifference(getDateFromString(year + "-"+date+""), new Date()).get("day");
        if(day>0){
            return day+"";
        }else if(day==0){
            return "srkl";
        }else{
            return Math.abs(day)+"";
        }
    }

}
