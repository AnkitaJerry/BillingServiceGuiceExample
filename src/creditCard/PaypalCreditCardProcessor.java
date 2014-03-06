package creditCard;


public class PaypalCreditCardProcessor implements CreditCardProcessor {

	@Override
	public void paymentProcessing(CreditCard creditCard) {
		System.out.println("Procesando pago PayPal");
		
	}
    
}
