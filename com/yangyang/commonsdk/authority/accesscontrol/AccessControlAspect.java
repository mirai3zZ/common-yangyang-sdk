 package com.inspur.bss.commonsdk.authority.accesscontrol;
 
 import com.fasterxml.jackson.databind.ObjectMapper;
 import com.inspur.bss.commonsdk.authority.service.IUserOperationRecordService;
 import com.inspur.bss.commonsdk.bean.User;
 import com.inspur.bss.commonsdk.utils.SecurityToken;
 import java.io.OutputStream;
 import java.lang.reflect.Method;
 import java.util.Date;
 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletResponse;
 import org.aspectj.lang.ProceedingJoinPoint;
 import org.aspectj.lang.annotation.Around;
 import org.aspectj.lang.annotation.Aspect;
 import org.aspectj.lang.annotation.Pointcut;
 import org.aspectj.lang.reflect.MethodSignature;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.data.redis.core.HashOperations;
 import org.springframework.data.redis.core.RedisTemplate;
 import org.springframework.stereotype.Component;
 import org.springframework.web.context.request.RequestAttributes;
 import org.springframework.web.context.request.RequestContextHolder;
 import org.springframework.web.context.request.ServletRequestAttributes;
 
 
 
 
 
 
 
 
 
 @Aspect
 @Component
 public class AccessControlAspect
 {
/*  39 */   private static Logger log = LoggerFactory.getLogger(AccessControlAspect.class);
   
   @Resource
   private RedisTemplate<String, Object> redisTemplate;
   private final IUserOperationRecordService userOperationRecordService;
   
   @Autowired
   public AccessControlAspect(IUserOperationRecordService userOperationRecordService)
   {
/*  48 */     this.userOperationRecordService = userOperationRecordService;
   }
   
 
 
   @Pointcut("@annotation(com.inspur.bss.commonsdk.authority.accesscontrol.AccessControl)")
   public void access() {}
   
 
 
   @Around("access()")
   public Object judgePermission(ProceedingJoinPoint proceedingJoinPoint)
     throws Throwable
   {
/*  62 */     User user = SecurityToken.getLoginUser();
     try {
/*  64 */       RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
       
/*  66 */       HttpServletResponse response = ((ServletRequestAttributes)requestAttributes).getResponse();
/*  67 */       MethodSignature signature = (MethodSignature)proceedingJoinPoint.getSignature();
       
/*  69 */       Method method = signature.getMethod();
       
/*  71 */       String methodName = method.getName();
/*  72 */       Method realMethod = proceedingJoinPoint.getTarget().getClass().getDeclaredMethod(signature.getName(), method.getParameterTypes());
/*  73 */       AccessControl accessControl = (AccessControl)realMethod.getAnnotation(AccessControl.class);
       
/*  75 */       String configAuthorityValues = accessControl.value();
/*  76 */       if (null == user) {
/*  77 */         log.error("access_control REFUSE for method_name:{},the token is illegal,time:{}", methodName, new Date());
/*  78 */         dealError(response);
/*  79 */         return null;
       }
/*  81 */       String userId = user.getId();
/*  82 */       log.debug("access control userId:{},method_name:{},value:{}", new Object[] { userId, methodName, configAuthorityValues });
/*  83 */       permissionsObj = this.redisTemplate.opsForHash().get("access-controll:accessKey-" + userId, "permissions");
     } catch (Throwable e) { Object permissionsObj;
/*  85 */       log.error("access controll failure", e);
/*  86 */       throw new RuntimeException("access controll failure", e); }
     HttpServletResponse response;
/*  88 */     String methodName; String userId; Object permissionsObj; String configAuthorityValues; if ((permissionsObj != null) && (!"".equals(permissionsObj)) && 
/*  89 */       (checkHasAuthority(permissionsObj.toString(), configAuthorityValues))) {
/*  90 */       log.debug("access control pass method_name:{},value:{}", new Object[] { methodName, configAuthorityValues, new Date() });
/*  91 */       this.userOperationRecordService.recordAction(configAuthorityValues, user, "通过");
/*  92 */       return proceedingJoinPoint.proceed();
     }
     
/*  95 */     log.debug("access_control REFUSE or NOTCONTROLLED for method_name:{},userid:{},value:{}", new Object[] { methodName, userId, configAuthorityValues });
/*  96 */     this.userOperationRecordService.recordAction(configAuthorityValues, user, "未通过");
/*  97 */     dealError(response);
/*  98 */     return null;
   }
   
   private boolean checkHasAuthority(String userPermisions, String configPermissions) {
/* 102 */     return ("," + userPermisions + ",").contains("," + configPermissions + ",");
   }
   
   private void dealError(HttpServletResponse response) {
     try {
/* 107 */       response.setCharacterEncoding("UTF-8");
/* 108 */       response.setContentType("application/json; charset=UTF-8");
/* 109 */       OutputStream outputStream = response.getOutputStream();
/* 110 */       ResponseBean responseBean = new ResponseBean("403", "您没有该操作权限");
/* 111 */       outputStream.write(new ObjectMapper().writeValueAsString(responseBean).getBytes("UTF-8"));
     } catch (Exception e) {
/* 113 */       e.getStackTrace();
     }
   }
   
   class ResponseBean {
     String code;
     String message;
     
     public ResponseBean(String code, String message) {
/* 122 */       this.code = code;
/* 123 */       this.message = message;
     }
     
     public String getCode() {
/* 127 */       return this.code;
     }
     
     public void setCode(String code) {
/* 131 */       this.code = code;
     }
     
     public String getMessage() {
/* 135 */       return this.message;
     }
     
     public void setMessage(String message) {
/* 139 */       this.message = message;
     }
   }
 }


/* Location:              C:\Users\liuyang04\Desktop\common-sdk-0.2.17.jar!\com\inspur\bss\commonsdk\authority\accesscontrol\AccessControlAspect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */