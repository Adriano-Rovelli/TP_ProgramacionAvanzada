import Observer.Notification;
import Observer.User;
import Strategy.EmailNotification;
import Strategy.NotificationChannel;
import Strategy.SMS_Notification;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // Crear canales de notificación
        NotificationChannel email = new EmailNotification();
        NotificationChannel sms = new SMS_Notification();

        // Crear usuarios con su estrategia de notificación
        User juan = new User("Juan", email);
        User ana = new User("Ana", sms);

        // Crear el notificador (sujeto)
        Notification notificador = new Notification();

        // Agregar usuarios como observadores
        notificador.AddUser(juan);
        notificador.AddUser(ana);


        notificador.NotifyUsers("¡Tenemos una nueva promoción!");
    }
}