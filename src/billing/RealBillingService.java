package billing;

import com.google.inject.Guice;
import com.google.inject.Injector;

import creditCard.CreditCard;
import creditCard.CreditCardProcessor;

public class RealBillingService implements BillingService {
	
	public RealBillingService(){
		int a=1;
		for (int i = 0; i < 10; i++) {
			new RealBillingService(a);
			if(a==4){
				a=1;
			}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
    public RealBillingService(int proveedorPago){
    	Injector injector = null;
    	
       // TransactionLog transactionLog = injector.getInstance(DatabaseTransactionLog.class);
    	switch (proveedorPago) {
		case 1:
			injector = Guice.createInjector(new PayPalModule());
			break;
		case 2:
			injector = Guice.createInjector(new GoogleModule());
			break;
		case 3:
			injector = Guice.createInjector(new PayulatamModule());
			break;	
		default:
			injector = Guice.createInjector(new DefaultModule());
			break;
		}
    	CreditCardProcessor creditCardProcessor = injector.getInstance(CreditCardProcessor.class);
    	creditCardProcessor.paymentProcessing(new CreditCard("4564", 5, 2013));
    }
    
    
    private Injector getDefaultCreditCardProcesor() {
		
    	return null;
	}

    
    
    
    
	public static void main(String[] args) {
    	Injector injector = Guice.createInjector(new BillingModule());
    	new RealBillingService();
      }

	
	@Override
	public Receipt chargeOrder(PizzaOrder order,
			CreditCardProcessor creditCardProcessor) {
		// TODO Auto-generated method stub
		return null;
	}






	@Override
	public void pagar() {
		// TODO Auto-generated method stub
		
	}
}