package org.example;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.time.ZonedDateTime;
//import org.apache.commons.lang3.StringUtils;

/**
 * @author lys612411
 */
public class DateUtil {
    public static final String DATE = "yyyy-MM-dd";
    public static final String DATE_TIME = "yyyy-MM-dd HH:mm:ss";
    public static final String TIME = "HHmmss";
    public static final String TIME_WITHOUT_SECOND = "HH:mm";
    public static final String DATE_TIME_WITHOUT_SECONDS = "yyyy-MM-dd HH:mm";

    public static int getYear(){
        LocalTime localTime=LocalTime.now();
        return localTime.get(ChronoField.YEAR);
    }

    public static int getMonth() {
        LocalTime localTime = LocalTime.now();
        return localTime.get(ChronoField.MONTH_OF_YEAR);
    }

    public static int getMonthOfDay() {
        LocalTime localTime = LocalTime.now();
        return localTime.get(ChronoField.DAY_OF_MONTH);
    }

    public static String format(Date date,String pattern){
        Instant instant = date.toInstant();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant,ZoneId.systemDefault());
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static Date parse(String dateStr, String pattern) {
        LocalDateTime localDateTime = LocalDateTime.parse(dateStr,DateTimeFormatter.ofPattern(pattern));
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    public static Date addMinutes(Date date, Long plusMinutes) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(date.toInstant(),ZoneId.systemDefault());
        LocalDateTime newDateTime = dateTime.plusMinutes(plusMinutes);
        return Date.from(newDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date addHour(Date date, Long hour) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(date.toInstant(),
                ZoneId.systemDefault());
        LocalDateTime localDateTime = dateTime.plusHours(hour);
        return
                Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date getStartTime() {
        LocalDateTime now = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);
        return localDateTime2Date(now);
    }

    public static Date getEndTime() {
        LocalDateTime now = LocalDateTime.now().withHour(23).withMinute(59).withSecond(59).withNano(999);
        return localDateTime2Date(now);
    }

    public static Date minusMonths(long monthsToSubtract){
        LocalDate localDate = LocalDate.now().minusMonths(monthsToSubtract);
        return localDate2Date(localDate);
    }

    public static Date localDate2Date(LocalDate localDate) {
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());
        return Date.from(zonedDateTime.toInstant());
    }

    public static Date localDateTime2Date(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

//    public static String getFirstDayOfCurrentYear(String pattern) {
//        LocalDateTime localDateTime = LocalDateTime.now().withMonth(1).withDayOfMonth(1);
//        if (StringUtils.isEmpty(pattern)) {
//            pattern = "yyyyMMdd";
//        }
//        return format(localDateTime2Date(localDateTime), pattern);
//    }
//
//    public static String getLastMonthFirstDayOfPreviousYear(String pattern) {
//        LocalDateTime localDateTime = LocalDateTime.now().minusYears(1L).withMonth(12).withDayOfMonth(1);
//        if (StringUtils.isEmpty(pattern)) {
//            pattern = "yyyyMMdd";
//        }
//        return format(localDateTime2Date(localDateTime), pattern);
//    }
//
//    public static String getLastMonthLastDayOfPreviousYear(String pattern)
//    {
//        LocalDateTime localDateTime = LocalDateTime.now().minusYears(1L).with(TemporalAdjusters.lastDayOfYear());
//        if (StringUtils.isEmpty(pattern)) {
//            pattern = "yyyyMMdd";
//        }
//        return format(localDateTime2Date(localDateTime), pattern);
//    }
//
//    public static String getCurrentDay(String pattern) {
//        LocalDateTime localDateTime = LocalDateTime.now();
//        if (StringUtils.isEmpty(pattern)) {
//            pattern = "yyyyMMdd";
//        }
//        return format(localDateTime2Date(localDateTime), pattern);
//    }
    public static void main(String[] args){

    }
}

