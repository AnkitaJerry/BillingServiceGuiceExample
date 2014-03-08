package billing;

import transactionLog.TransactionLog;

import com.google.inject.Inject;

import creditCard.CreditCard;
import creditCard.CreditCardProcessor;

public class RealBillingService implements BillingService {
    
    private final CreditCardProcessor processor;
    private final TransactionLog transactionLog;
    
   
    @Inject
    public RealBillingService(CreditCardProcessor processor, 
                                          TransactionLog transactionLog) {
      this.processor = processor;
      this.transactionLog = transactionLog;
    }

    @Override
    public Receipt chargeOrder(PizzaOrder order, CreditCard creditCard) {
        try {
            ChargeResult result = processor.charge(creditCard, order.getAmount());
            transactionLog.logChargeResult(processor,result);
            transactionLog.logChargeDetails(creditCard,order);

            if (result.wasSuccessful())
                return Receipt.forSuccessfulCharge(order.getAmount());
            else
                return Receipt.forDeclinedCharge(result.getDeclineMessage());
        
        } catch (UnreachableException e) {
            transactionLog.logConnectException(e);
            return Receipt.forSystemFailure(e.getMessage());
        }
    }
}