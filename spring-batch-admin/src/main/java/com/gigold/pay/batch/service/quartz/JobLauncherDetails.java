/**
 * Title: JobLauncherDetails.java
 * Description: 
 * Copyright: Copyright (c) 2015
 * Company: gigold
 *
 */
package com.gigold.pay.batch.service.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.gigold.pay.batch.log.BaseInterceptor;
import com.gigold.pay.batch.log.BaseQuartz;
import com.gigold.pay.framework.core.Context;

/**
 * Title: JobLauncherDetails
 * Description: 
 * Company: gigold
 * @author liubao
 * @date 2015年10月12日上午9:49:07
 *
 */
public class JobLauncherDetails extends QuartzJobBean
{
    /** serialVersionUID */
    private static final long serialVersionUID = -6287499051581253249L;

    public static final String JOB_NAME = "jobName";
    
    @Autowired
    private JobLocator jobLocator;
    @Autowired
    private JobLauncher jobLauncher;
    
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException
    {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhhmmssSSS");
        System.out.println("定时任务开始(每十五秒执行一次)："+sdf.format(new Date()));
        Map<String, Object> jobDataMap = new HashMap<String, Object>();
        jobDataMap.put("inputFile", "/Users/liubao/gongju/apache-tomcat-8.0.26/webapps/spring-batch-admin/WEB-INF/classes/cvs/input/sample.csv");
        jobDataMap.put("jobName","simpleFileImportJob");
        String jobName = (String) jobDataMap.get(JOB_NAME);
        JobParameters jobParameters = getJobParametersFromJobMap(jobDataMap);
        
        try 
        {
            jobLauncher.run(jobLocator.getJob(jobName), jobParameters);
        } 
        catch (Exception e) 
        {
              BaseInterceptor.preHandle();
              BaseQuartz.debug(Context.getCurrentRequestId(),jobName, "定时调度任务", jobDataMap.toString(), e.getMessage());
        }
    }
    
    private JobParameters getJobParametersFromJobMap(Map<String, Object> jobDataMap) 
    {
        JobParametersBuilder builder = new JobParametersBuilder();
        for (Entry<String, Object> entry : jobDataMap.entrySet()) 
        {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof String && !key.equals(JOB_NAME))
            {
                builder.addString(key, (String) value);
            }
            else if (value instanceof Float || value instanceof Double) 
            {
                builder.addDouble(key, ((Number) value).doubleValue());
            } 
            else if (value instanceof Integer || value instanceof Long) 
            {
                builder.addLong(key, ((Number) value).longValue());
            } 
            else if (value instanceof Date) 
            {
                builder.addDate(key, (Date) value);
            } 
            else 
            {
            }
        }
        builder.addDate("run date", new Date());
        return builder.toJobParameters();
    }
}
