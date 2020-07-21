 package com.inspur.bss.commonsdk.authority.bean;
 
 import com.fasterxml.jackson.annotation.JsonFormat;
 import java.io.Serializable;
 import java.util.Date;
 
 
 
 
 public class UserOperationRecord
   implements Serializable
 {
   private String id;
   private String moduleCode;
   private String permissionCode;
   private String permissionDesc;
   private String operationName;
   private String operationResult;
   @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
   private Date operationTime;
   private String remark;
   
   public String getId()
   {
/* 25 */     return this.id;
   }
   
   public void setId(String id) {
/* 29 */     this.id = id;
   }
   
   public String getModuleCode() {
/* 33 */     return this.moduleCode;
   }
   
   public void setModuleCode(String moduleCode) {
/* 37 */     this.moduleCode = moduleCode;
   }
   
   public String getPermissionCode() {
/* 41 */     return this.permissionCode;
   }
   
   public void setPermissionCode(String permissionCode) {
/* 45 */     this.permissionCode = permissionCode;
   }
   
   public String getPermissionDesc() {
/* 49 */     return this.permissionDesc;
   }
   
   public void setPermissionDesc(String permissionDesc) {
/* 53 */     this.permissionDesc = permissionDesc;
   }
   
   public String getOperationName() {
/* 57 */     return this.operationName;
   }
   
   public void setOperationName(String operationName) {
/* 61 */     this.operationName = operationName;
   }
   
   public String getOperationResult() {
/* 65 */     return this.operationResult;
   }
   
   public void setOperationResult(String operationResult) {
/* 69 */     this.operationResult = operationResult;
   }
   
   public Date getOperationTime() {
/* 73 */     return this.operationTime;
   }
   
   public void setOperationTime(Date operationTime) {
/* 77 */     this.operationTime = operationTime;
   }
   
   public String getRemark() {
/* 81 */     return this.remark;
   }
   
   public void setRemark(String remark) {
/* 85 */     this.remark = remark;
   }
   
   public String toString()
   {
/* 90 */     return "UserOperationRecord{id='" + this.id + '\'' + ", moduleCode='" + this.moduleCode + '\'' + ", permissionCode='" + this.permissionCode + '\'' + ", permissionDesc='" + this.permissionDesc + '\'' + ", operationName='" + this.operationName + '\'' + ", operationResult='" + this.operationResult + '\'' + ", operationTime=" + this.operationTime + ", remark='" + this.remark + '\'' + '}';
   }
 }


/* Location:              C:\Users\liuyang04\Desktop\common-sdk-0.2.17.jar!\com\inspur\bss\commonsdk\authority\bean\UserOperationRecord.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */