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

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import transactionLog.DatabaseTransactionLog;
import transactionLog.TransactionLog;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

import creditCard.CreditCardProcessor;
import creditCard.GoogleCheckoutCreditCardProcessor;
import creditCard.PaypalCreditCardProcessor;
import creditCard.PayulatamCreditCardProcessor;
import creditCard.TipoPagos;

public class BillingModule extends AbstractModule {
  private static int tipoPago=1;	
	
  public static void setTipoPago(int tipoPago){
	  BillingModule.tipoPago=tipoPago;
  }
  
  @Override 
  protected void configure() {
	  	bind(TransactionLog.class).to(DatabaseTransactionLog.class);

		bind(BillingService.class).to(RealBillingService.class);
  }
  
  @Provides
  public CreditCardProcessor getTipoPago() {
		switch (tipoPago) {
		case TipoPagos.PAY_PAL:
			return new PaypalCreditCardProcessor();
		case TipoPagos.GOOGLE:
			return new GoogleCheckoutCreditCardProcessor();
		case TipoPagos.PAY_ULATAM:
			return new PayulatamCreditCardProcessor();
		case TipoPagos.DEFAULT:
			return getDefaultProcessor();
		default:
			return null;
		}
	}
  
private CreditCardProcessor getDefaultProcessor() {
	String fuenteJava = "C:/tmp/defaultPays/Default.java";
	JavaCompiler compilador = ToolProvider.getSystemJavaCompiler();
	int resultado = compilador.run(null, null, null, fuenteJava);
	
	// Create a File object on the root of the directory containing the class file
	File file = new File("C:/tmp/defaultPays/");

	try {
	    // Convert File to a URL
	    URL url = file.toURL();          // file:/c:/myclasses/
	    URL[] urls = new URL[]{url};

	    // Create a new class loader with the directory
	    ClassLoader cl = new URLClassLoader(urls);

	    // Load in the class; MyClass.class should be located in
	    // the directory file:/c:/myclasses/com/mycompany
	    Class cls = cl.loadClass("Default");
	    
	    return (CreditCardProcessor)cls.newInstance();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}
  
}