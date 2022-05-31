package com.ve.locker.utils;

import io.netty.util.internal.StringUtil;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @Description create for hjgz .
 * @Author weiyi
 * @Date 2022/5/27
 */
public class LocalDateUtil {
    private LocalDateUtil() {
    }

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

    /**
     * 将LocalDate 转为yyyy-MM-dd 格式的字符串
     * @param localDate
     * @return
     *
     * @author zhaoyang10
     */
    public static String toStr(LocalDate localDate) {
        if (localDate == null) {
            return "";
        }
        return LOCALDATE_FMT.format(localDate);
    }


    /**
     * 将LocalDate转为自定义的时间格式的字符串
     * @param localDate
     * @param pattern
     * @return
     *
     * @author zhaoyang10
     */
    public static String toStr(LocalDate localDate, String pattern) {
        return DateTimeFormatter.ofPattern(pattern).format(localDate);
    }

    /**
     * 将LocalDateTime 转为yyyy-MM-dd HH:mm:ss 格式的字符串
     * @param localDateTime
     * @return
     *
     * @author zhaoyang10
     */
    public static String toStr(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return "";
        }
        return LOCALDATETIME_FMT.format(localDateTime);
    }

    /**
     * 将LocalDateTime转为自定义的时间格式的字符串
     * @param localDateTime
     * @param pattern
     * @return
     *
     * @author zhaoyang10
     */
    public static String toStr(LocalDateTime localDateTime, String pattern) {
        return DateTimeFormatter.ofPattern(pattern).format(localDateTime);
    }

    /**
     * 今天 字符串 格式 yyyy-MM-dd
     * @return
     *
     * @author zhaoyang10
     */
    public static String now() {
        return LOCALDATE_FMT.format(LocalDate.now());
    }

    /**
     * 昨天 字符串 格式 yyyy-MM-dd
     * @return
     *
     * @author zhaoyang10
     */
    public static String yesterday() {
        return LOCALDATE_FMT.format(LocalDate.now().minusDays(1));
    }

    /**
     *  字符串 格式 yyyyMMdd
     * @return
     *
     * @author zhaoyang10
     */
    public static String getYmd(LocalDate localDate) {
        return YYYMMDD_JFP_FMT.format(localDate);
    }

    /**
     * 格式化日期成字符串 yyyyMM
     * @param localDate
     * @return
     *
     * @author zhaoyang10
     */
    public static String getYm(LocalDate localDate) {
        return LOCALDATE_JFP_FMT.format(localDate);
    }

    /**
     * 格式化日期成字符串 dd
     * @param localDate
     * @return
     *
     * @author zhaoyang10
     */
    public static String getDay(LocalDate localDate) {
        return DAY_JFP_FMT.format(localDate);
    }

    /**
     * 参数为null 设置为当前日期 不为null 直接返回
     * @param localDate 日期
     * @return LocalDate
     *
     * @author zhaoyang10
     */
    public static LocalDate getNow(LocalDate localDate) {
        if (localDate == null) {
            return LocalDate.now();
        }
        return localDate;
    }

    public static String getNow(String localDate) {
        if (localDate == null) {
            return now();
        }
        return localDate;
    }

    /**
     * 将long类型的timestamp转为LocalDateTime
     * @param timestamp
     * @return
     *
     * @author zhaoyang10
     */
    public static LocalDateTime getDateTimeOfTimestamp(long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }


    /**
     * 将LocalDateTime转为long类型的毫秒数
     * @param localDateTime
     * @return
     *
     * @author zhaoyang10
     */
    public static long getTimestampOfDateTime(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return instant.toEpochMilli();
    }

    /**
     * 将LocalDateTime转为long类型的秒数
     * @param localDateTime
     * @return
     *
     * @author zhaoyang10
     */
    public static long getSecondOfDateTime(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.systemDefault();
        return localDateTime.atZone(zone).toEpochSecond();
    }

    public static LocalDate getLocalDate(String locaDateStr) {
        return LocalDate.parse(locaDateStr, DateTimeFormatter.ofPattern(DATE_SMALL_STR));
    }

    /**
     * @param endDateStr yyyy-MM-dd
     * @return
     */
    public static String getEndTime(String endDateStr) {
        return LocalDateUtil.getLocalDate(endDateStr).toString() + " 23:59:59";
    }

    /**
     * @param startDateStr yyyy-MM-dd
     * @return
     */
    public static String getStartTime(String startDateStr) {
        return LocalDateUtil.getLocalDate(startDateStr) + " 00:00:00";
    }

    /**
     * 将某时间字符串转为自定义时间格式的LocalDateTime
     * @param time
     * @param pattern
     * @return
     *
     * @author zhaoyang10
     */
    public static LocalDateTime parseStringToDateTime(String time, String pattern) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(time, df);
    }

    /**
     * 将java.util.Date 转换为java8 的java.time.LocalDateTime,默认时区为东8区
     * @param date
     * @return
     *
     * @author zhaoyang10
     */
    public static LocalDateTime convertToLocalDateTime(Date date) {
        return date.toInstant().atOffset(ZoneOffset.of("+8")).toLocalDateTime();
    }

    /**
     * 将java8 的 java.time.LocalDateTime 转换为 java.util.Date，默认时区为东8区
     * @param localDateTime
     * @return
     *
     * @author zhaoyang10
     */
    public static Date convertToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.toInstant(ZoneOffset.of("+8")));
    }

    /**
     * 计算今天剩余的秒数 可用于redis过期时间为当天
     * @return long
     *
     * @author zhaoyang10
     */
    public static long getSecondsToday() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime end = now.toLocalDate().atTime(23, 59, 59);
        return Duration.between(now, end).toMillis();
    }

    /**
     * 检查日期格式化格式是否合法
     * @author 冯赵杨
     * @date 2018年3月8日 上午11:49:49
     */
    public static boolean checkDateFormat(String pattern) {
        return DATE_JFP_STR.equals(pattern) || DATE_FULL_STR.equals(pattern) || DATE_SMALL_STR.equals(pattern)
                || DATE_KEY_STR.equals(pattern) || DATE_ALL_STR.equals(pattern);
    }

    /**
     * 判断日期是否大于今天 true大于今天
     * @param localDate
     * @return boolean
     *
     * @author zhaoyang10
     */
    public static boolean isGreaterToday(LocalDate localDate) {
        int flag = localDate.compareTo(LocalDate.now());
        return flag > 0;
    }

    public static LocalDate getLocalDate(String date, LocalDate defaultDate) {
        if (StringUtil.isNullOrEmpty(date)) {
            return defaultDate;
        }
        return getLocalDate(date);
    }

    /**
     * 获取本季度的第一天或最后一天
     * @param today
     * @param isFirst true 表示开始时间，false表示结束时间]
     * @return String
     *
     * @author zhaoyang10
     */
    public static LocalDate getStartOrEndDayOfQuarter(LocalDate today, boolean isFirst) {
        if (today == null) {
            today = LocalDate.now();
        }
        LocalDate resDate;
        Month month = today.getMonth();
        Month firstMonthOfQuarter = month.firstMonthOfQuarter();
        Month endMonthOfQuarter = Month.of(firstMonthOfQuarter.getValue() + 2);
        if (isFirst) {
            resDate = LocalDate.of(today.getYear(), firstMonthOfQuarter, 1);
        } else {
            resDate = LocalDate.of(today.getYear(), endMonthOfQuarter, endMonthOfQuarter.length(today.isLeapYear()));
        }
        return resDate;
    }

    /**
     * 获取本年的第一天或最后一天
     * @param today
     * @param isFirst true 表示开始时间，false表示结束时间]
     * @return LocalDate
     *
     * @author zhaoyang10
     */
    public static LocalDate getStartOrEndDayOfYear(LocalDate today, boolean isFirst) {
        if (today == null) {
            today = LocalDate.now();
        }
        LocalDate resDate;
        if (isFirst) {
            resDate = LocalDate.of(today.getYear(), Month.JANUARY, 1);
        } else {
            resDate = LocalDate.of(today.getYear(), Month.DECEMBER, Month.DECEMBER.length(today.isLeapYear()));
        }
        return resDate;
    }

    public static void main(String[] args) {
        LocalDate date = null;
        toStr(date);
    }
}