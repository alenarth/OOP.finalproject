package trasnformacaoparasocket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;

public class LoginClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 12347; // Número da porta do servidor de autenticação

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);

            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

            // Credenciais do usuário (você pode modificar isso para ler do formulário Swing)
            HashMap<String, String> userCredentials = new HashMap<>();
            userCredentials.put("username", "admin");
            userCredentials.put("password", "admin123");

            outputStream.writeObject(userCredentials);

            // Recebe a resposta do servidor
            String response = (String) inputStream.readObject();
            System.out.println(response);

            socket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
