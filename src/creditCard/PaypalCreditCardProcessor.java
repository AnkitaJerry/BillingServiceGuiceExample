package creditCard;

import billing.ChargeResult;
import billing.UnreachableException;

/**
 * 
 *
 */
public class PaypalCreditCardProcessor implements CreditCardProcessor {

    CreditCard card;
    int amount;

    /* (non-Javadoc)
     * @see com.agisoft.app.juice.CreditCardProcessor#charge(com.agisoft.app.juice.CreditCard, java.lang.Object)
     */
    @Override
    public ChargeResult charge(CreditCard creditCard, int amount) throws UnreachableException {
        card = creditCard;
        this.amount = amount;
        return new ChargeResult(true, null);
    }

    /* (non-Javadoc)
     * @see com.agisoft.app.juice.CreditCardProcessor#getCardOfOnlyCharge()
     */
    @Override
    public CreditCard getCardOfOnlyCharge() {
        return card;
    }

    /* (non-Javadoc)
     * @see com.agisoft.app.juice.CreditCardProcessor#getAmountOfOnlyCharge()
     */
    @Override
    public int getAmountOfOnlyCharge() {
        return amount;
    }

}
