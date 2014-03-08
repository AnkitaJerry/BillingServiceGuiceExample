/* 
  * ============================================================================ 
  * Name      : CreditCard.java
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


/**
 * 
 *
 */
public class CreditCard {
    
    String number;
    int expiryMonth;
    int expiryYear;

    public CreditCard(String number, int expiryMonth, int expiryYear) {
        super();
        this.number = number;
        this.expiryMonth = expiryMonth;
        this.expiryYear = expiryYear;
    }

    @Override
    public String toString() {
        return "CreditCard Number=" + 
        		number.toString().replaceAll("^[1-9]{12}", "XXXXXXXXXXXX");
    }
}
