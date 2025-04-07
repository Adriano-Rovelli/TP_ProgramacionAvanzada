package Adapters;

import Services.PaypalService;

public class PaypalAdapter implements PaymentAdapter{

    private final PaypalService paypalService = new PaypalService();

    @Override
    public boolean processPayment(double amount) {
        return paypalService.payWithPayPal(amount);
    }
}
