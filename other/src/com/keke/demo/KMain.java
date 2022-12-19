package com.keke.demo;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * LocalDateTime 工具类学习
 * @author k 2022/11/28 15:17
 */
public class KMain {
    public static void main(String[] args) {

        LocalTime now = LocalTime.now();
        int hour = now.getHour();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String format = dtf.format(LocalDateTime.now());
        System.out.println(format);

        LocalDateTime parse = LocalDateTime.parse(format,dtf);
        System.out.println(parse);
        int dayOfMonth = parse.getDayOfMonth();
        int monthValue = parse.getMonthValue();
        LocalDateTime minusDays = parse.minusDays(1);
        LocalDateTime minusHours = parse.minusHours(2);
        LocalDateTime localDateTime = parse.withDayOfYear(200);
        System.out.println("dayOfMonth: " + dayOfMonth );
        System.out.println("monthValue: " + monthValue );
        System.out.println("minusDays: " + minusDays );
        System.out.println("minusHours: " + minusHours );
        System.out.println("localDateTime: " + localDateTime );
    }
}
