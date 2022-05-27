package com.ve.locker.utils;
import com.ve.locker.util.LogUtil;
import org.joda.time.DateTime;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @Description create for hjgz .
 * @Author weiyi
 * @Date 2022/5/27
 */
public class TimeUtil {

    /**
     * 定义常量
     **/
    public static final String DATE_JFP_STR = "yyyyMM";
    public static final String DATE_FULL_STR = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_SMALL_STR = "yyyy-MM-dd";
    public static final String DATE_KEY_STR = "yyMMddHHmmss";
    public static final String DATE_ALL_STR = "yyyyMMddHHmmssSSS";
    public static final String DAY_JFP_STR = "dd";
    public static final String YYYMMDD_JFP_STR = "yyyyMMdd";

    private static final DateTimeFormatter LOCALDATE_FMT = DateTimeFormatter.ofPattern(DATE_SMALL_STR);

    private static final DateTimeFormatter LOCALDATETIME_FMT = DateTimeFormatter.ofPattern(DATE_FULL_STR);

    private static final DateTimeFormatter LOCALDATE_JFP_FMT = DateTimeFormatter.ofPattern(DATE_JFP_STR);

    private static final DateTimeFormatter DAY_JFP_FMT = DateTimeFormatter.ofPattern(DAY_JFP_STR);

    private static final DateTimeFormatter YYYMMDD_JFP_FMT = DateTimeFormatter.ofPattern(YYYMMDD_JFP_STR);


    public static SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

    public static Long yesterdayTime;

    public static boolean timeIn(Long startTime,Long endTime,Long currentTime){
        if(startTime<=currentTime&&currentTime<=endTime) {
            return true;
        }
        return false;
    }


    public static boolean timeIn(DateTime startTime, DateTime endTime, DateTime currentTime){
        int yesterday=Integer.parseInt(dateFormat.format(startTime));
        int now=Integer.parseInt(dateFormat.format(endTime));
        int current=Integer.parseInt(dateFormat.format(currentTime));
        if(yesterday<=current&&current<=now) {
            return true;
        }
        return false;
    }



//    public static boolean isYesterday(DateTime lastSingInTime,DateTime signInTime){
//        //20220601
//        int currentTime=Integer.parseInt(signInTime.toString("yyyyMMdd"));
//
//        //20220501
//        int yesterdayTime=Integer.parseInt(lastSingInTime.toString("yyyyMMdd"));
//
//        if(yesterdayTime<currentTime) {
//            return true;
//        }
//        return false;
//    }

    public static boolean isYesterday(LocalDateTime lastSingInTime, LocalDateTime  signInTime){
        if(lastSingInTime==null){
            return true;
        }
        //20220601
        int currentTime=Integer.parseInt(YYYMMDD_JFP_FMT.format(signInTime));

        //20220501
        int yesterdayTime=Integer.parseInt(YYYMMDD_JFP_FMT.format(lastSingInTime));

        LogUtil.println(currentTime);
        LogUtil.println(yesterdayTime);
        return yesterdayTime < currentTime;
    }

    public static Long getYesterdayTime(){

        Date yesterday=new Date("");
        return 0L;
    }
}
