/*    */ package com.inspur.bss.commonsdk.authority.service;
/*    */ 
/*    */ import com.fasterxml.jackson.databind.ObjectMapper;
/*    */ import com.inspur.bss.commonsdk.authority.bean.UserOperationRecord;
/*    */ import com.inspur.bss.commonsdk.bean.User;
/*    */ import com.inspur.bss.commonsdk.utils.IdWorker;
/*    */ import java.util.Date;
/*    */ import javax.annotation.Resource;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import org.springframework.amqp.rabbit.core.RabbitTemplate;
/*    */ import org.springframework.beans.factory.annotation.Value;
/*    */ import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.util.StringUtils;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Service
/*    */ @ConditionalOnProperty({"spring.rabbitmq.accesscontrol.addresses"})
/*    */ public class UserOperationRecordService
/*    */   implements IUserOperationRecordService
/*    */ {
/* 30 */   private static Logger log = LoggerFactory.getLogger(UserOperationRecordService.class);
/*    */   @Resource(name="accessRecordTemplate")
/*    */   private RabbitTemplate rabbitTemplate;
/*    */   @Resource
/*    */   private ObjectMapper objectMapper;
/*    */   @Value("${spring.rabbitmq.accesscontrol.enabled:false}")
/*    */   private String enabled;
/*    */   
/*    */   public void recordAction(String authorityValues, User user, String result)
/*    */   {
/* 40 */     if (!"true".equals(this.enabled)) {
/* 41 */       return;
/*    */     }
/* 43 */     log.info("record user access operation userid:{},authorityValues:{},result:{}", new Object[] { user.getId(), authorityValues, result });
/* 44 */     String moeduleCode = "";
/* 45 */     String permissionCode = "";
/* 46 */     String name = user.getEmail();
/* 47 */     String[] values = authorityValues.split("_");
/* 48 */     if (values.length <= 0) {
/* 49 */       return;
/*    */     }
/* 51 */     moeduleCode = values[0];
/* 52 */     permissionCode = values[1];
/* 53 */     UserOperationRecord userOperationRecord = new UserOperationRecord();
/* 54 */     userOperationRecord.setId(String.valueOf(IdWorker.getNextId()));
/* 55 */     userOperationRecord.setModuleCode(moeduleCode);
/* 56 */     userOperationRecord.setPermissionCode(permissionCode);
/* 57 */     if (StringUtils.isEmpty(name)) {
/* 58 */       name = user.getName();
/*    */     }
/* 60 */     userOperationRecord.setOperationName(name);
/* 61 */     userOperationRecord.setOperationResult(result);
/* 62 */     userOperationRecord.setOperationTime(new Date());
/*    */     try
/*    */     {
/* 65 */       String orderJson = this.objectMapper.writeValueAsString(userOperationRecord);
/* 66 */       this.rabbitTemplate.convertAndSend("access.record.exchange", "ACCESS_RECORD", orderJson);
/*    */     } catch (Exception ex) {
/* 68 */       log.error("消息发生异常,权限审计记录失败：", ex.fillInStackTrace());
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\liuyang04\Desktop\common-sdk-0.2.17.jar!\com\inspur\bss\commonsdk\authority\service\UserOperationRecordService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */