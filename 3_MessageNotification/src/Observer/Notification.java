package Observer;

import java.util.ArrayList;
import java.util.List;

public class Notification {
    private List<User> users = new ArrayList<User>();

    public void AddUser(User user) {
        users.add(user);
    }

    public void RemoveUser(User user) {
        users.remove(user);
    }

    public void NotifyUsers(String mensaje) {
        for (User usuario : users) {
            usuario.reciveMessage(mensaje);
        }
    }
}
