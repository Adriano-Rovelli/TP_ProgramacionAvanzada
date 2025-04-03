package Observer;

import Strategy.NotificationChannel;

public class User {

    private String name;
    private NotificationChannel channel;

    public User(String name, NotificationChannel channel) {
        this.name = name;
        this.channel = channel;
    }

    public void reciveMessage(String message) {
        channel.sendMessage(message, name);
    }

    public String getName() {
        return name;
    }
}
