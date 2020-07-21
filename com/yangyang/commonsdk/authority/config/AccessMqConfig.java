 package com.inspur.bss.commonsdk.authority.config;
 
 import org.springframework.amqp.core.Binding;
 import org.springframework.amqp.core.BindingBuilder;
 import org.springframework.amqp.core.BindingBuilder.DestinationConfigurer;
 import org.springframework.amqp.core.BindingBuilder.TopicExchangeRoutingKeyConfigurer;
 import org.springframework.amqp.core.Queue;
 import org.springframework.amqp.core.TopicExchange;
 import org.springframework.amqp.rabbit.annotation.EnableRabbit;
 import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
 import org.springframework.amqp.rabbit.connection.ConnectionFactory;
 import org.springframework.amqp.rabbit.core.RabbitTemplate;
 import org.springframework.beans.factory.annotation.Qualifier;
 import org.springframework.beans.factory.annotation.Value;
 import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
 import org.springframework.context.annotation.Bean;
 import org.springframework.context.annotation.Configuration;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 @Configuration
 @EnableRabbit
 @ConditionalOnProperty({"spring.rabbitmq.accesscontrol.addresses"})
 public class AccessMqConfig
 {
   @Value("${spring.rabbitmq.accesscontrol.addresses}")
   private String addresses;
   @Value("${spring.rabbitmq.accesscontrol.username}")
   private String username;
   @Value("${spring.rabbitmq.accesscontrol.password}")
   private String password;
   @Value("${spring.rabbitmq.accesscontrol.virtual-host}")
   private String virtualHost;
   @Value("${spring.rabbitmq.accesscontrol.publisher-confirms}")
   private boolean publisherConfirms;
   
   @Bean(name={"accessRecordFactory"})
   public CachingConnectionFactory connectionFactory()
   {
/*  53 */     CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
/*  54 */     connectionFactory.setAddresses(this.addresses);
/*  55 */     connectionFactory.setUsername(this.username);
/*  56 */     connectionFactory.setPassword(this.password);
/*  57 */     connectionFactory.setVirtualHost(this.virtualHost);
/*  58 */     connectionFactory.setPublisherConfirms(this.publisherConfirms);
/*  59 */     return connectionFactory;
   }
   
 
 
 
 
 
 
   @Bean(name={"accessRecordTemplate"})
   public RabbitTemplate rabbitTemplate(@Qualifier("accessRecordFactory") ConnectionFactory connectionFactory)
   {
/*  71 */     RabbitTemplate template = new RabbitTemplate(connectionFactory);
/*  72 */     return template;
   }
   
   @Bean
   TopicExchange accessExchange() {
/*  77 */     return new TopicExchange("access.record.exchange");
   }
   
 
 
 
 
 
 
   @Bean
   public Queue accessQueue()
   {
/*  89 */     return new Queue("access.record.queue");
   }
   
 
 
 
 
 
 
 
   @Bean
   public Binding bindingExchangeAccessCharge(Queue accessQueue, TopicExchange accessExchange)
   {
/* 102 */     return BindingBuilder.bind(accessQueue).to(accessExchange).with("ACCESS_RECORD");
   }
 }

