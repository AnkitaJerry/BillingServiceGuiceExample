/* 
  * ============================================================================ 
  * Name      : BillingModule.java
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
package billing;

import transactionLog.DatabaseTransactionLog;
import transactionLog.TransactionLog;
import util.ReadConfig;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

import creditCard.CreditCardProcessor;
import creditCard.GoogleCheckoutCreditCardProcessor;
import creditCard.PaypalCreditCardProcessor;
import creditCard.PayulatamCreditCardProcessor;
import creditCard.TipoPagos;
import creditCard.CreditCardProcessorLoader;


public class BillingModule extends AbstractModule {
  private static int paymentMethod=0;	
  private static String customPaymentMethod;

  public static void setPaymentMethod(int tipoPago){
	  BillingModule.paymentMethod=tipoPago;
  }
  
  public static void setPaymentMethod(String customPaymentMethod){
	  BillingModule.customPaymentMethod = customPaymentMethod;  
	  BillingModule.paymentMethod=0;
  }
  
  @Override 
  protected void configure() {
	  	bind(TransactionLog.class).to(DatabaseTransactionLog.class);

		bind(BillingService.class).to(RealBillingService.class);
  }
  
  
  @Provides
  public CreditCardProcessor getPaymentMethod() throws Exception {
	  	if(paymentMethod != 0){
			switch (paymentMethod) {
			case TipoPagos.PAY_PAL:
				return new PaypalCreditCardProcessor();
			case TipoPagos.GOOGLE:
				return new GoogleCheckoutCreditCardProcessor();
			case TipoPagos.PAY_ULATAM:
				return new PayulatamCreditCardProcessor();
			default:
				throw new Exception("Payment Method not supported");
			}
	  	}else{
	  		boolean supported = new ReadConfig()
	  					.checkIfSupportedProcessor(BillingModule.customPaymentMethod);
	  		System.out.println(supported);
	  		if(supported){
	  			return new CreditCardProcessorLoader()
				.getDefaultProcessor(BillingModule.customPaymentMethod);
	  		}else{
	  			throw new Exception("Payment Method not supported." );
	  		}
	  	}
	}
}