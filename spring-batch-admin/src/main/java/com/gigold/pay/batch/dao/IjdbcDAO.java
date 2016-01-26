/**
 * Title: IjdbcDAO.java
 * Description:
 * Copyright: Copyright (c) 2015
 * Company: gigold
 *
 */
package com.gigold.pay.batch.dao;

import com.gigold.pay.batch.bo.Product;

/**
 * Title: IjdbcDAO
 * Description:
 * Company: gigold
 * @author liubao
 * @date 2015年11月2日下午12:02:37
 */
public interface IjdbcDAO
{
    public Product queryProduct(int id);
    public void updateProduct(Product pro);
    public void insertProduct(Product pro);
}
