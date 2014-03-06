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
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import util.FilesUtil;

import com.google.inject.AbstractModule;

import creditCard.CreditCardProcessor;

public class DefaultModule extends AbstractModule {
  
	public String getHolaMundo(){
		return "Hola Mundo";
	}
	
  @Override 
  protected void configure() {
	  String fuenteJava = "C:/googleguice/guicedynamics/DefaultPay.java";
		JavaCompiler compilador = ToolProvider.getSystemJavaCompiler();
		int resultado = compilador.run(null, null, null, fuenteJava);
		System.out.println(resultado);
		
		byte[] bytes=null;
		try {
			bytes = new FilesUtil().read("C:/googleguice/guicedynamics/DefaultPay.class");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 Class c = bytes.getClass();
		 //bind(CreditCardProcessor.class).to(c.getClass());
  }
 
  
  public static void main2(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
	  
	  
	  
	  ClassLoader classLoader = DefaultModule.class.getClassLoader();
	  Class<?> clazz = classLoader.loadClass("com.example.MyClass");
	  
	  	String fuenteJava = "C:/googleguice/guicedynamics/DefaultPay.java";
		JavaCompiler compilador = ToolProvider.getSystemJavaCompiler();
		int resultado = compilador.run(null, null, null, fuenteJava);
	  
	    

	    try {
	        Class aClass = classLoader.loadClass("creditCard.DefaultPay");
	        System.out.println("aClass.getName() = " + aClass.getName());
	        CreditCardProcessor ccProcessor=(CreditCardProcessor) aClass.newInstance();
	       ccProcessor.paymentProcessing(null);
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }
  }

  }