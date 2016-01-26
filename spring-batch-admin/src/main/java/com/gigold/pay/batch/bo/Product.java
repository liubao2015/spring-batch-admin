/**
 * Title: Product.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.batch.bo;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gigold.pay.framework.core.Domain;

/**
 * Title: Product<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * @author liubao
 * @date 2015年10月9日下午5:41:02
 *
 */
@Component
@Scope("prototype")
public class Product extends Domain implements Serializable
{
    /** serialVersionUID */
    private static final long serialVersionUID = -2547220026313288834L;
    
    private int id;
    private String name;
    private String description;
    private int quantity;
    
    public Product()
    {
    }

    public Product(int id, String name, String description, int quantity) 
    {
        this.id = id;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }
}
