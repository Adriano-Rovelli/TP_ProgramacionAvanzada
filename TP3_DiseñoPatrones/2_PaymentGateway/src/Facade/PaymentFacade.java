package Facade;

import Adapters.MercadoPagoAdapter;
import Adapters.PaymentAdapter;
import Adapters.PaypalAdapter;

public class PaymentFacade {
    private PaymentAdapter paymentAdapter;

    public PaymentFacade(String paymentType) {
        switch (paymentType.toLowerCase()) {
            case "mercado pago":
                paymentAdapter = new MercadoPagoAdapter();
                break;
            case "paypal":
                paymentAdapter = new PaypalAdapter();
                break;
            default:
                throw new IllegalArgumentException("Invalid payment type");
        }
    }

    public boolean makePayment(double amount) {
        return paymentAdapter.processPayment(amount);
    }
}

