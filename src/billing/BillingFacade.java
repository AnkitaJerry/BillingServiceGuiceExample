package billing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.google.inject.Guice;
import com.google.inject.Injector;

import creditCard.CreditCard;
import creditCard.TipoPagos;
import java.lang.String;


public class BillingFacade {
	public static void main(String[] args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader (isr);
		String input;
		do{
			System.out.println("--------------------------------------------");
			System.out.println("Seleccione una metodo de pago");
			System.out.println("1 => Pago pay pal");
			System.out.println("2 => Google");
			System.out.println("3 => PagoUlatam");
			System.out.println("4 => Default directorio carga dinamica en ");
			System.out.println("x => Salir");
			System.out.println("Si conoce otro procesador de tarjetas de credito "
					+ "soportado puede ingresar su nombre.");
			System.out.print("Con que metodo de pago quiere pagar: ");
			input = br.readLine();
			System.out.println("--------------------------------------------");	
			initBilling(input);		
		}while(!input.toLowerCase().equals("x"));
	}

	private static void initBilling(String input) {
		input = input.trim();
		Injector injector = Guice.createInjector(new BillingModule());
		if(input.equals(""+TipoPagos.PAY_PAL)){
			BillingModule.setPaymentMethod(TipoPagos.PAY_PAL);
		}else if(input.equals(""+TipoPagos.GOOGLE)){
			BillingModule.setPaymentMethod(TipoPagos.GOOGLE);
		}else if(input.equals(""+TipoPagos.PAY_ULATAM)){
			BillingModule.setPaymentMethod(TipoPagos.PAY_ULATAM);
		}else if(input.equals("x")){
			System.out.println("************\nHasta pronto!\n************");
			return;
		}else{
			BillingModule.setPaymentMethod(input);
		}
		BillingService billingService=injector.getInstance(BillingService.class);
		billingService.chargeOrder(new PizzaOrder(500), new CreditCard("4564534545233214", 12, 2016));
	}
}
