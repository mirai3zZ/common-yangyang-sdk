 package com.inspur.bss.commonsdk.utils;
 
 import com.inspur.bss.commonsdk.bean.User;
 import org.keycloak.KeycloakSecurityContext;
 import org.keycloak.adapters.OidcKeycloakAccount;
 import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
 import org.keycloak.representations.AccessToken;
 import org.keycloak.representations.AccessToken.Access;
 import org.springframework.security.core.Authentication;
 import org.springframework.security.core.context.SecurityContext;
 import org.springframework.security.core.context.SecurityContextHolder;
 
 
 
 public class SecurityToken
 {
   public static User getLoginUser()
   {
/* 19 */     Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
/* 20 */     if ((authentication instanceof KeycloakAuthenticationToken)) {
/* 21 */       KeycloakAuthenticationToken tokenObj = (KeycloakAuthenticationToken)authentication;
/* 22 */       if (!tokenObj.isAuthenticated()) {
/* 23 */         throw new RuntimeException("Authenticated:false");
       }
/* 25 */       AccessToken token = tokenObj.getAccount().getKeycloakSecurityContext().getToken();
       
/* 27 */       String sub = token.getSubject();
/* 28 */       String loginName = token.getPreferredUsername();
/* 29 */       String email = token.getEmail();
/* 30 */       User loginUser = new User();
/* 31 */       loginUser.setName(loginName);
/* 32 */       loginUser.setId(sub);
/* 33 */       loginUser.setEmail(email);
/* 34 */       loginUser.setRoles(token.getRealmAccess().getRoles());
/* 35 */       loginUser.setDisplayName(token.getFamilyName() + token.getGivenName());
/* 36 */       return loginUser;
     }
/* 38 */     return null;
   }
 }

