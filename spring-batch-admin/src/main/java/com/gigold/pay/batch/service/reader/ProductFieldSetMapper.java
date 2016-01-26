/**
 * Title: ProductFieldSetMapper.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.batch.service.reader;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.gigold.pay.batch.bo.Product;
import com.gigold.pay.framework.core.Context;

/**
 * Title: ProductFieldSetMapper
 * Description:将FieldSet组成一个bean
 * Company: gigold
 * @author liubao
 * @date 2015年10月10日上午9:47:26
 *
 */
public class ProductFieldSetMapper implements FieldSetMapper<Product>
{
    /* （non Javadoc）
     * Title: mapFieldSet
     * <p>Description: 映射对象
     * @param arg0
     * @return
     * @throws BindException
     * @see org.springframework.batch.item.file.mapping.FieldSetMapper#mapFieldSet(org.springframework.batch.item.file.transform.FieldSet)
     */
    @Override
    public Product mapFieldSet(FieldSet arg0) throws BindException 
    {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhhmmssSSS");
        System.out.println("spring batch读取数据:"+sdf.format(new Date()));
        Product pro = new Product();
        pro.setId(arg0.readInt("id"));
        pro.setDescription(arg0.readString("description"));
        pro.setName(arg0.readString("name"));
        pro.setQuantity(arg0.readInt("quantity"));
        return pro;
    }
}
