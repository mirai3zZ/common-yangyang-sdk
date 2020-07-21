 package com.inspur.bss.commonsdk.utils.date;
 
 import java.text.SimpleDateFormat;
 import java.util.Date;
 
 
 
 
 
 
 public class DateFormatUtils
 {
   public static String format(Date date, String format)
   {
/* 15 */     return new SimpleDateFormat(format).format(date);
   }
 }


