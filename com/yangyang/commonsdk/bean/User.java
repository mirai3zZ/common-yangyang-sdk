 package com.inspur.bss.commonsdk.bean;
 
 import java.util.Set;
 
 
 
 
 
 
 
 
 
 
 
 
 public class User
 {
   private String id;
   private String name;
   private String displayName;
   private String email;
   private Set<String> roles;
   
   public String getId()
   {
/* 26 */     return this.id;
   }
   
   public void setId(String id) {
/* 30 */     this.id = id;
   }
   
   public String getName() {
/* 34 */     return this.name;
   }
   
   public void setName(String name) {
/* 38 */     this.name = name;
   }
   
   public String getDisplayName() {
/* 42 */     return this.displayName;
   }
   
   public void setDisplayName(String displayName) {
/* 46 */     this.displayName = displayName;
   }
   
   public String getEmail() {
/* 50 */     return this.email;
   }
   
   public void setEmail(String email) {
/* 54 */     this.email = email;
   }
   
   public Set<String> getRoles() {
/* 58 */     return this.roles;
   }
   
   public void setRoles(Set<String> roles) {
/* 62 */     this.roles = roles;
   }
   
   public String toString()
   {
/* 67 */     return "User{id='" + this.id + '\'' + ", name='" + this.name + '\'' + ", displayName='" + this.displayName + '\'' + ", email='" + this.email + '\'' + ", roles=" + this.roles + '}';
   }
 }


