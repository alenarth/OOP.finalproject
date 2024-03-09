package trasnformacaoparasocket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class AuthenticationServer {
    private static final int PORT = 12347; // Número da porta para o servidor de autenticação

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Servidor de Autenticação aguardando conexões...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado: " + clientSocket.getInetAddress().getHostAddress());

                ObjectInputStream inputStream = new ObjectInputStream(clientSocket.getInputStream());
                ObjectOutputStream outputStream = new ObjectOutputStream(clientSocket.getOutputStream());

                // Lógica de autenticação
                HashMap<String, String> userCredentials = (HashMap<String, String>) inputStream.readObject();

                String username = userCredentials.get("username");
                String password = userCredentials.get("password");

                // Aqui você deve implementar a lógica de autenticação adequada
                if (validateCredentials(username, password)) {
                    outputStream.writeObject("Login bem-sucedido como " + username + ".");
                } else {
                    outputStream.writeObject("Credenciais inválidas. Tente novamente.");
                }

                clientSocket.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static boolean validateCredentials(String username, String password) {
        // Implemente a lógica de validação de credenciais aqui
        // Por exemplo, compare com um banco de dados ou um conjunto de credenciais válidas
        // Este é um exemplo básico e não deve ser usado em um ambiente de produção
        return username.equals("admin") && password.equals("admin123");
    }
}

