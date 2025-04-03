import Facade.PaymentFacade;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        PaymentFacade paymentFacade = new PaymentFacade("PayPal");
        boolean result = paymentFacade.makePayment(10);
        System.out.println("Pago realizado: " + result);
    }
}