package com.mytest.local;

import ch.qos.logback.core.util.DatePatternToRegexUtil;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateTest {

    public static void main(String[] args) throws ParseException {

        String date = "2022-04-16 20:40:00";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        Date date2 = sdf.parse(date);

        int i = DateUtils.truncatedCompareTo(new Date(), date2, Calendar.MINUTE);
        System.out.println(i);


    }
}
