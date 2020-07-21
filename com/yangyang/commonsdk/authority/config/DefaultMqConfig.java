 package com.inspur.bss.commonsdk.authority.config;
 
 import org.springframework.amqp.rabbit.annotation.EnableRabbit;
 import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
 import org.springframework.amqp.rabbit.connection.ConnectionFactory;
 import org.springframework.amqp.rabbit.core.RabbitTemplate;
 import org.springframework.beans.factory.annotation.Qualifier;
 import org.springframework.beans.factory.annotation.Value;
 import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
 import org.springframework.context.annotation.Bean;
 import org.springframework.context.annotation.Configuration;
 import org.springframework.context.annotation.Primary;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 @Configuration
 @EnableRabbit
 @ConditionalOnProperty({"spring.rabbitmq.addresses"})
 public class DefaultMqConfig
 {
   @Value("${spring.rabbitmq.addresses}")
   private String addresses;
   @Value("${spring.rabbitmq.username}")
   private String username;
   @Value("${spring.rabbitmq.password}")
   private String password;
   @Value("${spring.rabbitmq.virtual-host}")
   private String virtualHost;
   @Value("${spring.rabbitmq.publisher-confirms}")
   private boolean publisherConfirms;
   
   @Bean(name={"defaultConnectionFactory"})
   @Primary
   public CachingConnectionFactory connectionFactory()
   {
/* 50 */     CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
/* 51 */     connectionFactory.setAddresses(this.addresses);
/* 52 */     connectionFactory.setUsername(this.username);
/* 53 */     connectionFactory.setPassword(this.password);
/* 54 */     connectionFactory.setVirtualHost(this.virtualHost);
/* 55 */     connectionFactory.setPublisherConfirms(this.publisherConfirms);
/* 56 */     return connectionFactory;
   }
   
 
 
 
 
 
 
   @Bean
   @Primary
   public RabbitTemplate rabbitTemplate(@Qualifier("defaultConnectionFactory") ConnectionFactory connectionFactory)
   {
/* 69 */     RabbitTemplate template = new RabbitTemplate(connectionFactory);
/* 70 */     return template;
   }
 }


