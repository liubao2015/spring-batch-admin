/**
 * Title: ProductItemProcessor.java
 * Description: 
 * Copyright: Copyright (c) 2015
 * Company: gigold
 *
 */
package com.gigold.pay.batch.service.process;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import com.gigold.pay.batch.bo.Product;
import com.gigold.pay.batch.dao.IjdbcDAO;

/**
 * Title: ProductItemProcessor
 * Description: 对写入数据进行处理
 * Company: gigold
 * @author liubao
 * @date 2015年10月10日上午11:07:42
 *
 */
public class ProductItemProcessor implements ItemProcessor<Product, Product> 
{
    @Autowired
    private IjdbcDAO jdbcDao;
    
    /* （non Javadoc）
     * Title: process
     * <p>Description:
     * @param arg0
     * @return
     * @throws Exception
     * @see org.springframework.batch.item.ItemProcessor#process(java.lang.Object)
     */
    @Override
    public Product process(Product arg0) throws Exception 
    {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhhmmssSSS");
        System.out.println("spring batch处理数据："+sdf.format(new Date()));
        //判断数据是否数据是否存在
        try
        {
            Product p = jdbcDao.queryProduct(arg0.getId());
            if (p!=null) 
            {
                arg0.setQuantity( p.getQuantity() + arg0.getQuantity() );
            }
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        return arg0;
    }
}
