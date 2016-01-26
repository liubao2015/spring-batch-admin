package com.gigold.pay.batch;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        String springConfig = "jobs/file-import-job.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(springConfig);
    }
}
