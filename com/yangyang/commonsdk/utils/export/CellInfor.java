 package com.inspur.bss.commonsdk.utils.export;
 
 import org.apache.poi.ss.usermodel.CellStyle;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 public class CellInfor<T>
 {
   private T value;
   private int rowNum;
   private int colNum;
/*  24 */   private int width = 1;
   
 
 
/*  28 */   private int height = 1;
   
 
   private CellStyle cellStyle;
   
 
   public CellInfor() {}
   
 
   public CellInfor(T value, int rowNum, int colNum, int width, int height)
   {
/*  39 */     this.value = value;
/*  40 */     this.rowNum = rowNum;
/*  41 */     this.colNum = colNum;
/*  42 */     this.width = width;
/*  43 */     this.height = height;
   }
   
   public CellInfor(T value, int rowNum, int colNum, int width, int height, CellStyle cellStyle) {
/*  47 */     this.value = value;
/*  48 */     this.rowNum = rowNum;
/*  49 */     this.colNum = colNum;
/*  50 */     this.width = width;
/*  51 */     this.height = height;
/*  52 */     this.cellStyle = cellStyle;
   }
   
   public CellInfor(T value, int rowNum, int colNum) {
/*  56 */     this.value = value;
/*  57 */     this.rowNum = rowNum;
/*  58 */     this.colNum = colNum;
   }
   
   public T getValue() {
/*  62 */     return (T)this.value;
   }
   
   public void setValue(T value) {
/*  66 */     this.value = value;
   }
   
   public int getRowNum() {
/*  70 */     return this.rowNum;
   }
   
   public void setRowNum(int rowNum) {
/*  74 */     this.rowNum = rowNum;
   }
   
   public int getColNum() {
/*  78 */     return this.colNum;
   }
   
   public void setColNum(int colNum) {
/*  82 */     this.colNum = colNum;
   }
   
   public int getWidth() {
/*  86 */     return this.width;
   }
   
   public void setWidth(int width) {
/*  90 */     this.width = width;
   }
   
   public int getHeight() {
/*  94 */     return this.height;
   }
   
   public void setHeight(int height) {
/*  98 */     this.height = height;
   }
   
   public CellStyle getCellStyle() {
/* 102 */     return this.cellStyle;
   }
   
   public void setCellStyle(CellStyle cellStyle) {
/* 106 */     this.cellStyle = cellStyle;
   }
 }

