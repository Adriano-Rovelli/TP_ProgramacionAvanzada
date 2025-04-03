package Strategy;

public class EmailNotification implements NotificationChannel {
    @Override
    public void sendMessage(String message, String subject) {
        System.out.println("Email enviado a: " + subject + "\n" +
                "Mensaje: " + message + "\n");
    }
}
