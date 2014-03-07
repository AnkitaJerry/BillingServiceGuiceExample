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
  private static int tipoPago=0;	
  private static String customPaymentMethod;
	
  public static void setTipoPago(int tipoPago){
	  System.out.println("PaymentsInMenu");
	  BillingModule.tipoPago=tipoPago;
  }
  
  public static void setTipoPago(String customPaymentMethod){
	  System.out.println("CustomPayment");
	  BillingModule.customPaymentMethod = customPaymentMethod;  
	  tipoPago=0;
  }
  
  @Override 
  protected void configure() {
	  	bind(TransactionLog.class).to(DatabaseTransactionLog.class);

		bind(BillingService.class).to(RealBillingService.class);
  }
  
  @Provides
  public CreditCardProcessor getTipoPago() throws Exception {
	  	System.out.println(tipoPago);
	  	if(tipoPago != 0){
	  		System.out.println("Entro");
			switch (tipoPago) {
			case TipoPagos.PAY_PAL:
				return new PaypalCreditCardProcessor();
			case TipoPagos.GOOGLE:
				return new GoogleCheckoutCreditCardProcessor();
			case TipoPagos.PAY_ULATAM:
				return new PayulatamCreditCardProcessor();
			case TipoPagos.DEFAULT:
				return new CreditCardProcessorLoader()
				.getDefaultProcessor("Custom");
			default:
				throw new Exception("Payment Method not supported");
			}
	  	}else{
	  		boolean supported = new ReadConfig().checkIfSupportedProcessor(BillingModule.customPaymentMethod);
			System.out.println(supported + " : " + BillingModule.customPaymentMethod);
	  		return supported 
			
			?  new CreditCardProcessorLoader()
					.getDefaultProcessor(BillingModule.customPaymentMethod)

			:	null;
	  	}
	}
}