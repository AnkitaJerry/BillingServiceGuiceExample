package billing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.google.inject.Guice;
import com.google.inject.Injector;

import creditCard.CreditCard;
import creditCard.TipoPagos;

public class BillingFacade {
	public static void main(String[] args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader (isr);
		String input;
		do{
			System.out.println("##############################################");
			System.out.println("Seleccione un procesador de pago");
			System.out.println("1 Pago pay pal");
			System.out.println("2 Google");
			System.out.println("3 PagoUlatam");
			System.out.println("4 Default directorio carga dinamica en ");
			System.out.println("x Sair");
			input = br.readLine().toUpperCase();
			initBilling(input);
			System.out.println("##############################################");
		}while(!input.equals("X"));
	}

	private static void initBilling(String input) {
		Injector injector = Guice.createInjector(new BillingModule());
		if(input.equals(""+TipoPagos.PAY_PAL)){
			BillingModule.setTipoPago(TipoPagos.PAY_PAL);
		}else if(input.equals(""+TipoPagos.GOOGLE)){
			BillingModule.setTipoPago(TipoPagos.GOOGLE);
		}else if(input.equals(""+TipoPagos.PAY_ULATAM)){
			BillingModule.setTipoPago(TipoPagos.PAY_ULATAM);
		}else if(input.equals(""+TipoPagos.DEFAULT)){
			BillingModule.setTipoPago(TipoPagos.DEFAULT);
		}else if(input.equals("X")){
			System.out.println("Entrada invalida");
		}
		BillingService billingService=injector.getInstance(BillingService.class);
		billingService.chargeOrder(new PizzaOrder(500), new CreditCard("45645", 12, 2016));
	}
}
