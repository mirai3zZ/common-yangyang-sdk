 package com.inspur.bss.commonsdk.utils;
 
 import java.io.IOException;
 import java.io.UnsupportedEncodingException;
 import java.security.InvalidKeyException;
 import java.security.NoSuchAlgorithmException;
 import java.security.SecureRandom;
 import javax.crypto.BadPaddingException;
 import javax.crypto.Cipher;
 import javax.crypto.IllegalBlockSizeException;
 import javax.crypto.KeyGenerator;
 import javax.crypto.NoSuchPaddingException;
 import javax.crypto.SecretKey;
 import javax.crypto.spec.SecretKeySpec;
 
 
 
 
 
 
 
 
 
 
 public class AesCrypt
 {
   public static String encrypt(String content, String passWord)
   {
     try
     {
/*  31 */       SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
/*  32 */       random.setSeed(passWord.getBytes());
       
/*  34 */       KeyGenerator keygen = KeyGenerator.getInstance("AES");
       
 
/*  37 */       keygen.init(128, random);
       
/*  39 */       SecretKey originalKey = keygen.generateKey();
       
/*  41 */       byte[] raw = originalKey.getEncoded();
       
/*  43 */       SecretKey key = new SecretKeySpec(raw, "AES");
       
/*  45 */       Cipher cipher = Cipher.getInstance("AES");
       
/*  47 */       cipher.init(1, key);
       
/*  49 */       byte[] byteEncode = content.getBytes("utf-8");
       
/*  51 */       byte[] byteAES = cipher.doFinal(byteEncode);
       
/*  53 */       return parseByte2HexStr(byteAES);
     } catch (NoSuchAlgorithmException e) {
/*  55 */       e.printStackTrace();
     } catch (NoSuchPaddingException e) {
/*  57 */       e.printStackTrace();
     } catch (InvalidKeyException e) {
/*  59 */       e.printStackTrace();
     } catch (IllegalBlockSizeException e) {
/*  61 */       e.printStackTrace();
     } catch (BadPaddingException e) {
/*  63 */       e.printStackTrace();
     } catch (UnsupportedEncodingException e) {
/*  65 */       e.printStackTrace();
     }
     
/*  68 */     return null;
   }
   
 
 
 
 
   public static String decrypt(String content, String passWord)
   {
     try
     {
/*  79 */       SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
/*  80 */       random.setSeed(passWord.getBytes());
       
/*  82 */       KeyGenerator keygen = KeyGenerator.getInstance("AES");
       
 
/*  85 */       keygen.init(128, random);
       
/*  87 */       SecretKey originalKey = keygen.generateKey();
       
/*  89 */       byte[] raw = originalKey.getEncoded();
       
/*  91 */       SecretKey key = new SecretKeySpec(raw, "AES");
       
/*  93 */       Cipher cipher = Cipher.getInstance("AES");
       
/*  95 */       cipher.init(2, key);
       
/*  97 */       byte[] byteContent = parseHexStr2Byte(content);
       
 
/* 100 */       byte[] byteDecode = cipher.doFinal(byteContent);
/* 101 */       return new String(byteDecode, "utf-8");
     } catch (NoSuchAlgorithmException e) {
/* 103 */       e.printStackTrace();
     } catch (NoSuchPaddingException e) {
/* 105 */       e.printStackTrace();
     } catch (InvalidKeyException e) {
/* 107 */       e.printStackTrace();
     } catch (IOException e) {
/* 109 */       e.printStackTrace();
     } catch (IllegalBlockSizeException e) {
/* 111 */       e.printStackTrace();
     } catch (BadPaddingException e) {
/* 113 */       e.printStackTrace();
     }
     
/* 116 */     return null;
   }
   
 
 
 
 
 
   private static String parseByte2HexStr(byte[] buf)
   {
/* 126 */     StringBuffer sb = new StringBuffer();
/* 127 */     for (int i = 0; i < buf.length; i++) {
/* 128 */       String hex = Integer.toHexString(buf[i] & 0xFF);
/* 129 */       if (hex.length() == 1) {
/* 130 */         hex = '0' + hex;
       }
/* 132 */       sb.append(hex.toUpperCase());
     }
/* 134 */     return sb.toString();
   }
   
 
 
 
 
 
   private static byte[] parseHexStr2Byte(String hexStr)
   {
/* 144 */     if (hexStr.length() < 1) {
/* 145 */       return null;
     }
/* 147 */     byte[] result = new byte[hexStr.length() / 2];
/* 148 */     for (int i = 0; i < hexStr.length() / 2; i++) {
/* 149 */       int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
/* 150 */       int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
/* 151 */       result[i] = ((byte)(high * 16 + low));
     }
/* 153 */     return result;
   }
 }
