package Services;

public class PaypalService {
    public boolean payWithPayPal(double amount) {
        System.out.println("Pagando con PayPal: $" + amount);
        return true;
    }
}

