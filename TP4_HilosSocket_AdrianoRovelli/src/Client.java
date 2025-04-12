import java.io.*;
import java.net.Socket;

public class Client implements Runnable { // 🔧 Ahora implementa Runnable

    private Socket socket = null;
    private BufferedReader in = null;
    private DataOutputStream out = null;

    public Client(String addr, int port) {
        try {
            socket = new Socket(addr, port);
            System.out.println("Conexión exitosa");

            in = new BufferedReader(new InputStreamReader(System.in));
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void run() {
        String message = "";

        while (!message.equals("Over")) {
            try {
                System.out.print("Mensaje para enviar: ");
                message = in.readLine();
                out.writeUTF(message);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            in.close();
            out.close();
            socket.close();
            System.out.println("Conexión cerrada");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Client client = new Client("127.0.0.1", 5000);
        Thread clientThread = new Thread(client);
        clientThread.start();
    }
}

