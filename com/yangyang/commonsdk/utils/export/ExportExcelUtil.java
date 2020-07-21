 package com.inspur.bss.commonsdk.utils.export;
 
 import java.io.IOException;
 import java.io.OutputStream;
 import java.lang.reflect.InvocationTargetException;
 import java.lang.reflect.Method;
 import java.math.BigDecimal;
 import java.net.URLEncoder;
 import java.text.SimpleDateFormat;
 import java.util.Date;
 import java.util.Iterator;
 import java.util.LinkedHashMap;
 import java.util.List;
 import java.util.Map;
 import java.util.Map.Entry;
 import javax.servlet.ServletOutputStream;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import org.apache.poi.ss.usermodel.BorderStyle;
 import org.apache.poi.ss.usermodel.CellStyle;
 import org.apache.poi.ss.usermodel.Font;
 import org.apache.poi.ss.usermodel.HorizontalAlignment;
 import org.apache.poi.ss.usermodel.VerticalAlignment;
 import org.apache.poi.ss.usermodel.Workbook;
 import org.apache.poi.xssf.usermodel.XSSFCell;
 import org.apache.poi.xssf.usermodel.XSSFCellStyle;
 import org.apache.poi.xssf.usermodel.XSSFFont;
 import org.apache.poi.xssf.usermodel.XSSFRow;
 import org.apache.poi.xssf.usermodel.XSSFSheet;
 import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 
 @org.springframework.stereotype.Component
 public class ExportExcelUtil
 {
   public void doExport(Workbook wb, HttpServletResponse response, HttpServletRequest request, String fileName)
   {
/*  37 */     OutputStream os = null;
     try {
/*  39 */       os = response.getOutputStream();
/*  40 */       response.reset();
/*  41 */       String agent = request.getHeader("USER-AGENT");
/*  42 */       if (((null != agent) && (-1 != agent.indexOf("MSIE"))) || ((null != agent) && 
/*  43 */         (-1 != agent.indexOf("Trident")))) {
/*  44 */         String name = URLEncoder.encode(fileName, "UTF8");
/*  45 */         fileName = name;
/*  46 */       } else if ((null != agent) && (-1 != agent.indexOf("Mozilla"))) {
/*  47 */         fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
       }
/*  49 */       response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");
       
/*  51 */       response.setContentType("application/msexcel");
/*  52 */       wb.write(os); return;
     } catch (IOException e) {
/*  54 */       e.printStackTrace();
     } finally {
       try {
/*  57 */         wb.close();
       } catch (IOException e) {
/*  59 */         e.printStackTrace();
       }
     }
   }
   
   public static List<Object[]> ListOthersToListObjectArray(List<? extends Object> lists, Class clazz)
   {
/*  66 */     java.lang.reflect.Field[] fields = clazz.getDeclaredFields();
/*  67 */     String[] getMethodNames = new String[fields.length];
/*  68 */     List<Object[]> objsList = new java.util.ArrayList();
/*  69 */     for (int i = 0; i < fields.length; i++) {
/*  70 */       String filedName = fields[i].getName();
/*  71 */       getMethodNames[i] = ("get" + filedName.substring(0, 1).toUpperCase() + filedName.substring(1));
     }
/*  73 */     for (Object obj : lists) {
/*  74 */       Object[] objs = new Object[fields.length];
/*  75 */       for (int j = 0; j < getMethodNames.length; j++) {
/*  76 */         Method method = null;
         try {
/*  78 */           method = clazz.getMethod(getMethodNames[j], new Class[0]);
         } catch (NoSuchMethodException e) {
/*  80 */           e.printStackTrace();
         }
         try {
/*  83 */           objs[j] = method.invoke(obj, new Object[0]);
         } catch (IllegalAccessException e) {
/*  85 */           e.printStackTrace();
         } catch (InvocationTargetException e) {
/*  87 */           e.printStackTrace();
         }
       }
/*  90 */       objsList.add(objs);
     }
/*  92 */     return objsList;
   }
   
 
 
 
 
 
 
 
 
 
 
 
   public void doExport(HttpServletResponse response, HttpServletRequest request, Map<String, Object> dataMap)
   {
/* 108 */     String fileName = (String)dataMap.get("fileName");
/* 109 */     OutputStream os = null;
     try {
/* 111 */       os = response.getOutputStream();
/* 112 */       response.reset();
/* 113 */       String agent = request.getHeader("USER-AGENT");
/* 114 */       if (((null != agent) && (-1 != agent.indexOf("MSIE"))) || ((null != agent) && 
/* 115 */         (-1 != agent.indexOf("Trident")))) {
/* 116 */         String name = URLEncoder.encode(fileName, "UTF8");
/* 117 */         fileName = name;
/* 118 */       } else if ((null != agent) && (-1 != agent.indexOf("Mozilla"))) {
/* 119 */         fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
       }
/* 121 */       response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xls");
       
/* 123 */       response.setContentType("application/msexcel");
/* 124 */       doExport(os, dataMap);
     } catch (Exception e) {
/* 126 */       e.printStackTrace();
     }
   }
   
 
 
 
 
 
 
 
 
 
 
 
 
   public void doExport(HttpServletResponse response, HttpServletRequest request, String fileName, String[] nameArr, String[] titleArr, String[] alignArr, int[] widthArr, List dataList)
   {
     try
     {
/* 146 */       OutputStream os = response.getOutputStream();
/* 147 */       response.reset();
/* 148 */       String agent = request.getHeader("USER-AGENT");
/* 149 */       if (((null != agent) && (-1 != agent.indexOf("MSIE"))) || ((null != agent) && 
/* 150 */         (-1 != agent.indexOf("Trident")))) {
/* 151 */         String name = URLEncoder.encode(fileName, "UTF8");
/* 152 */         fileName = name;
/* 153 */       } else if ((null != agent) && (-1 != agent.indexOf("Mozilla"))) {
/* 154 */         fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
       }
/* 156 */       response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");
       
/* 158 */       response.setContentType("application/msexcel");
/* 159 */       doExport(os, nameArr, titleArr, alignArr, widthArr, dataList);
     } catch (Exception e) {
/* 161 */       e.printStackTrace();
     }
   }
   
 
 
 
 
 
 
 
 
 
 
 
 
   public void doExport(OutputStream os, Map<String, Object> dataMap)
   {
/* 179 */     String[] nameArr = (String[])dataMap.get("nameArr");
/* 180 */     String[] titleArr = (String[])dataMap.get("titleArr");
/* 181 */     String[] alignArr = (String[])dataMap.get("alignArr");
/* 182 */     int[] widthArr = (int[])dataMap.get("widthArr");
/* 183 */     List<Object> dataList = (List)dataMap.get("dataList");
     try {
/* 185 */       doExport(os, nameArr, titleArr, alignArr, widthArr, dataList);
     } catch (Exception e) {
/* 187 */       e.printStackTrace();
     } finally {
/* 189 */       org.apache.poi.util.IOUtils.closeQuietly(os);
     }
   }
   
 
 
 
 
 
 
 
 
 
 
 
 
   public void doExport(OutputStream os, String[] nameArr, String[] titleArr, String[] alignArr, int[] widthArr, List dataList)
   {
/* 207 */     List<CellInfor<String>> titles = new java.util.ArrayList();
/* 208 */     for (int i = 0; i < titleArr.length; i++) {
/* 209 */       titles.add(new CellInfor(titleArr[i], 0, i, 1, 1));
     }
/* 211 */     doExport(os, nameArr, titles, alignArr, widthArr, dataList);
   }
   
 
 
 
 
 
 
 
 
 
 
   public void doExport(OutputStream os, String[] nameArr, List<CellInfor<String>> titles, String[] alignArr, int[] widthArr, List dataList)
   {
/* 226 */     Position p = new Position(-1, -1);
/* 227 */     LinkedHashMap<Position, List> dataMap = new LinkedHashMap();
/* 228 */     dataMap.put(p, dataList);
/* 229 */     doExport(os, nameArr, titles, alignArr, widthArr, dataMap);
   }
   
 
 
 
 
 
 
 
 
 
 
 
   public void doExport(OutputStream os, String[] nameArr, List<CellInfor<String>> titles, String[] alignArr, int[] widthArr, LinkedHashMap<Position, List> dataMap)
   {
/* 245 */     String title = "Sheet1";
     try
     {
/* 248 */       XSSFWorkbook workbook = new XSSFWorkbook();
       
/* 250 */       XSSFSheet sheet = workbook.createSheet(title);
       
/* 252 */       sheet.setDefaultColumnWidth(20);
       
/* 254 */       XSSFCellStyle titleStyle = workbook.createCellStyle();
/* 255 */       titleStyle.setAlignment(HorizontalAlignment.CENTER);
/* 256 */       titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);
       
/* 258 */       XSSFFont titleFontStyle = workbook.createFont();
       
/* 260 */       titleFontStyle.setFontName("宋体");
       
/* 262 */       titleFontStyle.setFontHeightInPoints((short)10);
       
/* 264 */       titleFontStyle.setBold(true);
/* 265 */       titleStyle.setFont(titleFontStyle);
       
 
/* 268 */       int k = 0;
/* 269 */       for (Iterator localIterator1 = titles.iterator(); localIterator1.hasNext();) { cellInfo = (CellInfor)localIterator1.next();
/* 270 */         XSSFRow row = sheet.getRow(cellInfo.getRowNum());
/* 271 */         if (row == null) {
/* 272 */           row = sheet.createRow(cellInfo.getRowNum());
         }
/* 274 */         XSSFCell cell = row.createCell(cellInfo.getColNum());
/* 275 */         cell.setCellValue((String)cellInfo.getValue());
/* 276 */         cell.setCellStyle(titleStyle);
         
/* 278 */         if ((widthArr != null) && (widthArr.length > 0)) {
/* 279 */           sheet.setColumnWidth(k, widthArr[k] * 2 * 256);
         }
/* 281 */         k++;
       }
       CellInfor<String> cellInfo;
/* 284 */       Object cellRangeAddressList = CellInforUtil.getCellRangeAddressArray(titles);
/* 285 */       for (org.apache.poi.ss.util.CellRangeAddress cra : (List)cellRangeAddressList) {
/* 286 */         sheet.addMergedRegion(cra);
       }
       
 
/* 290 */       XSSFCellStyle contentStyle = workbook.createCellStyle();
/* 291 */       XSSFFont contentFontStyle = workbook.createFont();
       
/* 293 */       contentFontStyle.setFontName("宋体");
       
/* 295 */       contentFontStyle.setFontHeightInPoints((short)10);
/* 296 */       contentStyle.setWrapText(true);
/* 297 */       contentStyle.setVerticalAlignment(VerticalAlignment.CENTER);
/* 298 */       contentStyle.setFont(contentFontStyle);
       
/* 300 */       Boolean isMap = Boolean.valueOf(false);
       
/* 302 */       int currentRowN = sheet.getLastRowNum();
/* 303 */       int lastRowN = sheet.getLastRowNum() + 1;
/* 304 */       for (Map.Entry<Position, List> entry : dataMap.entrySet()) {
/* 305 */         List dataList = (List)entry.getValue();
/* 306 */         Position startPosition = (Position)entry.getKey();
/* 307 */         for (int i = 0; i < dataList.size(); i++) {
/* 308 */           Map<String, Object> data = null;
/* 309 */           Object obj = dataList.get(i);
/* 310 */           if ((obj instanceof Map)) {
/* 311 */             isMap = Boolean.valueOf(true);
/* 312 */             data = (Map)obj;
           }
           
/* 315 */           int startRow = startPosition.getRowNum() == -1 ? lastRowN : startPosition.getRowNum();
/* 316 */           XSSFRow row = sheet.getRow(i + startRow);
/* 317 */           if (row == null) {
/* 318 */             row = sheet.createRow(i + startRow);
           }
           
/* 321 */           for (int j = 0; j < nameArr.length; j++) {
/* 322 */             if ((alignArr == null) || (alignArr.length == 0)) {
/* 323 */               contentStyle.setAlignment(HorizontalAlignment.CENTER);
             } else {
/* 325 */               contentStyle.setAlignment(changeAlign(alignArr[j]));
             }
             
/* 328 */             int startCol = startPosition.getColNum() == -1 ? 0 : startPosition.getColNum();
/* 329 */             XSSFCell cell = row.createCell(j + startCol);
/* 330 */             Object value = null;
/* 331 */             if (isMap.booleanValue()) {
/* 332 */               value = data.get(nameArr[j]);
             } else {
/* 334 */               value = methodInvoke(obj, nameArr[j]);
             }
/* 336 */             String textValue = "";
/* 337 */             if ((value instanceof Date)) {
/* 338 */               Date date = (Date)value;
/* 339 */               SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
/* 340 */               textValue = format.format(date);
/* 341 */               cell.setCellValue(textValue);
/* 342 */             } else if (((value instanceof Integer)) || ((value instanceof Float)) || ((value instanceof Double)) || ((value instanceof Long)) || ((value instanceof BigDecimal)))
             {
 
/* 345 */               textValue = changeToStr(value);
/* 346 */               if (textValue.length() <= 11)
               {
/* 348 */                 cell.setCellValue(Double.parseDouble(textValue));
               } else {
/* 350 */                 cell.setCellValue(new BigDecimal(textValue).toString());
               }
             } else {
/* 353 */               textValue = changeToStr(value);
/* 354 */               cell.setCellValue(textValue);
             }
/* 356 */             cell.setCellStyle(contentStyle);
           }
         }
       }
       
/* 361 */       workbook.write(os);
/* 362 */       workbook.close();
     } catch (Exception e) {
/* 364 */       e.printStackTrace();
     }
   }
   
   public void doExport(Workbook workbook, HttpServletResponse response, String fileName) {
/* 369 */     response.setContentType("APPLICATION/DOWNLOAD");
     try {
/* 371 */       response.setHeader("Content-Disposition", String.format("attachment;fileName= %s", new Object[] { URLEncoder.encode(fileName + ".xlsx", "UTF-8") }));
/* 372 */       ServletOutputStream out = null;
/* 373 */       out = response.getOutputStream();
/* 374 */       workbook.write(out);
/* 375 */       out.flush();
/* 376 */       out.close();
     } catch (Exception e) {
/* 378 */       e.printStackTrace();
     }
   }
   
 
   public HorizontalAlignment changeAlign(String align)
   {
     HorizontalAlignment alignment;
     HorizontalAlignment alignment;
/* 387 */     if ("left".equals(align)) {
/* 388 */       alignment = HorizontalAlignment.LEFT; } else { HorizontalAlignment alignment;
/* 389 */       if ("center".equals(align)) {
/* 390 */         alignment = HorizontalAlignment.CENTER;
       } else
/* 392 */         alignment = HorizontalAlignment.RIGHT;
     }
/* 394 */     return alignment;
   }
   
 
   private Object methodInvoke(Object obj, String str)
     throws Exception
   {
/* 401 */     Object object = obj;
/* 402 */     String[] params = str.split("\\.");
/* 403 */     for (int i = 0; i < params.length; i++) {
/* 404 */       if (null != object)
       {
 
/* 407 */         object = methodInvokeHander(object, params[i]); }
     }
/* 409 */     return object;
   }
   
   private Object methodInvokeHander(Object obj, String str) throws Exception {
/* 413 */     Class clss = obj.getClass();
/* 414 */     String getMethodName = "get" + str.substring(0, 1).toUpperCase() + str.substring(1);
/* 415 */     Method method = clss.getMethod(getMethodName, new Class[0]);
/* 416 */     return method.invoke(obj, new Object[0]);
   }
   
   private CellStyle getCommonStyle(Workbook workbook) {
/* 420 */     CellStyle commonStyle = workbook.createCellStyle();
     
/* 422 */     commonStyle.setBorderBottom(BorderStyle.MEDIUM);
/* 423 */     commonStyle.setBorderLeft(BorderStyle.MEDIUM);
/* 424 */     commonStyle.setBorderTop(BorderStyle.MEDIUM);
/* 425 */     commonStyle.setBorderRight(BorderStyle.MEDIUM);
/* 426 */     commonStyle.setWrapText(true);
     
/* 428 */     commonStyle.setAlignment(HorizontalAlignment.CENTER);
/* 429 */     commonStyle.setVerticalAlignment(VerticalAlignment.CENTER);
     
/* 431 */     Font cfont = workbook.createFont();
/* 432 */     cfont.setFontName("宋体");
     
/* 434 */     cfont.setFontHeightInPoints((short)11);
     
/* 436 */     commonStyle.setFont(cfont);
/* 437 */     return commonStyle;
   }
   
 
 
   public String changeToStr(Object value)
   {
/* 444 */     if ((value == null) || ("null".equals(value))) {
/* 445 */       return "";
     }
/* 447 */     return String.valueOf(value);
   }
 }
