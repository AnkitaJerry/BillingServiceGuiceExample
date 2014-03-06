package creditCard;

public class PayulatamCreditCardProcessor implements CreditCardProcessor{
	@Override
	public void paymentProcessing(CreditCard creditCard) {
		System.out.println("Procesando pago Payulatam");
	}
}
