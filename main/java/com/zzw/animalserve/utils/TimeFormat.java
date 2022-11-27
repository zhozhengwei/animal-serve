package com.zzw.animalserve.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description(时间转换工具)
 * @autor: zhouzhengwei
 * @date: 2022/10/15__23:21
 */
public class TimeFormat {


    public static String now() {
        Date date=new Date();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    public static String formatTime(Date date){
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    public static Date dateTime(String date) {
        Date time = null;
        try {
            time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }

}
