/**
 * Title: BaseQuartz.java
 * Description:
 * Copyright: Copyright (c) 2015
 * Company: gigold
 */
package com.gigold.pay.batch.log;

import com.gigold.pay.framework.core.Context;
import com.gigold.pay.framework.core.log.AbsLogger;
import com.gigold.pay.framework.core.log.LogFactory;
import com.gigold.pay.framework.core.log.Logger;

/**
 * Title: BaseQuartz
 * Description:
 * Company: gigold
 * @author liubao
 * @date 2015年11月10日上午11:28:19
 */
public abstract class BaseQuartz extends AbsLogger
{
      private static Logger logger = LogFactory.getLogger("JOB_QUARTZ.trc");
      
      /**
       * Title: debug
       * Description:
       * @author liubao
       * @date 2015年11月11日下午5:47:58
       * @param jobName
       * @param JobType
       * @param Param
       * @param message
       */
      public static void debug(String msgId,String jobName,String JobType,String Param,String message)
      {
          System.out.println(canLog());
          if(canLog())
          {
              StackTraceElement stack[] = (new Throwable()).getStackTrace();   
              for (int i=0; i <stack.length; i++) 
              {   
                  if(i==1)
                  {
                      StackTraceElement ste=stack[i];   
                      String str = String.format("MsgId[%s],JobType[%s],JobId[%s],Param[%s],ClassName[%s],MethodName[%s],LineNumber[%s],Msg[%s]",
                              Context.getCurrentRequestId(),
                              JobType,jobName,Param,
                              ste.getClassName(),
                              ste.getMethodName(),
                              ste.getLineNumber(),
                              message);
                      logger.debug(str);
                  }
              }
          }
      }
      
      /**
       * Title: info
       * Description:
       * @author liubao
       * @date 2015年11月11日下午5:44:48
       * @param message
       */
      public static void info(String msgId,String jobName,String JobType,String Param,String message) 
      {
          if(canLog())
          {
              StackTraceElement stack[] = (new Throwable()).getStackTrace();   
              for (int i=0; i <stack.length; i++) 
              {   
                  if(i==1)
                  {
                      StackTraceElement ste=stack[i];   
                      String str = String.format("MsgId[%s],JobType[%s],JobId[%s],Param[%s],ClassName[%s],MethodName[%s],LineNumber[%s],Msg[%s]",
                              Context.getCurrentRequestId(),
                              JobType,jobName,Param,
                              ste.getClassName(),
                              ste.getMethodName(),
                              ste.getLineNumber(),
                              message);
                      logger.info(str+","+message);
                  }
              }
          }
      }
      
      /**
       * Title: error
       * Description:
       * @author liubao
       * @date 2015年11月16日上午11:15:39
       * @param jobName
       * @param JobType
       * @param Param
       * @param message
       */
      public static void error(String msgId,String jobName,String JobType,String Param,String message) 
      {
          if(canLog())
          {
              StackTraceElement stack[] = (new Throwable()).getStackTrace();   
              for (int i=0; i <stack.length; i++) 
              {   
                  if(i==1)
                  {
                      StackTraceElement ste=stack[i];   
                      String str = String.format("MsgId[%s],JobType[%s],JobId[%s],Param[%s],ClassName[%s],MethodName[%s],LineNumber[%s],Msg[%s]",
                              Context.getCurrentRequestId(),
                              JobType,jobName,Param,
                              ste.getClassName(),
                              ste.getMethodName(),
                              ste.getLineNumber(),
                              message);
                      logger.error(str+","+message);
                  }
              }
          }
      }
      
      /**
       * Title: warn
       * Description:
       * @author liubao
       * @date 2015年11月16日上午11:16:08
       * @param jobName
       * @param JobType
       * @param Param
       * @param message
       */
      public static void warn(String msgId,String jobName,String JobType,String Param,String message)
      {
          if(canLog())
          {
              StackTraceElement stack[] = (new Throwable()).getStackTrace();   
              for (int i=0; i <stack.length; i++) 
              {   
                  if(i==1)
                  {
                      StackTraceElement ste=stack[i];   
                      String str = String.format("MsgId[%s],JobType[%s],JobId[%s],Param[%s],ClassName[%s],MethodName[%s],LineNumber[%s],Msg[%s]",
                              Context.getCurrentRequestId(),
                              JobType,jobName,Param,
                              ste.getClassName(),
                              ste.getMethodName(),
                              ste.getLineNumber(),
                              message);
                      logger.warn(str+","+message);
                  }
              }
          }
      }
}
