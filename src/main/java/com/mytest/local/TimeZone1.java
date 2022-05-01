package com.mytest.local;

import org.apache.http.client.utils.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TimeZone1 {
     
    public static void main(String[] args) {
     Date date = new Date(1391174450000L); // 2014-1-31 21:20:50
     String dateStr = "2014-1-31 21:20:50 ";
     SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
     try { 
      Date dateTmp = dateFormat.parse(dateStr); 
      System.out.println(dateTmp); 
      } catch (ParseException e) {
      e.printStackTrace(); 
     } 
     String dateStrTmp = dateFormat.format(date); 
     System.out.println(dateStrTmp);

     Long test = 1649812857000L;
     System.out.println(DateUtils.formatDate( new Date(test),"yyyy-MM-dd HH:mm:ss"));
    }
     
 
}