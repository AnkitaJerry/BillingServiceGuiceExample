/* 
  * ============================================================================ 
  * Name      : CreditCardProcessorFactory.java
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
package creditCard;

import creditCard.CreditCardProcessor;

public class CreditCardProcessorFactory {
  
  private static CreditCardProcessor instance;
  
  public static void setInstance(CreditCardProcessor creditCardProcessor) {
    instance = creditCardProcessor;
  }

  public static CreditCardProcessor getInstance() {
    if (instance == null) {
      return new PaypalCreditCardProcessor();
    }
    
    return instance;
  }
}