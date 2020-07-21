 package com.inspur.bss.commonsdk.utils.date;
 
 import java.time.Year;
 import java.util.Date;
 
 
 
 
 
 
 
 
 public class FinanceYear
 {
   private static final String START_DATETIME = "-04-01 00:00:00";
   private static final String END_DATETIME = "-03-31 23:59:59";
   private Year sYear;
   private String startTime;
   private String endTime;
   
   public FinanceYear()
   {
/* 23 */     this(new Date());
   }
   
   public FinanceYear(Date date) {
/* 27 */     init(date);
   }
   
   public FinanceYear(String startTime, String endTime) {
/* 31 */     this.startTime = startTime;
/* 32 */     this.endTime = endTime;
   }
   
   public FinanceYear(Year sYear) {
/* 36 */     this.startTime = (sYear + "-04-01 00:00:00");
/* 37 */     this.endTime = (sYear.plusYears(1L) + "-03-31 23:59:59");
   }
   
   private void init(Date date) {
/* 41 */     Year cYear = Year.now();
/* 42 */     String cYearStrart = cYear + "-04-01 00:00:00";
/* 43 */     String dateString = DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");
/* 44 */     if (dateString.compareTo(cYearStrart) >= 0) {
/* 45 */       Year nextYear = cYear.plusYears(1L);
/* 46 */       this.sYear = cYear;
/* 47 */       this.startTime = (cYear + "-04-01 00:00:00");
/* 48 */       this.endTime = (nextYear + "-03-31 23:59:59");
     }
     else {
/* 51 */       Year previousYear = cYear.minusYears(1L);
/* 52 */       this.sYear = previousYear;
/* 53 */       this.startTime = (previousYear + "-04-01 00:00:00");
/* 54 */       this.endTime = (cYear + "-03-31 23:59:59");
     }
   }
   
   public FinanceYear previous() {
/* 59 */     return new FinanceYear(this.sYear.minusYears(1L));
   }
   
   public FinanceYear next() {
/* 63 */     return new FinanceYear(this.sYear.plusYears(1L));
   }
   
   public String getStartTime() {
/* 67 */     return this.startTime;
   }
   
   public void setStartTime(String startTime) {
/* 71 */     this.startTime = startTime;
   }
   
   public String getEndTime() {
/* 75 */     return this.endTime;
   }
   
   public void setEndTime(String endTime) {
/* 79 */     this.endTime = endTime;
   }
 }


