package billing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.google.inject.Guice;
import com.google.inject.Injector;

import creditCard.CreditCard;
import creditCard.TipoPagos;
import java.lang.String;


public class BillingPresentation {
	public static void main(String[] args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader (isr);
		String input;
		do{
			System.out.println(
			"\n|------------------------------------------|" +
			"\n| Seleccione una metodo de pago            |" +
			"\n| 1 => Pago pay pal                        |" +
			"\n| 2 => Google                              |" +
			"\n| 3 => PagoUlatam                          |" +
			"\n| x => Salir                               |" +
			"\n| Si conoce otro procesador de tarjetas de |" +
			"\n| credito soportado puede ingresar su      | " +
			"\n| su nombre                                |" +
			"\n|------------------------------------------|");	
			System.out.print("Con que metodo de pago quiere pagar: ");
			input = br.readLine();
			if(input.toLowerCase().trim().equals("x")){
				System.out.println("          \nHasta pronto!\n");
				return;
			}
			initBilling(input);		
		}while(true);
	}

	private static void initBilling(String input){
		try{
			input = input.trim();
			Injector injector = Guice.createInjector(new BillingModule());
			if(input.equals(""+TipoPagos.PAY_PAL)){
				BillingModule.setPaymentMethod(TipoPagos.PAY_PAL);
			}else if(input.equals(""+TipoPagos.GOOGLE)){
				BillingModule.setPaymentMethod(TipoPagos.GOOGLE);
			}else if(input.equals(""+TipoPagos.PAY_ULATAM)){
				BillingModule.setPaymentMethod(TipoPagos.PAY_ULATAM);
			}else{
				BillingModule.setPaymentMethod(input);
			}
			BillingService billingService=injector.getInstance(BillingService.class);
			billingService.chargeOrder(
					new PizzaOrder(500), new CreditCard("4564534545233214", 12, 2016));
		}catch(Exception e){
			System.out.println(
				"\nUps, something is wrong: \n"+ e.getLocalizedMessage());
		}
	}
		
}
