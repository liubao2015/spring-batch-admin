/**
 * Title: BatchConfiguration.java
 * Description:
 * Copyright: Copyright (c) 2015
 * Company: gigold
 */
package com.gigold.pay.batch.service.config;

import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Title: BatchConfiguration
 * Description:
 * Company: gigold
 * @author liubao
 * @date 2015年12月17日下午3:54:02
 */
public class BatchConfiguration
{
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    public String job(String str)
    {
        return jobBuilderFactory.get(str).toString();
    }
}
