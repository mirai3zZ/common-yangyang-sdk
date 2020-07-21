 package com.inspur.bss.commonsdk.utils.export;
 
 
 
 
 
 
 
 
 public class Position
 {
/* 12 */   int rowNum = -1;
   
 
 
/* 16 */   int colNum = -1;
   
   public Position(int rowNum, int colNum) {
/* 19 */     this.rowNum = rowNum;
/* 20 */     this.colNum = colNum;
   }
   
   public int getRowNum() {
/* 24 */     return this.rowNum;
   }
   
   public void setRowNum(int rowNum) {
/* 28 */     this.rowNum = rowNum;
   }
   
   public int getColNum() {
/* 32 */     return this.colNum;
   }
   
   public void setColNum(int colNum) {
/* 36 */     this.colNum = colNum;
   }
 }

