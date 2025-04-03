package Strategy;

public class SMS_Notification implements NotificationChannel {
    @Override
    public void sendMessage(String message, String subject) {
        System.out.println("Mensaje SMS enviado a: " + subject + "\n" +
                "Mensaje: " + message + "\n");
    }
}
