 package com.inspur.bss.commonsdk.redis;
 
 import org.springframework.beans.factory.annotation.Value;
 import org.springframework.context.annotation.Configuration;
 
 
 
 
 
 
 
 
 
 
 
 @Configuration
 public class RedisSerializerProperties
 {
   public static final String SERIALIZER_TYPE_STRING = "string";
   public static final String SERIALIZER_TYPE_JSON = "json";
   @Value("${sdk.common.redis.serializer.value:json}")
   private String valueSerializer;
   @Value("${sdk.common.redis.serializer.hash-value:json}")
   private String hashValueSerializer;
   
   public String getValueSerializer()
   {
/* 28 */     return this.valueSerializer;
   }
   
   public void setValueSerializer(String valueSerializer) {
/* 32 */     this.valueSerializer = valueSerializer;
   }
   
   public String getHashValueSerializer() {
/* 36 */     return this.hashValueSerializer;
   }
   
   public void setHashValueSerializer(String hashValueSerializer) {
/* 40 */     this.hashValueSerializer = hashValueSerializer;
   }
 }
