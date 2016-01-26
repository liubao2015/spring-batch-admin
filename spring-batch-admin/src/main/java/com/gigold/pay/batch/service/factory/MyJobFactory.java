/**
 * Title: MyJobFactory.java
 * Description:
 * Copyright: Copyright (c) 2015
 * Company: gigold
 */
package com.gigold.pay.batch.service.factory;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

/**
 * Title: MyJobFactory
 * Description:
 * Company: gigold
 * @author liubao
 * @date 2015年12月17日上午9:26:44
 */
public class MyJobFactory extends org.springframework.scheduling.quartz.SpringBeanJobFactory
{
    @Autowired
    private AutowireCapableBeanFactory beanFactory;
    
    /* （non Javadoc）
     * Title: createJobInstance
     * <p>Description:
     * @param bundle
     * @return
     * @throws Exception
     * @see org.springframework.scheduling.quartz.SpringBeanJobFactory#createJobInstance(org.quartz.spi.TriggerFiredBundle)
     */
    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception
    {
        Object jobInstance =  super.createJobInstance(bundle);
        beanFactory.autowireBean(jobInstance);
        return jobInstance;
    }
}
