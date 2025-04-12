import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket socket;
    private DataInputStream in;

    public ClientHandler(Socket socket) {
        this.socket = socket;
        try {
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        String message = "";
        try {
            while (!message.equals("Over")) {
                message = in.readUTF();
                System.out.println("Mensaje del cliente: " + message);
            }
            System.out.println("Cliente desconectado: " + socket.getInetAddress());
            in.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
