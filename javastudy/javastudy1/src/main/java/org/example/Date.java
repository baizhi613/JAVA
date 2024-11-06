package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author lys612411
 */
public class Date {
    public static void main(String[] args) {
        // 设置起始日期和日期格式
        LocalDate startDate = LocalDate.of(2020, 2, 1);
        LocalDate endDate = LocalDate.now(); // 当前日期
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // 生成日期列表
        List<LocalDate> dateList = new ArrayList<>();
        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            dateList.add(date);
        }

        // 反转列表以按降序排序
        Collections.reverse(dateList);

        // 输出日期
        for (LocalDate date : dateList) {
            System.out.println(date.format(formatter));
        }
    }
}
