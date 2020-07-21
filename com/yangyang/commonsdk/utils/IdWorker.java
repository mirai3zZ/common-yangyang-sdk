 package com.inspur.bss.commonsdk.utils;
 
 import java.lang.management.ManagementFactory;
 import java.lang.management.RuntimeMXBean;
 import java.net.NetworkInterface;
 import java.net.SocketException;
 import java.util.Enumeration;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 public class IdWorker
 {
/*  29 */   private final long workerIdBits = 5L;
   
 
 
 
/*  34 */   private final long datacenterIdBits = 5L;
   
 
 
 
   private long workerId;
   
 
 
 
   private long datacenterId;
   
 
 
 
/*  49 */   private long sequence = 0L;
   
 
 
 
/*  54 */   private long lastTimestamp = -1L;
   
 
 
/*  58 */   private static IdWorker idWorker = new IdWorker();
   
 
 
 
 
 
   public static synchronized long getNextId()
   {
/*  67 */     return idWorker.nextId();
   }
   
   private IdWorker() {
/*  71 */     this.workerId = getWorkerId();
/*  72 */     this.datacenterId = getDatacenterId();
     
/*  74 */     long maxWorkerId = 31L;
/*  75 */     if ((this.workerId > maxWorkerId) || (this.workerId < 0L)) {
/*  76 */       throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", new Object[] { Long.valueOf(maxWorkerId) }));
     }
     
/*  79 */     long maxDatacenterId = 31L;
/*  80 */     if ((this.datacenterId > maxDatacenterId) || (this.datacenterId < 0L)) {
/*  81 */       throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", new Object[] { Long.valueOf(maxDatacenterId) }));
     }
   }
   
 
 
 
 
 
   private synchronized long nextId()
   {
/*  92 */     long timestamp = timeGen();
     
 
/*  95 */     if (timestamp < this.lastTimestamp)
     {
/*  97 */       throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", new Object[] {Long.valueOf(this.lastTimestamp - timestamp) }));
     }
     
 
/* 101 */     long sequenceBits = 12L;
     
/* 103 */     if (this.lastTimestamp == timestamp)
     {
/* 105 */       long sequenceMask = 0xFFFFFFFFFFFFFFFF ^ -1L << (int)sequenceBits;
/* 106 */       this.sequence = (this.sequence + 1L & sequenceMask);
       
/* 108 */       if (this.sequence == 0L)
       {
/* 110 */         timestamp = tilNextMillis(this.lastTimestamp);
       }
     }
     else
     {
/* 115 */       this.sequence = 0L;
     }
     
 
/* 119 */     this.lastTimestamp = timestamp;
     
 
 
/* 123 */     long twepoch = 1514736000000L;
     
 
/* 126 */     long datacenterIdShift = sequenceBits + 5L;
     
/* 128 */     long timestampLeftShift = sequenceBits + 5L + 5L;
/* 129 */     return timestamp - twepoch << (int)timestampLeftShift | this.datacenterId << (int)datacenterIdShift | this.workerId << (int)sequenceBits | this.sequence;
   }
   
 
 
 
 
 
 
 
 
 
   private long tilNextMillis(long lastTimestamp)
   {
/* 143 */     long timestamp = timeGen();
/* 144 */     while (timestamp <= lastTimestamp) {
/* 145 */       timestamp = timeGen();
     }
/* 147 */     return timestamp;
   }
   
 
 
 
 
   private long timeGen()
   {
/* 156 */     return System.currentTimeMillis();
   }
   
 
 
 
 
 
   private long getWorkerId()
   {
/* 166 */     StringBuilder sb = new StringBuilder();
/* 167 */     Enumeration<NetworkInterface> e = null;
     try {
/* 169 */       e = NetworkInterface.getNetworkInterfaces();
     } catch (SocketException e1) {
/* 171 */       e1.printStackTrace();
     }
/* 173 */     while ((e != null) && (e.hasMoreElements())) {
/* 174 */       NetworkInterface ni = (NetworkInterface)e.nextElement();
/* 175 */       sb.append(ni.toString());
     }
/* 177 */     long machineId = sb.toString().hashCode();
     
/* 179 */     long workerIdMask = 31L;
/* 180 */     return machineId & workerIdMask;
   }
   
 
 
 
 
 
   private long getDatacenterId()
   {
/* 190 */     RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
/* 191 */     long datacenterId = Long.valueOf(runtimeMXBean.getName().split("@")[0]).longValue();
     
/* 193 */     long datacenterIdMask = 31L;
/* 194 */     return datacenterId & datacenterIdMask;
   }
 }

