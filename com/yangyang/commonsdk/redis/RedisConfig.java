 package com.inspur.bss.commonsdk.redis;
 
 import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
 import com.fasterxml.jackson.annotation.PropertyAccessor;
 import com.fasterxml.jackson.databind.ObjectMapper;
 import com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping;
 import javax.annotation.PostConstruct;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.context.annotation.Bean;
 import org.springframework.context.annotation.Configuration;
 import org.springframework.data.redis.connection.RedisConnectionFactory;
 import org.springframework.data.redis.core.RedisTemplate;
 import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
 import org.springframework.data.redis.serializer.StringRedisSerializer;
 
 
 
 
 
 
 
 
 @Configuration
 public class RedisConfig
 {
/* 26 */   private StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
   
   private Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer;
   private final RedisSerializerProperties serializerProperties;
   
   @Autowired
   public RedisConfig(RedisSerializerProperties serializerProperties)
   {
/* 34 */     this.serializerProperties = serializerProperties;
   }
   
   @Bean
   public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
/* 39 */     RedisTemplate<String, Object> redisTemplate = new RedisTemplate();
/* 40 */     redisTemplate.setConnectionFactory(redisConnectionFactory);
/* 41 */     initializeRedisSerializer(redisTemplate);
/* 42 */     redisTemplate.afterPropertiesSet();
/* 43 */     return redisTemplate;
   }
   
   private void initializeRedisSerializer(RedisTemplate<String, Object> redisTemplate)
   {
/* 48 */     redisTemplate.setKeySerializer(this.stringRedisSerializer);
/* 49 */     redisTemplate.setHashKeySerializer(this.stringRedisSerializer);
     
 
 
/* 53 */     redisTemplate.setValueSerializer(this.jackson2JsonRedisSerializer);
/* 54 */     redisTemplate.setHashValueSerializer(this.jackson2JsonRedisSerializer);
/* 55 */     if ("string".equals(this.serializerProperties.getValueSerializer())) {
/* 56 */       redisTemplate.setValueSerializer(this.stringRedisSerializer);
     }
     
/* 59 */     if ("string".equals(this.serializerProperties.getHashValueSerializer())) {
/* 60 */       redisTemplate.setHashValueSerializer(this.stringRedisSerializer);
     }
   }
   
   @PostConstruct
   private void initializeJsonSerializer()
   {
/* 67 */     ObjectMapper objectMapper = new ObjectMapper();
/* 68 */     objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
/* 69 */     objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
     
/* 71 */     this.jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
/* 72 */     this.jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
   }
 }


