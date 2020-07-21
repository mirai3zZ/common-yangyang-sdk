/*    */ package com.inspur.bss.commonsdk.authority.service;
/*    */ 
/*    */ import com.inspur.bss.commonsdk.bean.User;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Service
/*    */ @ConditionalOnMissingBean({UserOperationRecordService.class})
/*    */ public class NullUserOperationRecordService
/*    */   implements IUserOperationRecordService
/*    */ {
/* 19 */   private static Logger log = LoggerFactory.getLogger(NullUserOperationRecordService.class);
/*    */   
/*    */   public void recordAction(String authorityValues, User user, String result) {
/* 22 */     log.info("authority: {}, user: {}, result: {}", new Object[] { authorityValues, user.getId(), result });
/*    */   }
/*    */ }


/* Location:              C:\Users\liuyang04\Desktop\common-sdk-0.2.17.jar!\com\inspur\bss\commonsdk\authority\service\NullUserOperationRecordService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */