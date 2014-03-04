/* 
  * ============================================================================ 
  * Name      : DatabaseTransactionLog.java
  * Part of     :  NEON
  * 
  * Copyright (c) 2007-2011 Nokia.  All rights reserved.
  * This material, including documentation and any related computer
  * programs, is protected by copyright controlled by Nokia.  All
  * rights are reserved.  Copying, including reproducing, storing,
  * adapting or translating, any or all of this material requires the
  * prior written consent of Nokia.  This material also contains
  * confidential information which may not be disclosed to others
  * without the prior written consent of Nokia.
 * 
  * ============================================================================
  */
package transactionLog;

import billing.ChargeResult;
import billing.UnreachableException;

/**
 * 
 *
 */
public class DatabaseTransactionLog implements TransactionLog {

    /* (non-Javadoc)
     * @see com.agisoft.app.juice.TransactionLog#logChargeResult(com.agisoft.app.juice.ChargeResult)
     */
    @Override
    public void logChargeResult(ChargeResult result) {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see com.agisoft.app.juice.TransactionLog#logConnectException(com.agisoft.app.juice.UnreachableException)
     */
    @Override
    public void logConnectException(UnreachableException e) {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see com.agisoft.app.juice.TransactionLog#wasSuccessLogged()
     */
    @Override
    public boolean wasSuccessLogged() {
        // TODO Auto-generated method stub
        return false;
    }

}
