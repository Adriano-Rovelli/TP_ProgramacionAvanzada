import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientHandler extends Thread {
    private final Socket socket;
    private final DataInputStream input;
    private final DataOutputStream output;
    private final int clientID;
    private final List<ClientHandler> clients;

    public ClientHandler(Socket socket, List<ClientHandler> clients) throws IOException {
        this.socket = socket;
        this.clients = clients;
        this.clientID = clients.size();
        this.input = new DataInputStream(socket.getInputStream());
        this.output = new DataOutputStream(socket.getOutputStream());
    }

    public int getClientID() {
        return clientID;
    }

    @Override
    public void run() {
        try {
            output.writeUTF("Conectado como C" + clientID + ". Usa *ALL, *C<num> o *RESOLVE \"expresión\"");
            handleMessages();
        } catch (IOException e) {
            System.out.println("Error en cliente [C" + clientID + "]: " + e.getMessage());
        } finally {
            closeCommunication();
        }
    }

    private void handleMessages() throws IOException {
        String msg;

        while (true) {
            msg = input.readUTF().trim();
            System.out.println("[C" + clientID + "] > " + msg);

            if (msg.equalsIgnoreCase("adios")) {
                output.writeUTF("Conexión cerrada");
                break;
            }

            if (msg.startsWith("*ALL ")) {
                String text = msg.substring(5);
                broadcast("C" + clientID + " (a todos): " + text);

            } else if (msg.startsWith("*C")) {
                String[] parts = msg.split(" ", 2);
                if (parts.length < 2) {
                    output.writeUTF("Formato incorrecto. Usa *C<num> mensaje");
                    continue;
                }

                try {
                    int targetId = Integer.parseInt(parts[0].substring(2));
                    sendToClient(targetId, "C" + clientID + " (privado): " + parts[1]);
                } catch (NumberFormatException e) {
                    output.writeUTF("ID inválido. Usa *C<num> mensaje");
                }

            } else if (msg.startsWith("*RESOLVE")) {
                try {
                    String expr = msg.substring(9).replaceAll("\"", "").trim();
                    double resultado = evaluarExpresionSimple(expr);
                    output.writeUTF("Resultado: " + resultado);
                } catch (Exception e) {
                    output.writeUTF("Error al resolver la expresión. Usa solo números y operadores + - * /");
                }

            } else {
                output.writeUTF("Comando no reconocido. Usa *ALL, *C<num> o *RESOLVE");
            }

            output.flush();
        }
    }

    // Envía un mensaje a todos menos a este cliente
    private void broadcast(String message) {
        for (ClientHandler client : clients) {
            if (client != this && !client.socket.isClosed()) {
                try {
                    client.output.writeUTF(message);
                } catch (IOException e) {
                    System.out.println("Error al enviar a C" + client.clientID);
                }
            }
        }
    }

    // Envía mensaje a un cliente específico
    private void sendToClient(int targetId, String message) throws IOException {
        for (ClientHandler client : clients) {
            if (client.clientID == targetId && !client.socket.isClosed()) {
                client.output.writeUTF(message);
                return;
            }
        }
        output.writeUTF("Cliente C" + targetId + " no encontrado.");
    }

    // Evaluador simple: + - * /
    private double evaluarExpresionSimple(String expr) {
        StringBuilder num = new StringBuilder();
        List<Double> valores = new ArrayList<>();
        List<Character> ops = new ArrayList<>();

        for (char c : expr.toCharArray()) {
            if (Character.isDigit(c) || c == '.') {
                num.append(c);
            } else if ("+-*/".indexOf(c) >= 0) {
                valores.add(Double.parseDouble(num.toString()));
                ops.add(c);
                num = new StringBuilder();
            }
        }
        valores.add(Double.parseDouble(num.toString()));

        // Primero * y /
        for (int i = 0; i < ops.size(); ) {
            char op = ops.get(i);
            if (op == '*' || op == '/') {
                double res = op == '*' ? valores.get(i) * valores.get(i + 1)
                        : valores.get(i) / valores.get(i + 1);
                valores.set(i, res);
                valores.remove(i + 1);
                ops.remove(i);
            } else {
                i++;
            }
        }

        // Luego + y -
        double resultado = valores.get(0);
        for (int i = 0; i < ops.size(); i++) {
            resultado = ops.get(i) == '+' ? resultado + valores.get(i + 1)
                    : resultado - valores.get(i + 1);
        }

        return resultado;
    }

    private void closeCommunication() {
        try {
            clients.remove(this);
            if (input != null) input.close();
            if (output != null) output.close();
            if (socket != null) socket.close();
            System.out.println("Conexión cerrada con cliente [C" + clientID + "]");
        } catch (IOException e) {
            System.out.println("Error al cerrar cliente [C" + clientID + "]");
        }
    }
}
