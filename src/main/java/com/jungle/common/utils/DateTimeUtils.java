package com.jungle.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * @author jungle
 * @version V1.0
 * @date 2018/11/12 10:23
 * @Title: DateTimeUtils.java
 * @Package com.jungle.common.utils
 * @Description: 日期时间工具类
 * copyright © 2018- github.com
 */
public final class DateTimeUtils {

    /**
     * 日志。
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DateTimeUtils.class);

    /**
     * 模式：yyyy-MM-dd。
     */
    public static final SimpleDateFormat SDF_YYYY_MM_DD = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 模式：yyyy-MM-dd HH:mm:ss。
     */
    public static final SimpleDateFormat SDF_YYYY_MM_DD_HH_MM_SS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 模式：yyyy-MM-dd HH:mm:ss.SSS。
     */
    public static final SimpleDateFormat SDF_YYYY_MM_DD_HH_MM_SS_SSS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    /**
     * no-args constructor.
     */
    private DateTimeUtils() {/**/}

    /**
     * 将目标字符串按所给格式解析为日期。
     *
     * @param dateStr 目标日期字符串
     * @param pattern 日期模式
     * @return {@link Date}
     */
    public static Date parse(String dateStr, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            LOGGER.warn("unable to parse [{}] to date type with pattern [{}]", dateStr, pattern);
        }
        return null;
    }

    /**
     * 将目标字符串按所给格式解析为日期。
     *
     * @param dateStr 目标日期字符串
     * @param sdf     {@link SimpleDateFormat} 日期模式
     * @return {@link Date}
     */
    public static Date parse(String dateStr, SimpleDateFormat sdf) {
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            LOGGER.warn("unable to parse [{}] to date type with pattern [{}]", dateStr, sdf.toPattern());
        }
        return null;
    }

    /**
     * 将目标日期格式化为字符串。
     *
     * @param date    目标日期
     * @param pattern 模式
     * @return String
     */
    public static String format(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * 将{@link Date}按当前时区转换为{@link LocalDate}。
     *
     * @param date {@link Date}
     * @return {@link LocalDate}
     */
    public static LocalDate date2LocalDate(Date date) {
        return date2LocalDate(date, ZoneId.systemDefault());
    }

    /**
     * * 将{@link Date}按时区转换为{@link LocalDate}。
     *
     * @param date   {@link Date}
     * @param zoneId {@link ZoneId}目标时区id
     * @return {@link LocalDate}
     */
    public static LocalDate date2LocalDate(Date date, ZoneId zoneId) {
        Instant instant = date.toInstant();
        return instant.atZone(zoneId).toLocalDate();
    }

    /**
     * 计算两个Date之间的日期差。
     *
     * @param start 开始时间
     * @param end   结束时间
     * @return long
     */
    public static long diffInDays(Date start, Date end) {

        LocalDate localStart = date2LocalDate(start);
        LocalDate localEnd = date2LocalDate(end);
        return ChronoUnit.DAYS.between(localStart, localEnd);
    }

}
