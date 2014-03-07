package creditCard;

import billing.ChargeResult;
import billing.UnreachableException;


public class PaypalCreditCardProcessor implements CreditCardProcessor {

	@Override
	public ChargeResult charge(CreditCard creditCard, int amount)
			throws UnreachableException {
		return new ChargeResult(true, "PayPal");
	}
    
}
