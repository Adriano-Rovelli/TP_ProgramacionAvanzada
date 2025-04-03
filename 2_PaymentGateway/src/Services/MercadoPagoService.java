package Services;

public class MercadoPagoService {
    public  boolean payWithMercadoPago(double amount) {
        System.out.println("Pagando con mercado pago: $" + amount);
        return true;
    }
}
