 package com.inspur.bss.commonsdk.utils.export;
 
 import java.io.OutputStream;
 import java.io.UnsupportedEncodingException;
 import java.net.URLEncoder;
 import java.util.LinkedHashMap;
 import java.util.List;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import org.apache.poi.util.IOUtils;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Component;
 
 
 @Component
 public abstract class Downloadable
 {
   @Autowired
   HttpServletRequest request;
   @Autowired
   ExportExcelUtil exportExcelUtil;
   
   public void downloadExcel(List<?> list)
   {
/* 25 */     OutputStream os = null;
     try {
/* 27 */       os = getResponse().getOutputStream();
/* 28 */       getResponse().reset();
/* 29 */       setHeader();
/* 30 */       if (getExcelTitle() == null) {
/* 31 */         this.exportExcelUtil.doExport(os, getExcelColumn(), getExcelTitleList(), new String[0], new int[0], list);
       }
       else {
/* 34 */         this.exportExcelUtil.doExport(os, getExcelColumn(), getExcelTitle(), new String[0], new int[0], list);
       }
     }
     catch (Exception e)
     {
/* 39 */       e.printStackTrace();
     } finally {
/* 41 */       IOUtils.closeQuietly(os);
     }
   }
   
   public void downloadExcel(LinkedHashMap<Position, List> dataMap) {
/* 46 */     OutputStream os = null;
     try {
/* 48 */       os = getResponse().getOutputStream();
/* 49 */       getResponse().reset();
/* 50 */       setHeader();
/* 51 */       this.exportExcelUtil.doExport(os, getExcelColumn(), getExcelTitleList(), new String[0], new int[0], dataMap);
     }
     catch (Exception e) {
/* 54 */       e.printStackTrace();
     } finally {
/* 56 */       IOUtils.closeQuietly(os);
     }
   }
   
   private void setHeader() throws UnsupportedEncodingException
   {
/* 62 */     String fileName = getFileName();
/* 63 */     String agent = this.request.getHeader("USER-AGENT");
/* 64 */     if (((null != agent) && (-1 != agent.indexOf("MSIE"))) || ((null != agent) && 
/* 65 */       (-1 != agent.indexOf("Trident")))) {
/* 66 */       String name = URLEncoder.encode(fileName, "UTF8");
/* 67 */       fileName = name;
/* 68 */     } else if ((null != agent) && (-1 != agent.indexOf("Mozilla"))) {
/* 69 */       fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
     }
/* 71 */     getResponse().setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");
     
/* 73 */     getResponse().setContentType("application/msexcel");
   }
   
   public String[] getExcelTitle() {
/* 77 */     return null;
   }
   
   public List<CellInfor<String>> getExcelTitleList() {
/* 81 */     return null;
   }
   
   public abstract String[] getExcelColumn();
   
   public abstract HttpServletResponse getResponse();
   
   public abstract String getFileName();
   
   public String getSheetName() {
/* 91 */     return null;
   }
   
   public List<String> getSheetNameList() {
/* 95 */     return null;
   }
 }

