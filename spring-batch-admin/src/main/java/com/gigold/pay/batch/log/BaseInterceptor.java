/**
 * Title: BaseIntercept.java
 * Description:
 * Copyright: Copyright (c) 2015
 * Company: gigold
 */
package com.gigold.pay.batch.log;

import org.springframework.beans.factory.annotation.Autowired;

import com.gigold.pay.framework.bootstrap.SystemPropertyConfigure;
import com.gigold.pay.framework.core.Context;
import com.gigold.pay.framework.core.RequestInfo;
import com.gigold.pay.framework.core.TransactionInfo;
import com.gigold.pay.framework.dao.TransactionInfoDAO;

/**
 * Title: BaseIntercept
 * Description:
 * Company: gigold
 * @author liubao
 * @date 2015年11月16日上午10:05:59
 */
public class BaseInterceptor
{
    /**
     * Title: preHandle
     * Description:
     * @author liubao
     * @date 2015年11月16日上午10:24:27
     */
    public static void preHandle()
    {
        // 初始化当前上下文
        Context ctx = Context.createContext("currentContext", null);
        Context.pushCurrentContext(ctx);
        //initTxInfo();
    }
    
    /**
     * Title: initTxInfo
     * Description:
     * @author liubao
     * @date 2015年11月16日上午10:32:51
     * @return
     */
    private static TransactionInfo initTxInfo() 
    {
        TransactionInfo inputMessage = new TransactionInfo();
        TransactionInfo txIf = new TransactionInfo();
        txIf.setAppId("BATCHAPP");
        txIf.setCode("");
        txIf.setDesc("");
        txIf.setLogSwitch("Y");
        txIf.setModId("URM");
        txIf.setPwdSwitch("N");
        txIf.setTxSwitch("Y");
        TransactionInfo.setCurInstance(txIf);
        return inputMessage;
    }
}
