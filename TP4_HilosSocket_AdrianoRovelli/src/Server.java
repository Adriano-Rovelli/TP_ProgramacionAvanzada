import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket = null;

    public Server(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Servidor esperando conexión en el puerto " + port + "...");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Cliente conectado: " + socket.getInetAddress());

                // Crear y lanzar un hilo para manejar al cliente
                Thread clientThread = new Thread(new ClientHandler(socket));
                clientThread.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Esto se ejecuta si el servidor se cierra
            try {
                if (serverSocket != null && !serverSocket.isClosed()) {
                    serverSocket.close();
                    System.out.println("Servidor cerrado");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Server(5000);
    }
}
