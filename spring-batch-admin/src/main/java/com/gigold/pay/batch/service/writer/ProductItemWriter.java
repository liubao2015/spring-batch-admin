/**
 * Title: ProductItemWriter.java
 * Description: 
 * Copyright: Copyright (c) 2015
 * Company: gigold
 *
 */
package com.gigold.pay.batch.service.writer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.gigold.pay.batch.bo.Product;
import com.gigold.pay.batch.dao.IjdbcDAO;

/**
 * Title: ProductItemWriter
 * Description: 将product写入数据库
 * Company: gigold
 * @author liubao
 * @date 2015年10月10日上午10:41:31
 *
 */
public class ProductItemWriter implements ItemWriter<Product> 
{
    @Autowired
    private IjdbcDAO jdbcDao;
    
    /* （non Javadoc）
     * Title: write
     * <p>Description:写入数据库
     * @param list product数据集合
     * @throws Exception
     * @see org.springframework.batch.item.ItemWriter#write(java.util.List)
     */
    @Override
    public void write(List<? extends Product> list) throws Exception 
    {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhhmmssSSS");
        System.out.println("spring batch写入数据："+sdf.format(new Date()));
        for (Product product:list)
        {
            //判断数据是否数据是否存在
            Product p = jdbcDao.queryProduct(product.getId());
            
            if(p!=null)
            {
                jdbcDao.updateProduct(product);
            }
            else
            {
                jdbcDao.insertProduct(product);
            }
        }
    }
}
