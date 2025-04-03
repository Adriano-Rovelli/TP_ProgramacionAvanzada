package Adapters;

import Services.MercadoPagoService;

public class MercadoPagoAdapter implements PaymentAdapter{
    private final MercadoPagoService mercadoPagoService = new MercadoPagoService();


    @Override
    public boolean processPayment(double amount) {
        return mercadoPagoService.payWithMercadoPago(amount);
    }
}
