 package com.inspur.bss.commonsdk.utils.export;
 
 import com.inspur.bss.commonsdk.utils.date.DateFormatUtils;
 import java.io.IOException;
 import java.io.OutputStream;
 import java.lang.reflect.Field;
 import java.lang.reflect.Method;
 import java.util.Date;
 import java.util.Objects;
 import javax.servlet.http.HttpServletResponse;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import org.springframework.http.MediaType;
 import org.springframework.util.Assert;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 public class CsvResponseWriter
 {
/*  35 */   private static final Logger logger = LoggerFactory.getLogger(CsvResponseWriter.class);
   
 
   private static final String LINE_SEPARATOR = "\r\n";
   
 
   private String[] columnHeader;
   
   private String[] columnField;
   
   private OutputStream outputStream;
   
 
   public CsvResponseWriter(TableColumnPair[] columnPairs, OutputStream outputStream)
   {
/*  50 */     this.outputStream = outputStream;
/*  51 */     init(columnPairs);
   }
   
   private void init(TableColumnPair[] columnPairs) {
/*  55 */     Assert.notNull(columnPairs, "Column header and field are required.");
/*  56 */     int columnSize = columnPairs.length;
/*  57 */     this.columnField = new String[columnSize];
/*  58 */     this.columnHeader = new String[columnSize];
     
/*  60 */     for (int i = 0; i < columnSize; i++) {
/*  61 */       this.columnField[i] = columnPairs[i].getField();
/*  62 */       this.columnHeader[i] = columnPairs[i].getHeader();
     }
     
 
/*  66 */     sendCsvHeader();
   }
   
   private void sendCsvHeader() {
/*  70 */     sendCsvHeader(this.columnHeader, this.outputStream);
   }
   
   public static void sendCsvHeader(String[] header, OutputStream outputStream) {
/*  74 */     sendCsvHeader(String.join(",", header), outputStream);
   }
   
   public static void sendCsvHeader(String headerString, OutputStream outputStream)
   {
     try {
/*  80 */       byte[] uft8Bom = { -17, -69, -65 };
/*  81 */       outputStream.write(uft8Bom);
       
/*  83 */       outputStream.write(headerString.getBytes());
/*  84 */       outputStream.write("\r\n".getBytes());
     }
     catch (Exception e) {
/*  87 */       logger.error(e.getMessage());
     }
   }
   
   public void sendRow(Object rowData) {
/*  92 */     sendRow(rowData, this.columnField, this.outputStream);
   }
   
   public static void sendRow(Object rowData, String[] fieldNames, OutputStream outputStream) {
/*  96 */     if (Objects.isNull(fieldNames)) {
/*  97 */       fieldNames = getAllFieldNames(rowData);
     }
/*  99 */     sendRow(rowData, fieldNames, "yyyy-MM-dd HH:mm:ss", outputStream);
   }
   
   private static String[] getAllFieldNames(Object rowData) {
/* 103 */     Field[] fields = rowData.getClass().getDeclaredFields();
/* 104 */     int fieldSize = fields.length;
/* 105 */     String[] fieldNames = new String[fieldSize];
/* 106 */     for (int i = 0; i < fieldSize; i++) {
/* 107 */       fieldNames[i] = fields[i].getName();
     }
/* 109 */     return fieldNames;
   }
   
   public static void sendRow(Object rowData, String[] fieldNames, String dateFormat, OutputStream outputStream) {
/* 113 */     String formattedRowData = composeRowData(rowData, fieldNames, dateFormat);
/* 114 */     sendRow(formattedRowData, outputStream);
   }
   
   public static void sendRow(String rowDataString, OutputStream outputStream) {
     try {
/* 119 */       outputStream.write(rowDataString.getBytes());
/* 120 */       outputStream.write("\r\n".getBytes());
     }
     catch (IOException e) {
/* 123 */       logger.error(e.getMessage());
     }
   }
   
   private static String composeRowData(Object rowData, String[] fieldNames, String dateFormat) {
/* 128 */     StringBuilder rowValueBuilder = new StringBuilder();
/* 129 */     for (String fieldName : fieldNames)
     {
/* 131 */       String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
/* 132 */       Class<?> dataClass = rowData.getClass();
       try
       {
/* 135 */         Method getMethod = dataClass.getMethod(getMethodName, new Class[0]);
/* 136 */         Object columnValue = getMethod.invoke(rowData, new Object[0]);
/* 137 */         if (null == columnValue) {
/* 138 */           columnValue = "";
         }
/* 140 */         rowValueBuilder.append(formatColumnValue(columnValue, dateFormat)).append(",");
       }
       catch (Exception e) {
/* 143 */         logger.error(e.getMessage());
       }
     }
     
 
/* 148 */     if (rowValueBuilder.length() > 0) {
/* 149 */       rowValueBuilder.deleteCharAt(rowValueBuilder.length() - 1);
     }
/* 151 */     return rowValueBuilder.toString();
   }
   
   private static String formatColumnValue(Object columnValue, String dateFormat) {
/* 155 */     if ((columnValue instanceof Date)) {
/* 156 */       Date date = (Date)columnValue;
/* 157 */       return DateFormatUtils.format(date, dateFormat);
     }
/* 159 */     return String.valueOf(columnValue);
   }
   
   public static void sendHttpHeader(HttpServletResponse response) {
/* 163 */     String currentDate = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
/* 164 */     String fileName = currentDate + ".csv";
/* 165 */     response.setContentType(MediaType.APPLICATION_OCTET_STREAM.toString());
/* 166 */     response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"; filename*=utf-8''" + fileName);
     try {
/* 168 */       response.flushBuffer();
     }
     catch (IOException e) {
/* 171 */       logger.error(e.getMessage());
     }
   }
 }

