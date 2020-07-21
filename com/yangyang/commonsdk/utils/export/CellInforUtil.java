 package com.inspur.bss.commonsdk.utils.export;
 
 import java.util.LinkedList;
 import java.util.List;
 import org.apache.poi.ss.util.CellRangeAddress;
 
 public class CellInforUtil
 {
   public static List<CellRangeAddress> getCellRangeAddressArray(List<? extends CellInfor> cellInfors)
   {
/* 11 */     List<CellRangeAddress> cellRangeAddressList = new LinkedList();
/* 12 */     for (CellInfor cellInfor : cellInfors) {
/* 13 */       if ((cellInfor.getWidth() > 1) || (cellInfor.getHeight() > 1)) {
/* 14 */         cellRangeAddressList.add(new CellRangeAddress(cellInfor.getRowNum(), cellInfor.getRowNum() + cellInfor.getHeight() - 1, cellInfor.getColNum(), cellInfor.getColNum() + cellInfor.getWidth() - 1));
       }
     }
/* 17 */     return cellRangeAddressList;
   }
 }


