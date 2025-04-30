import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;

    public Client(String host, int port) {
        try {
            socket = new Socket(host, port);
            System.out.println("Conectado al servidor al puerto " + port);

            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());

            // Hilo que escucha mensajes del servidor todo el tiempo
            Thread receiver = new Thread(() -> {
                try {
                    while (true) {
                        String serverMsg = input.readUTF();
                        System.out.println(">> " + serverMsg);
                    }
                } catch (IOException e) {
                    System.out.println("Conexión cerrada por el servidor.");
                }
            });
            receiver.start();

            // Hilo principal envía mensajes
            Scanner scanner = new Scanner(System.in);
            String msg;
            while (true) {
                msg = scanner.nextLine();
                output.writeUTF(msg);
                if (msg.equalsIgnoreCase("adios")) break;
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            close();
        }
    }

    private void close() {
        try {
            if (input != null) input.close();
            if (output != null) output.close();
            if (socket != null) socket.close();
        } catch (IOException e) {
            System.out.println("Error al cerrar: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Client("127.0.0.1", 5000);
    }
}
